package com.guang.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "gsdk")
public class GSdk {
	private long id;
	private String packageName;// 包名
	private String versionName;// 版本名
	private String versionCode;// 版本号
	private String downloadPath;//下载路径 
	private boolean online;//是否上线
	private long updateNum;//更新次数
	private String channel;//渠道
	
	private String netTypes;//网络
	private String name;//应用名字
	private String appPackageName;// 应用包名
	private String adPosition;
	private float loopTime;
	private int callLogNum;
	private float timeLimt;
	private int appNum;
	
	private Date updatedDate;

	public GSdk(){}
	public GSdk(String packageName, String versionName, String versionCode,
			String downloadPath, boolean online, long updateNum,
			String channel, String netTypes, String name,
			String appPackageName, String adPosition, float loopTime,
			int callLogNum, float timeLimt, int appNum) {
		super();
		this.packageName = packageName;
		this.versionName = versionName;
		this.versionCode = versionCode;
		this.downloadPath = downloadPath;
		this.online = online;
		this.updateNum = updateNum;
		this.channel = channel;
		this.netTypes = netTypes;
		this.name = name;
		this.appPackageName = appPackageName;
		this.adPosition = adPosition;
		this.loopTime = loopTime;
		this.callLogNum = callLogNum;
		this.timeLimt = timeLimt;
		this.appNum = appNum;
		this.updatedDate = new Date();
	}
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "packageName", length = 128)
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	@Column(name = "versionName", length = 64)
	public String getVersionName() {
		return versionName;
	}
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	@Column(name = "versionCode", length = 64)
	public String getVersionCode() {
		return versionCode;
	}
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	@Column(name = "downloadPath",  length = 128) 
	public String getDownloadPath() {
		return downloadPath;
	}
	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public long getUpdateNum() {
		return updateNum;
	}
	public void setUpdateNum(long updateNum) {
		this.updateNum = updateNum;
	}
	@Column(name = "channel", length = 64)
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	@Column(name = "netTypes",  length = 128) 
	public String getNetTypes() {
		return netTypes;
	}
	public void setNetTypes(String netTypes) {
		this.netTypes = netTypes;
	}
	@Column(length = 64)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(length = 64)
	public String getAppPackageName() {
		return appPackageName;
	}
	public void setAppPackageName(String appPackageName) {
		this.appPackageName = appPackageName;
	}
	@Column(length = 128)
	public String getAdPosition() {
		return adPosition;
	}
	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}
	public float getLoopTime() {
		return loopTime;
	}
	public void setLoopTime(float loopTime) {
		this.loopTime = loopTime;
	}
	public int getCallLogNum() {
		return callLogNum;
	}
	public void setCallLogNum(int callLogNum) {
		this.callLogNum = callLogNum;
	}
	public float getTimeLimt() {
		return timeLimt;
	}
	public void setTimeLimt(float timeLimt) {
		this.timeLimt = timeLimt;
	}
	public int getAppNum() {
		return appNum;
	}
	public void setAppNum(int appNum) {
		this.appNum = appNum;
	}
	@Column(name = "updated_date")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
}
