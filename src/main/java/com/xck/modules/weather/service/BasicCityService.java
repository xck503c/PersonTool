package com.xck.modules.weather.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.xck.modules.weather.entity.BasicCity;
import com.xck.modules.weather.mapper.CityMapper;
import com.xck.modules.weather.utils.WeatherUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class BasicCityService extends BaseCityService{

	@Override
	public String get(String city) {
		return WeatherUtils.request(city, "search");
	}
	
	public List<BasicCity> getData(String jsonData) {
		return pareseJsonData(jsonData);
	}

	public ArrayList<BasicCity> pareseJsonData(String jsonData){
		ArrayList<BasicCity> cityList = new ArrayList<BasicCity>();
		
		JSONObject json = JSONObject.fromObject(jsonData);
		JSONArray results = json.getJSONArray("HeWeather5");
		
		if(CollectionUtils.isNotEmpty(results)){
			for (int i=0; i<results.size(); i++){
				JSONObject jsonObj = results.getJSONObject(i);
				BasicCity basicCity = CityMapper.mappingBasicCity(jsonObj);
				cityList.add(basicCity);
			}
		}
		return cityList;
	}
}
