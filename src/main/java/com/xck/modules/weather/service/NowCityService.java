package com.xck.modules.weather.service;

import org.springframework.stereotype.Service;

import com.xck.modules.weather.entity.NowCity;
import com.xck.modules.weather.mapper.CityMapper;
import com.xck.modules.weather.utils.WeatherUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
		NowCity nowCity = pareseJsonData(jsonData);
		String getProv = nowCity.getProv();
		if(!getProv.equals(prov) && prov != null){
			nowCity.setProv(prov);
			return nowCity;
		}
		return nowCity;
	}
	
	/*
	 * 解析JSON字符串
	 * @return NowCity
	 * */
	public NowCity pareseJsonData(String jsonData){
		NowCity nowCity = new NowCity();
		
		JSONObject json = JSONObject.fromObject(jsonData);
		JSONObject jsonObj = json.getJSONArray("HeWeather5").getJSONObject(0);
		
		if(!jsonObj.isEmpty()){
			nowCity = CityMapper.mappingNowCity(jsonObj);
		}
		return nowCity;
	}
}
