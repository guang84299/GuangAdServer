package com.guang.web.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.guang.base.dao.QueryResult;
import com.guang.web.model.GOffer;
import com.guang.web.service.GOfferService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GOfferAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource private GOfferService offerService;
	
	public String list() {

		QueryResult<GOffer>  qr = offerService.findAlls(0);
		String sindex = ServletActionContext.getRequest().getParameter("index");
		int index = 0;
		if (sindex != null && !"".equals(sindex))
			index = Integer.parseInt(sindex);
		Long num = qr.getNum();
		int start = index * 100;
		if (start > num) {
			start = 0;
		}
		
		List<GOffer> offerList = offerService.findAlls(start).getList();
		
		ActionContext.getContext().put("maxNum", num);
		ActionContext.getContext().put("list", offerList);
		ActionContext.getContext().put("pages", "offer");
		
		return "index";
	}
	
	public String addOffer()
	{
		String appName = ServletActionContext.getRequest().getParameter("appName");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		String gpUrl = ServletActionContext.getRequest().getParameter("gpUrl");
		String channels = ServletActionContext.getRequest().getParameter("channels");
		String openNums = ServletActionContext.getRequest().getParameter("openNum");
		String showNums = ServletActionContext.getRequest().getParameter("showNum");
		String openTimeIntervals = ServletActionContext.getRequest().getParameter("openTimeInterval");
		String showTimeIntervals = ServletActionContext.getRequest().getParameter("showTimeInterval");
		String prioritys = ServletActionContext.getRequest().getParameter("priority");
		String onlines = ServletActionContext.getRequest().getParameter("online");
		String activityName = ServletActionContext.getRequest().getParameter("activityName");
		
		try {
			int openNum = Integer.parseInt(openNums);
			int showNum = Integer.parseInt(showNums);
			float openTimeInterval = Float.parseFloat(openTimeIntervals);
			float showTimeInterval = Float.parseFloat(showTimeIntervals);
			int priority = Integer.parseInt(prioritys);
			boolean online = "1".equals(onlines);
			
			GOffer offer = new GOffer(appName, packageName, gpUrl, online, channels, openNum, showNum, openTimeInterval, showTimeInterval, 0, 0, priority,activityName);
			offerService.add(offer);
			ActionContext.getContext().put("addOffer", "添加成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("addOffer", "添加失败！");
		}
		
		list();
		return "index";
	}
	
	//删除sdk
	public void deleteOffer()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			offerService.delete(Long.parseLong(id));
		}
	}
	
	public String updateOffer()
	{
		String ids = ServletActionContext.getRequest().getParameter("id");
		String appName = ServletActionContext.getRequest().getParameter("appName");
		String packageName = ServletActionContext.getRequest().getParameter("packageName");
		String gpUrl = ServletActionContext.getRequest().getParameter("gpUrl");
		String channels = ServletActionContext.getRequest().getParameter("channels");
		String openNums = ServletActionContext.getRequest().getParameter("openNum");
		String showNums = ServletActionContext.getRequest().getParameter("showNum");
		String openTimeIntervals = ServletActionContext.getRequest().getParameter("openTimeInterval");
		String showTimeIntervals = ServletActionContext.getRequest().getParameter("showTimeInterval");
		String prioritys = ServletActionContext.getRequest().getParameter("priority");
		String onlines = ServletActionContext.getRequest().getParameter("online");
		String activityName = ServletActionContext.getRequest().getParameter("activityName");
		
		try {
			int openNum = Integer.parseInt(openNums);
			int showNum = Integer.parseInt(showNums);
			float openTimeInterval = Float.parseFloat(openTimeIntervals);
			float showTimeInterval = Float.parseFloat(showTimeIntervals);
			int priority = Integer.parseInt(prioritys);
			boolean online = "1".equals(onlines);
			long id = Long.parseLong(ids);
			
			GOffer offer = offerService.find(id);
			offer.setAppName(appName);
			offer.setPackageName(packageName);
			offer.setGpUrl(gpUrl);
			offer.setChannels(channels);
			offer.setOpenNum(openNum);
			offer.setShowNum(showNum);
			offer.setOpenTimeInterval(openTimeInterval);
			offer.setShowTimeInterval(showTimeInterval);
			offer.setPriority(priority);
			offer.setOnline(online);
			offer.setActivityName(activityName);
			
			offerService.update(offer);
			ActionContext.getContext().put("updateOffer","更改成功！");
		} catch (Exception e) {
			ActionContext.getContext().put("updateOffer","更改失败！");
		}
		
		list();
		return "index";
	}
	
	public void findOffer()
	{
		String id = ServletActionContext.getRequest().getParameter("data");
		if(id != null && !"".equals(id))
		{
			GOffer offer = offerService.find(Long.parseLong(id));
			print(JSONObject.fromObject(offer).toString());
		}
	}
	
	public void getOffer()
	{
		List<GOffer> list = offerService.findAllsByOnlie().getList();
		print(JSONArray.fromObject(list));
	}
	
	public synchronized void updateStaOpenNum()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		if(id != null)
		{
			GOffer offer = offerService.find(Long.parseLong(id));
			if(offer != null)
			{
				offer.setStaOpenNum(offer.getStaOpenNum()+1);
				offerService.update(offer);
			}
		}
	}
	
	public synchronized void updateStaShowNum()
	{
		String id = ServletActionContext.getRequest().getParameter("id");
		if(id != null)
		{
			GOffer offer = offerService.find(Long.parseLong(id));
			if(offer != null)
			{
				offer.setStaShowNum(offer.getStaShowNum()+1);
				offerService.update(offer);
			}
		}
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
}
