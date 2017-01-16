package com.xck.modules.weather.entity;

/*
 * 天气 Entity
 * @author xck
 * @version 2017-1-16
 * */
public class Weather {
	//basic基本信息
	private String id; //城市ID
	private String city; //城市
	private String cnty; //国家
	private String update; //更新时间
	
	private Integer hum; //相对湿度(%)
	private Double pcpn; //降水量(mm)
	private Integer pop; //降水概率
	private Integer pres; //气压
	private Integer vis; //能见度
	
	private String cond; //天气状况(阴)
	
	//风力风向
	private Integer deg; //方向(360度)
	private String dir; //文字风向
	private String sc; //风力等级
	private Integer spd; //风速(kmph)
	//温度
	private Integer tmp; //当前温度；
	private Integer tmp_max; //最高温度
	private Integer tmp_min; //最低温度
	
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
	public String getCnty() {
		return cnty;
	}
	public void setCnty(String cnty) {
		this.cnty = cnty;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public Integer getHum() {
		return hum;
	}
	public void setHum(Integer hum) {
		this.hum = hum;
	}
	public Double getPcpn() {
		return pcpn;
	}
	public void setPcpn(Double pcpn) {
		this.pcpn = pcpn;
	}
	public Integer getPop() {
		return pop;
	}
	public void setPop(Integer pop) {
		this.pop = pop;
	}
	public Integer getPres() {
		return pres;
	}
	public void setPres(Integer pres) {
		this.pres = pres;
	}
	public Integer getVis() {
		return vis;
	}
	public void setVis(Integer vis) {
		this.vis = vis;
	}
	public String getCond() {
		return cond;
	}
	public void setCond(String cond) {
		this.cond = cond;
	}
	public Integer getDeg() {
		return deg;
	}
	public void setDeg(Integer deg) {
		this.deg = deg;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public Integer getSpd() {
		return spd;
	}
	public void setSpd(Integer spd) {
		this.spd = spd;
	}
	public Integer getTmp() {
		return tmp;
	}
	public void setTmp(Integer tmp) {
		this.tmp = tmp;
	}
	public Integer getTmp_max() {
		return tmp_max;
	}
	public void setTmp_max(Integer tmp_max) {
		this.tmp_max = tmp_max;
	}
	public Integer getTmp_min() {
		return tmp_min;
	}
	public void setTmp_min(Integer tmp_min) {
		this.tmp_min = tmp_min;
	}
}
