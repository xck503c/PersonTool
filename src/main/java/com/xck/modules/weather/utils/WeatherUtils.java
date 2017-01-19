package com.xck.modules.weather.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.ui.Model;

import com.xck.modules.weather.entity.BasicCity;
import com.xck.modules.weather.entity.NowCity;
import com.xck.modules.weather.mapper.CityMapper;
import com.xck.modules.weather.service.BasicCityService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeatherUtils {
	public final static String WEATHER_URL = "https://free-api.heweather.com/v5/";
	public final static String KEY = "1b32a177da3a45b9b5d2841058fa1e4e";
	
	public static String request(String city, String type){
		BufferedReader reader = null;
		String result = null;
		StringBuffer sb = new StringBuffer();
		String httpUrl = WEATHER_URL + type + "?city=" + city + "&key=" + KEY;
		System.out.println(httpUrl);
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			while((temp = reader.readLine()) != null){
				sb.append(temp);
				sb.append("\r\n");
			}
			reader.close();
			result = sb.toString();
		} catch(Exception e){
			return "error";
		}
		return result;
	}
	
	public static String checkJSONNodeString(JSONObject obj, String key){
		if(!obj.isNullObject() && obj.has(key)){
			return obj.getString(key);
		}
		return "";
	}
	
	public static Integer checkJSONNodeInteger(JSONObject obj, String key){
		if(!obj.isNullObject() && obj.has(key)){
			return obj.getInt(key);
		}
		return 0;
	}
	
	public static Double checkJSONNodeDouble(JSONObject obj, String key){
		if(!obj.isNullObject() && obj.has(key)){
			return obj.getDouble(key);
		}
		return 0.0;
	}
	
	public static void addModelAttribute(Model model, String provE, String provC){
		model.addAttribute("provE", provE);
		model.addAttribute("provC", provC);
	}
	
	public static void addModelAttribute(Model model, List<BasicCity> cityList, String provE, String provC) {
		model.addAttribute("cityList", cityList);
		model.addAttribute("provE", provE);
		model.addAttribute("provC", provC);
	}
	
	public static void addModelAttribute(Model model, NowCity nowCity, String provE, String provC){
		model.addAttribute("city", nowCity);
		model.addAttribute("provE", provE);
		model.addAttribute("provC", provC);
	}
	
	/*
	 * 通过关键字来解析JSON文件BasicCity
	 * @return List
	 * */
	public static ArrayList<BasicCity> pareseBasicCityJSONByProv(String prov){
		ArrayList<BasicCity> cityList = new ArrayList<BasicCity>();
		try{
			InputStream is=BasicCityService.class.getClassLoader().getResourceAsStream("china-city-list.json");
			StringBuilder sb = new StringBuilder();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String temp = null;
			while((temp = reader.readLine()) != null){
				sb.append(temp);
			}
			JSONObject json = JSONObject.fromObject(sb.toString());
			JSONArray results = json.getJSONArray(prov);
			
			if(CollectionUtils.isNotEmpty(results)){
				for (int i=0; i<results.size(); i++){
					JSONObject jsonObj = results.getJSONObject(i);
					BasicCity basicCity = CityMapper.mappingBasicCityBySource(jsonObj);
					cityList.add(basicCity);
				}
			}
			return cityList;
		}catch(Exception e){
			
		}
		return cityList;
	}
	
	/*
	 * 解析BasicCity单个城市的JSON数据
	 * @return ArrayList
	 * */
	public static ArrayList<BasicCity> pareseBasicCityJSON(JSONObject obj){
		ArrayList<BasicCity> cityList = new ArrayList<BasicCity>();
		try{
			JSONArray results = obj.getJSONArray("HeWeather5");		
			if(CollectionUtils.isNotEmpty(results)){
				for (int i=0; i<results.size(); i++){
					JSONObject jsonObj = results.getJSONObject(i);
					BasicCity basicCity = CityMapper.mappingBasicCity(jsonObj);
					cityList.add(basicCity);
				}
			}
			return cityList;
		}catch(Exception e){
			
		}
		return cityList;
	}
	
	/*
	 * 解析NowCity实时JSON字符串
	 * @return NowCity
	 * */
	public static NowCity pareseNowCityJSONData(String jsonData){
		NowCity nowCity = new NowCity();
		
		JSONObject json = JSONObject.fromObject(jsonData);
		JSONObject jsonObj = json.getJSONArray("HeWeather5").getJSONObject(0);
		
		if(!jsonObj.isEmpty()){
			nowCity = CityMapper.mappingNowCity(jsonObj);
		}
		return nowCity;
	}
}
