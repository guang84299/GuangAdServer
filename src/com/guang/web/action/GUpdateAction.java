package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.guang.base.dao.QueryResult;
import com.guang.tools.ApkTools;
import com.guang.tools.StringTools;
import com.guang.web.model.GUpdate;
import com.guang.web.service.GUpdateService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GUpdateAction extends ActionSupport{
	private static final Logger logger = LoggerFactory.getLogger(GUpdateAction.class);
	private static final long serialVersionUID = 1L;
	@Resource private GUpdateService tbUpdateService;
	
	private File apk;
	private String apkFileName;
	private String online_state;
	private String channel;
	private String stopRun_state;
	
	public String list() {

		QueryResult<GUpdate>  qr = tbUpdateService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GUpdate> updateList = tbUpdateService.findAlls(start).getList();
		
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", updateList);
		ActionContext.getContext().put("pages", "update");
		return "index";
	}
	
	public void print(Object obj)
	{
		try {
			ServletActionContext.getResponse().getWriter().print(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String addUpdate()
	{
		if(apk == null || StringTools.isEmpty(channel))
		{
			ActionContext.getContext().put("addUpdate", "添加失败！");
			list();
			return "index";
		}
		GUpdate update = tbUpdateService.findFirst(channel);
		String code = "1";
		if(update != null)
		{
			code = update.getVersionCode();
			code = (Integer.parseInt(code)+1)+"";
		}
			
		String apk_relpath = ServletActionContext.getServletContext().getRealPath(
				"update/" + channel+code);
		try {
			//上传apk		
			File file = new File(new File(apk_relpath), apkFileName);
			if (!file.getParentFile().exists())
				file.getParentFile().mkdirs();
			FileUtils.copyFile(apk, file);
			String downloadPath = apk_relpath + "/" + apkFileName;
			String []str = ApkTools.unZip(downloadPath, "");	
			String versionName = str[0];
			String packageName = str[1];
			String versionCode = str[2];
			boolean online = false;
			if(online_state != null && "1".equals(online_state))
			{
				online = true;
			}
			downloadPath = "update/" + channel+code +  "/" + apkFileName;
			tbUpdateService.add(new GUpdate(packageName, versionName, versionCode,
					downloadPath, online,0l,channel));
			ActionContext.getContext().put("addUpdate", "添加成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("addUpdate", "添加失败！");
		}
		list();
		return "index";
	}
	//删除update
	public void deleteUpdate()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			tbUpdateService.delete(Long.parseLong(id));
		}
	}
	
	public void findUpdate()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			GUpdate update = tbUpdateService.find(Long.parseLong(id));
			print(JSONObject.fromObject(update).toString());
		}
	}
	
	public String updateUpdate()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String online_state = ServletActionContext.getRequest().getParameter("online_state");
		if(id != null && !"".equals(id))
		{
			GUpdate update = tbUpdateService.find(Long.parseLong(id));
			if("1".equals(online_state))
				update.setOnline(true);
			else
				update.setOnline(false);
			tbUpdateService.update(update);
			
			ActionContext.getContext().put("updateUpdate","更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateUpdate","更改失败！");
		return "index";
	}
	
	
	//另外的应用用
	public void findTBNew()
	{		
		String channel = ServletActionContext.getRequest().getParameter("channel");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		if(channel != null && packageName != null)
		{
			GUpdate update = tbUpdateService.findNew(packageName,channel);
			if(update == null)
			{
				JSONObject obj = new JSONObject();
				obj.put("stopRun", false);
				print(obj.toString());
			}
			else
			{
				if(update.getOnline())
					print(JSONObject.fromObject(update).toString());
				else
				{
					JSONObject obj = new JSONObject();
					print(obj.toString());
				}
			}
		}
		else
		{
			JSONObject obj = new JSONObject();
			obj.put("stopRun", false);
			print(obj.toString());
		}
	}
	
	
	
	public synchronized void updateTBNum()
	{
		String channel = ServletActionContext.getRequest().getParameter("channel");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		if(channel != null && packageName != null)
		{
			GUpdate update = tbUpdateService.findNew(packageName,channel);
			if(update != null)
			{
				update.setUpdateNum(update.getUpdateNum()+1);
				tbUpdateService.update(update);
			}
		}
	}
	
	
	
	public void getConnec()
	{
		print(true);
	}
	
	public File getApk() {
		return apk;
	}

	public void setApk(File apk) {
		this.apk = apk;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

	public String getOnline_state() {
		return online_state;
	}

	public void setOnline_state(String online_state) {
		this.online_state = online_state;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getStopRun_state() {
		return stopRun_state;
	}

	public void setStopRun_state(String stopRun_state) {
		this.stopRun_state = stopRun_state;
	}

	
	
	
}
