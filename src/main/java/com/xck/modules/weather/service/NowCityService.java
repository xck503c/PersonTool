package com.xck.modules.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.xck.modules.weather.entity.NowCity;
import com.xck.modules.weather.mapper.CityMapper;
import com.xck.modules.weather.utils.WeatherUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 实况天气 Service
 * @author xck
 * @version 2017-1-17
 * */
@Service
public class NowCityService  extends BaseCityService{

	@Override
	public String get(String city) {
		return WeatherUtils.request(city, "now");
	}

	public List<NowCity> getData(String jsonData) {
		return pareseJsonData(jsonData);
	}
	
	public ArrayList<NowCity> pareseJsonData(String jsonData){
		ArrayList<NowCity> cityList = new ArrayList<NowCity>();
		
		JSONObject json = JSONObject.fromObject(jsonData);
		JSONArray results = json.getJSONArray("HeWeather5");
		
		if(CollectionUtils.isNotEmpty(results)){
			for (int i=0; i<results.size(); i++){
				JSONObject jsonObj = results.getJSONObject(i);
				NowCity nowCity = CityMapper.mappingNowCity(jsonObj);
				cityList.add(nowCity);
			}
		}
		return cityList;
	}
}
