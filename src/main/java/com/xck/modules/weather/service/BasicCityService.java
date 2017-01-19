package com.xck.modules.weather.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xck.modules.weather.entity.BasicCity;
import com.xck.modules.weather.mapper.CityMapper;
import com.xck.modules.weather.utils.WeatherUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * 城市的基础信息Service
 * @author xck503c
 * @version 2017-1-18
 * */
@Service
public class BasicCityService extends BaseCityService{

	/*
	 * 通过city关键字来获取basic城市信息，目前没有使用
	 * @return String返回查询到的JSON字符串
	 * */
	@Override
	public String get(String city) {
		return WeatherUtils.request(city, "search");
	}
	
	/*
	 * 通过关键字搜索匹配的城市
	 * @return List
	 * */
	public List<BasicCity> getListByCity(String city, List<BasicCity> list) {
		return searchCityInProv(city, list);
	}
	
	/*
	 * 获取同一个省份的城市信息列表
	 * @return List
	 * */
	public List<BasicCity> getAllList(String prov) {
		return pareseBasicCityJSONData(prov);
	}
	
	/*
	 * 通过关键字来获取JSON文件中的对应数据
	 * @return List
	 * */
	public ArrayList<BasicCity> pareseBasicCityJSONData(String prov){
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
	 * 通过关键字city对list里面的城市名进行搜索匹配(中文和拼音)
	 * @return List返回符合条件的List
	 * */
	public List<BasicCity> searchCityInProv(String city, List<BasicCity> list){
		List<BasicCity> result = new ArrayList<BasicCity>();
		for(int i=0; i<list.size(); i++){
			BasicCity bc = list.get(i);
			String cityEn = bc.getCityEn();
			String cityZh = bc.getCity();
			if(cityEn.indexOf(city) != -1|| cityZh.indexOf(city) != -1){
				result.add(bc);
			}
		}
		return result;
	}

	/*
	 * 试图获取所属省份的城市列表，若session中没有对应的列表则将其添加到session中，
	 * 否则直接返回
	 * @return boolean
	 * */
	public boolean prov(String provC, String provE, HttpSession session, Model model) {
		String key = provE + "allcityList";
		List<BasicCity> list = null;
		if(provE != null || !provE.trim().equals("")){
			if(session.getAttribute(key) != null){
				model.addAttribute("cityList", session.getAttribute(key));
			}else{
				list = getAllList(provE);
				model.addAttribute("cityList", list);
				System.out.println("Session添加省份" + provC + provE + "列表");
				session.setAttribute(key, list);
			}
			WeatherUtils.addModelAttribute(model, provE, provC);
			return true;
		}
		return false;
	}

	public boolean query(String city, String provC, String provE,HttpSession session, Model model) {
		String key = provE + "allcityList";
		//如果session里面没有指定省份的城市列表直接返回到index页面重新来
		List<BasicCity> provList = (List<BasicCity>) session.getAttribute(key);
		if(provList == null){
			return false;
		}
		List<BasicCity> list = getListByCity(city, provList);
		WeatherUtils.addModelAttribute(model, provE, provC);
		model.addAttribute("cityList", list);
		session.setAttribute("cityList", list);
		return true;
	}

	public void index(String provC, String provE, HttpSession session, Model model) {
		String key = provE + "allcityList";
		List<BasicCity> provList = (List<BasicCity>) session.getAttribute(key);
		//检查session中是否有对应省份的列表，若有就移除
		if(provList != null){
			System.out.println("Session移除省份" + provC + provE + "列表");
			session.removeAttribute(key);
		}
	}
}
