package com.xck.modules.weather.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.xck.modules.weather.entity.Weather;
import com.xck.modules.weather.utils.WeatherUtils;

/*
 * 天气 service
 * @author xck
 * @version 2017-1-16
 * */

@Service
public class WeatherService {
	public String get(String city){
		return WeatherUtils.request(city);
	}
	
	public List<Weather> parseJsonData(String jsonData){
		return WeatherUtils.pareseJsonData(jsonData);
	}
}
