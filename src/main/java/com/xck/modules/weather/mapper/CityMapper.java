package com.xck.modules.weather.mapper;

import com.xck.modules.weather.entity.BasicCity;
import com.xck.modules.weather.entity.NowCity;
import com.xck.modules.weather.utils.WeatherUtils;

import net.sf.json.JSONObject;

public class CityMapper {
	public static NowCity mappingNowCity(JSONObject jsonObj){
		NowCity nowCity = new NowCity();
		
		JSONObject basicObj = jsonObj.getJSONObject("basic");
		nowCity.setId(WeatherUtils.checkJSONNodeString(basicObj, "id"));
		nowCity.setCity(WeatherUtils.checkJSONNodeString(basicObj, "city"));
		nowCity.setCnty(WeatherUtils.checkJSONNodeString(basicObj, "cnty"));
		nowCity.setProv(WeatherUtils.checkJSONNodeString(basicObj, "prow"));
		
		JSONObject basicUTObj = basicObj.getJSONObject("update");
		nowCity.setUpdate(WeatherUtils.checkJSONNodeString(basicUTObj, "loc"));
		
		JSONObject nowObj = jsonObj.getJSONObject("now");
		JSONObject condObj = nowObj.getJSONObject("cond");
		nowCity.setCond(WeatherUtils.checkJSONNodeString(condObj, "txt"));
		nowCity.setFl(WeatherUtils.checkJSONNodeInteger(nowObj, "fl"));
		nowCity.setHum(WeatherUtils.checkJSONNodeInteger(nowObj, "hum"));
		nowCity.setPcpn(WeatherUtils.checkJSONNodeDouble(nowObj, "pcpn"));
		nowCity.setPres(WeatherUtils.checkJSONNodeInteger(nowObj, "pres"));
		nowCity.setTmp(WeatherUtils.checkJSONNodeInteger(nowObj, "tmp"));
		nowCity.setVis(WeatherUtils.checkJSONNodeInteger(nowObj, "vis"));
		
		JSONObject windObj = nowObj.getJSONObject("wind");
		nowCity.setDeg(WeatherUtils.checkJSONNodeInteger(windObj, "deg"));
		nowCity.setDir(WeatherUtils.checkJSONNodeString(windObj, "dir"));
		nowCity.setSc(WeatherUtils.checkJSONNodeString(windObj, "sc"));
		nowCity.setSpd(WeatherUtils.checkJSONNodeInteger(windObj, "spd"));
		
		return nowCity;
	}
	
	public static BasicCity mappingBasicCity(JSONObject jsonObj){
		BasicCity basicCity = new BasicCity();
		JSONObject basicObj = jsonObj.getJSONObject("basic");
		basicCity.setId(WeatherUtils.checkJSONNodeString(basicObj, "id"));
		basicCity.setCity(WeatherUtils.checkJSONNodeString(basicObj, "city"));
		basicCity.setCnty(WeatherUtils.checkJSONNodeString(basicObj, "cnty"));
		basicCity.setProv(WeatherUtils.checkJSONNodeString(basicObj, "prow"));
		return basicCity;
	}
}
