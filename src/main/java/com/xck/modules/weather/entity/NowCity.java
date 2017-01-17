package com.xck.modules.weather.entity;

public class NowCity extends BaseCity{
	
	private String cond; //天气状况(阴)
	
	private Integer fl; //体感温度
	private Integer hum; //相对湿度(%)
	private Double pcpn; //降水量(mm)
	private Integer pres; //气压
	private Integer tmp; //当前温度；
	private Integer vis; //能见度
	
	//风力风向
	private Integer deg; //方向(360度)
	private String dir; //文字风向
	private String sc; //风力等级
	private Integer spd; //风速(kmph)
	
	public String getCond() {
		return cond;
	}
	public void setCond(String cond) {
		this.cond = cond;
	}
	public Integer getFl() {
		return fl;
	}
	public void setFl(Integer fl) {
		this.fl = fl;
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
	public Integer getPres() {
		return pres;
	}
	public void setPres(Integer pres) {
		this.pres = pres;
	}
	public Integer getTmp() {
		return tmp;
	}
	public void setTmp(Integer tmp) {
		this.tmp = tmp;
	}
	public Integer getVis() {
		return vis;
	}
	public void setVis(Integer vis) {
		this.vis = vis;
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
}
