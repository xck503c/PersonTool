package com.xck.modules.weather.entity;

/*
 * 天气 Entity
 * @author xck503c
 * @version 2017-1-18
 * */
public abstract class BaseCity {
	//basic基本信息
	protected String id; //城市ID
	protected String city; //城市
	protected String cityEn; //城市英文
	protected String cnty; //国家中文
	protected String prov; //城市所属省份(仅限国内城市)
	protected String update; //更新时间

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityEn() {
		return cityEn;
	}
	public void setCityEn(String cityEn) {
		this.cityEn = cityEn;
	}
	public String getCnty() {
		return cnty;
	}
	public void setCnty(String cnty) {
		this.cnty = cnty;
	}
	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
}
