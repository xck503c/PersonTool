package com.xck.modules.weather.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xck.modules.weather.service.BasicCityService;

/*
 * 城市基础信息Controller
 * @author xck503c
 * @version 2017-1-18
 * */

@Controller
@RequestMapping(value="/f/weather/basicCity")
public class BasicCityController extends BaseCityController{
	
	@Autowired
	private BasicCityService basicCityService;
	
	@RequestMapping(value="/index", method={RequestMethod.POST, RequestMethod.GET})
	public String index(HttpServletRequest request, String provC, String provE, Model model) {
		HttpSession session = request.getSession(true);
		basicCityService.index(provC, provE, session, model);
		return "weather/weather_index";
	}
	
	@RequestMapping(value="/query", method={RequestMethod.POST, RequestMethod.GET})
	public String query(HttpServletRequest request, String city, String provC, String provE, Model model) {
		HttpSession session = request.getSession(true);
		boolean result = basicCityService.query(city, provC, provE, session, model);
		if(result){
			return "weather/weather_city";
		}
		return "weather/weather_index";
	}
	
	@RequestMapping(value="/prov", method={RequestMethod.POST, RequestMethod.GET})
	public String prov(HttpServletRequest request, String provC, String provE, Model model) {
		HttpSession session = request.getSession(true);
		boolean result = basicCityService.prov(provC, provE, session, model);
		if(result){
			return "weather/weather_city";
		}
		return "weather/weather_index";
	}
}
