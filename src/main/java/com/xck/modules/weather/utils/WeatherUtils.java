package com.xck.modules.weather.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.ui.Model;

import com.xck.modules.weather.entity.NowCity;

import net.sf.json.JSONObject;

public class WeatherUtils {
	public final static String WEATHER_URL = "https://free-api.heweather.com/v5/";
	public final static String KEY = "1b32a177da3a45b9b5d2841058fa1e4e";
	
	public static String request(String city, String type){
		BufferedReader reader = null;
		String result = null;
		StringBuffer sb = new StringBuffer();
		String httpUrl = WEATHER_URL + type + "?city=" + city + "&key=" + KEY;
		
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
	
	public static void addModelAttribute(Model model, NowCity nowCity, String provE, String provC){
		model.addAttribute("city", nowCity);
		model.addAttribute("provE", provE);
		model.addAttribute("provC", provC);
	}
}
