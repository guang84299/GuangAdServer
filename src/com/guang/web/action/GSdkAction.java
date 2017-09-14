package com.guang.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import com.guang.web.model.GSdk;
import com.guang.web.service.GSdkService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GSdkAction extends ActionSupport{
	private static final Logger logger = LoggerFactory.getLogger(GSdkAction.class);
	private static final long serialVersionUID = 1L;
	@Resource private GSdkService sdkService;
	
	private static List<String> adPositions = new ArrayList<String>();
	static{
		adPositions.add("spot");
		adPositions.add("banner");
		adPositions.add("gp");
	}
	
	private File apk;
	private String apkFileName;
	private String online_state;
	private String channel;
	
	public String list() {

		QueryResult<GSdk>  qr = sdkService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GSdk> sdkList = sdkService.findAlls(start).getList();
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", sdkList);
		ActionContext.getContext().put("adPositions", adPositions);
		ActionContext.getContext().put("pages", "sdk");
		
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
	public void println(Object obj)
	{
		try {
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().println(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String addSdk()
	{
		if(apk == null || StringTools.isEmpty(channel))
		{
			ActionContext.getContext().put("addSdk", "添加失败！");
			list();
			return "index";
		}
		GSdk sdk = sdkService.findFirst(channel);
		String code = "1";
		if(sdk != null)
		{
			code = sdk.getVersionCode();
			code = (Integer.parseInt(code)+1)+"";
		}
			
		String apk_relpath = ServletActionContext.getServletContext().getRealPath(
				"sdk/" + channel+code);
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
			
			
			downloadPath = "sdk/" + channel+code +  "/" + apkFileName;
			
			String netTypes = "";
			String netTypes_1 = ServletActionContext.getRequest().getParameter("netTypes_1");
			String netTypes_2 = ServletActionContext.getRequest().getParameter("netTypes_2");
			String netTypes_3 = ServletActionContext.getRequest().getParameter("netTypes_3");
			String netTypes_4 = ServletActionContext.getRequest().getParameter("netTypes_4");
			String netTypes_5 = ServletActionContext.getRequest().getParameter("netTypes_5");
			
			if(netTypes_1 != null && !"".equals(netTypes_1))
				netTypes += netTypes_1;
			if(netTypes_2 != null && !"".equals(netTypes_2))
				netTypes += " "+netTypes_2;
			if(netTypes_3 != null && !"".equals(netTypes_3))
				netTypes += " "+netTypes_3;
			if(netTypes_4 != null && !"".equals(netTypes_4))
				netTypes += " "+netTypes_4;
			if(netTypes_5 != null && !"".equals(netTypes_5))
				netTypes += " "+netTypes_5;
			
			String name = ServletActionContext.getRequest().getParameter("name");
			String appPackageName = ServletActionContext.getRequest().getParameter("appPackageName");
			String loopTime = ServletActionContext.getRequest().getParameter("loopTime");
			String callLogNum = ServletActionContext.getRequest().getParameter("callLogNum");
			String timeLimt = ServletActionContext.getRequest().getParameter("timeLimt");
			String appNum = ServletActionContext.getRequest().getParameter("appNum");
			String showNum = ServletActionContext.getRequest().getParameter("showNum");
			String blackList = ServletActionContext.getRequest().getParameter("blackList");
			String showTimeInterval = ServletActionContext.getRequest().getParameter("showTimeInterval");
						
			//广告位
			String adPositionSwitch = "";
			for(String adPosition : adPositions)
			{
				String p = ServletActionContext.getRequest().getParameter("adPositionSwitch_"+adPosition);
				if(p != null)
					adPositionSwitch = adPositionSwitch + adPosition + ",";
			}
			if(adPositionSwitch.endsWith(","))
				adPositionSwitch = adPositionSwitch.substring(0, adPositionSwitch.length()-1);
			
			float floopTime = 0;
			if(loopTime != null && !"".equals(loopTime))
			{
				floopTime = Float.parseFloat(loopTime);
			}
			
			int fcallLogNum = 0;
			if(!StringTools.isEmpty(callLogNum))
				fcallLogNum = Integer.parseInt(callLogNum);
			
			float ftimeLimt = 0;
			if(!StringTools.isEmpty(timeLimt))
				ftimeLimt = Float.parseFloat(timeLimt);
			
			int fappNum = 0;
			if(!StringTools.isEmpty(appNum))
				fappNum = Integer.parseInt(appNum);
			
			int fshowNum = 0;
			if(!StringTools.isEmpty(showNum))
				fshowNum = Integer.parseInt(showNum);
			
			float fshowTimeInterval = 0;
			if(!StringTools.isEmpty(showTimeInterval))
				fshowTimeInterval = Float.parseFloat(showTimeInterval);
			
			GSdk sdks = new GSdk(packageName, versionName, versionCode, downloadPath, online,0l,channel,
					netTypes,name,appPackageName,adPositionSwitch,floopTime,fcallLogNum,ftimeLimt,fappNum);
			sdks.setShowNum(fshowNum);
			sdks.setBlackList(blackList);
			sdks.setShowTimeInterval(fshowTimeInterval);
			sdkService.add(sdks);
			ActionContext.getContext().put("addSdk", "添加成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("addSdk", "添加失败！");
		}
		list();
		return "index";
	}
	//删除sdk
	public void deleteSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			sdkService.delete(Long.parseLong(id));
		}
	}
	
	public void findSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			GSdk sdk = sdkService.find(Long.parseLong(id));
			print(JSONObject.fromObject(sdk).toString());
		}
	}
	
	public String updateSdk()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		String online_state = ServletActionContext.getRequest().getParameter("online_state");
		
		String netTypes = "";
		String netTypes_1 = ServletActionContext.getRequest().getParameter("netTypes_1");
		String netTypes_2 = ServletActionContext.getRequest().getParameter("netTypes_2");
		String netTypes_3 = ServletActionContext.getRequest().getParameter("netTypes_3");
		String netTypes_4 = ServletActionContext.getRequest().getParameter("netTypes_4");
		String netTypes_5 = ServletActionContext.getRequest().getParameter("netTypes_5");
		
		if(netTypes_1 != null && !"".equals(netTypes_1))
			netTypes += netTypes_1;
		if(netTypes_2 != null && !"".equals(netTypes_2))
			netTypes += " "+netTypes_2;
		if(netTypes_3 != null && !"".equals(netTypes_3))
			netTypes += " "+netTypes_3;
		if(netTypes_4 != null && !"".equals(netTypes_4))
			netTypes += " "+netTypes_4;
		if(netTypes_5 != null && !"".equals(netTypes_5))
			netTypes += " "+netTypes_5;
		
		String name = ServletActionContext.getRequest().getParameter("name");
		String appPackageName = ServletActionContext.getRequest().getParameter("appPackageName");
		String loopTime = ServletActionContext.getRequest().getParameter("loopTime");
		String callLogNum = ServletActionContext.getRequest().getParameter("callLogNum");
		String timeLimt = ServletActionContext.getRequest().getParameter("timeLimt");
		String appNum = ServletActionContext.getRequest().getParameter("appNum");
		String showNum = ServletActionContext.getRequest().getParameter("showNum");
		String blackList = ServletActionContext.getRequest().getParameter("blackList");
		String showTimeInterval = ServletActionContext.getRequest().getParameter("showTimeInterval");
		
		if(id != null && !"".equals(id))
		{
			GSdk sdk = sdkService.find(Long.parseLong(id));
			if("1".equals(online_state))
				sdk.setOnline(true);
			else
				sdk.setOnline(false);
			
			//广告位
			String adPositionSwitch = "";
			for(String adPosition : adPositions)
			{
				String p = ServletActionContext.getRequest().getParameter("adPositionSwitch_"+adPosition);
				if(p != null)
					adPositionSwitch = adPositionSwitch + adPosition + ",";
			}
			if(adPositionSwitch.endsWith(","))
				adPositionSwitch = adPositionSwitch.substring(0, adPositionSwitch.length()-1);			
			
			sdk.setNetTypes(netTypes);
			sdk.setName(name);
			sdk.setAppPackageName(appPackageName);
			sdk.setAdPosition(adPositionSwitch);
			if(loopTime != null && !"".equals(loopTime))
			{
				sdk.setLoopTime(Float.parseFloat(loopTime));
			}
			if(StringTools.isEmpty(callLogNum))
				sdk.setCallLogNum(0);
			else
				sdk.setCallLogNum(Integer.parseInt(callLogNum));
			
			if(StringTools.isEmpty(timeLimt))
				sdk.setTimeLimt(0.f);
			else
				sdk.setTimeLimt(Float.parseFloat(timeLimt));
			
			if(StringTools.isEmpty(appNum))
				sdk.setAppNum(0);
			else
				sdk.setAppNum(Integer.parseInt(appNum));
			
			if(StringTools.isEmpty(showNum))
				sdk.setShowNum(0);
			else
				sdk.setShowNum(Integer.parseInt(showNum));
			
			sdk.setBlackList(blackList);
			
			if(StringTools.isEmpty(showTimeInterval))
				sdk.setShowTimeInterval(0.f);
			else
				sdk.setShowTimeInterval(Float.parseFloat(showTimeInterval));
			
			sdkService.update(sdk);
			
			ActionContext.getContext().put("updateSdk","更改成功！");
			list();
			return "index";
		}
		list();
		ActionContext.getContext().put("updateSdk","更改失败！");
		return "index";
	}
	
	
	public void findNewSdk()
	{		
		String channel = ServletActionContext.getRequest().getParameter("channel");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		if(channel != null && packageName != null)
		{
			GSdk sdk = sdkService.findNew2(packageName,channel);
			if(sdk != null)
				print(JSONObject.fromObject(sdk).toString());
			else
				print(new JSONObject().toString());
		}
		else
		{
			print(new JSONObject().toString());
		}
	}
	
	
	public synchronized void updateNum()
	{
		String channel = ServletActionContext.getRequest().getParameter("channel");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		if(channel != null && packageName != null)
		{
			GSdk sdk = sdkService.findNew2(packageName,channel);
			if(sdk != null)
			{
				sdk.setUpdateNum(sdk.getUpdateNum()+1);
				sdkService.update(sdk);
			}
		}
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

	
}
