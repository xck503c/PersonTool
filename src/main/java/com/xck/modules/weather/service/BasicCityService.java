package com.xck.modules.weather.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.xck.modules.weather.entity.BasicCity;
import com.xck.modules.weather.utils.WeatherUtils;

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
		return WeatherUtils.pareseBasicCityJSONByProv(prov);
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

	/*
	 * 一个是全省城市查询，一个是单个城市查询
	 * @return boolean
	 * */
	public boolean query(String city, String provC, String provE,HttpSession session, Model model) {
		if(provC ==  null|| provE == null){
			String data = get(city);
			if(data.equals("error") || data == null || data.trim().equals("")){
				model.addAttribute("msg", "request error");
				return false;
			}
			JSONObject json = JSONObject.fromObject(data.toString());
			List<BasicCity> list = WeatherUtils.pareseBasicCityJSON(json);
			model.addAttribute("cityList", list);
			return true;
		}
		String key = provE + "allcityList";
		//如果session里面没有指定省份的城市列表直接返回到index页面重新来
		List<BasicCity> provList = (List<BasicCity>) session.getAttribute(key);
		if(provList == null){
			return false;
		}
		List<BasicCity> list = getListByCity(city, provList);
		WeatherUtils.addModelAttribute(model, provE, provC);
		model.addAttribute("cityList", list);
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
