package com.xck.modules.weather.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.commons.collections.CollectionUtils;

import com.xck.modules.weather.entity.Weather;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeatherUtils {
	public final static String WEATHER_URL = "https://free-api.heweather.com/v5/now";
	public final static String KEY = "1b32a177da3a45b9b5d2841058fa1e4e";
	
	public static String request(String city){
		BufferedReader reader = null;
		String result = null;
		StringBuffer sb = new StringBuffer();
		String httpUrl = WEATHER_URL + "?city=" + city + "&key=" + KEY;
		
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			System.out.println(conn.getURL());
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
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static ArrayList<Weather> pareseJsonData(String jsonData){
		ArrayList<Weather> cityList = new ArrayList();
		
		JSONObject json = JSONObject.fromObject(jsonData);
		JSONArray results = json.getJSONArray("HeWeather5");
		
		if(CollectionUtils.isNotEmpty(results)){
			for (int i=0; i<results.size(); i++){
				JSONObject jsonObj = results.getJSONObject(i);
				System.out.println(jsonObj.toString());
				Weather w = new Weather();
				JSONObject basicObj = jsonObj.getJSONObject("basic");
				
				w.setId(basicObj.getString("id"));
				w.setCity(basicObj.getString("city"));
				w.setCnty(basicObj.getString("cnty"));
				JSONObject basicUTObj = basicObj.getJSONObject("update");
				w.setUpdate(basicUTObj.getString("loc"));
				
				JSONObject nowObj = jsonObj.getJSONObject("now");
				JSONObject condObj = nowObj.getJSONObject("cond");
				w.setCond(condObj.getString("txt"));
				w.setHum(nowObj.getInt("hum"));
				w.setPcpn(nowObj.getDouble("pcpn"));
				//w.setPop(nowObj.getInt("pop"));
				w.setPres(nowObj.getInt("pres"));
				w.setTmp(nowObj.getInt("tmp"));
				w.setVis(nowObj.getInt("vis"));
				
				JSONObject windObj = nowObj.getJSONObject("wind");
				w.setDeg(windObj.getInt("deg"));
				w.setDir(windObj.getString("dir"));
				w.setSc(windObj.getString("sc"));
				w.setSpd(windObj.getInt("spd"));
				
				cityList.add(w);
			}
		}
		return cityList;
	}
}
