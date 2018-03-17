package com.guang.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goffer")
public class GOffer {
	private long id;
	private String appName;//应用名字
	private String packageName;// 包名
	private String gpUrl;//GP路径 
	private boolean online;//是否上线
	private String channels;//渠道
	private int openNum;//应用打开次数
	private int showNum;//广告展示次数
	private float openTimeInterval;//广告应用打开时间间隔
	private float showTimeInterval;//广告展示时间间隔
	private long staOpenNum;//统计应用打开次数
	private long staShowNum;//统计广告展示次数
	private int priority;//优先级
	private String activityName;//入口类名
	private Date created;
	
	
	public GOffer(){}
	public GOffer(String appName, String packageName, String gpUrl,
			boolean online, String channels, int openNum, int showNum,
			float openTimeInterval, float showTimeInterval, long staOpenNum,
			long staShowNum,int priority,String activityName) {
		super();
		this.appName = appName;
		this.packageName = packageName;
		this.gpUrl = gpUrl;
		this.online = online;
		this.channels = channels;
		this.openNum = openNum;
		this.showNum = showNum;
		this.openTimeInterval = openTimeInterval;
		this.showTimeInterval = showTimeInterval;
		this.staOpenNum = staOpenNum;
		this.staShowNum = staShowNum;
		this.priority = priority;
		this.activityName = activityName;
		this.created = new Date();
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(length = 64)
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	@Column(length = 128)
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	@Column(length = 256) 
	public String getGpUrl() {
		return gpUrl;
	}
	public void setGpUrl(String gpUrl) {
		this.gpUrl = gpUrl;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	@Column(length = 128)
	public String getChannels() {
		return channels;
	}
	public void setChannels(String channels) {
		this.channels = channels;
	}
	public int getOpenNum() {
		return openNum;
	}
	public void setOpenNum(int openNum) {
		this.openNum = openNum;
	}
	public int getShowNum() {
		return showNum;
	}
	public float getOpenTimeInterval() {
		return openTimeInterval;
	}
	public void setOpenTimeInterval(float openTimeInterval) {
		this.openTimeInterval = openTimeInterval;
	}
	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}
	public float getShowTimeInterval() {
		return showTimeInterval;
	}
	public void setShowTimeInterval(float showTimeInterval) {
		this.showTimeInterval = showTimeInterval;
	}
	public long getStaOpenNum() {
		return staOpenNum;
	}
	public void setStaOpenNum(long staOpenNum) {
		this.staOpenNum = staOpenNum;
	}
	public long getStaShowNum() {
		return staShowNum;
	}
	public void setStaShowNum(long staShowNum) {
		this.staShowNum = staShowNum;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Column(length = 128)
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	
}
