package com.xck.modules.weather.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xck.modules.weather.entity.NowCity;
import com.xck.modules.weather.utils.WeatherUtils;


/*
 * 实时城市天气信息 Service
 * @author xck503c
 * @version 2017-1-18
 * */
@Service
public class NowCityService  extends BaseCityService{

	/*
	 * 实时获取指定城市的天气JSON数据
	 * @return String JSON字符串
	 * */
	@Override
	public String get(String city) {
		return WeatherUtils.request(city, "now");
	}

	/*
	 * 解析数据，并check省份是否正确
	 * @return NowCity 实时天气数据
	 * */
	public NowCity getData(String jsonData, String prov) {
		NowCity nowCity = WeatherUtils.pareseNowCityJSONData(jsonData);
		String getProv = nowCity.getProv();
		if(!getProv.equals(prov) && prov != null){
			nowCity.setProv(prov);
			return nowCity;
		}
		return nowCity;
	}

	public boolean query(String city, String provC, String provE, Model model) {
		String jsonData = get(city);
		if(jsonData.equals("error")){
			model.addAttribute("msg", "request error, try again latter");
			return false;
		}
		NowCity nowCity = getData(jsonData, provC);	
		WeatherUtils.addModelAttribute(model, nowCity, provE, provC);
		return true;
	}

	public void returnUp(Model model, String provC, String provE) {
		WeatherUtils.addModelAttribute(model, provE, provC);
	}
}
