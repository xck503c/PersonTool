package com.xck.modules.weather.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xck.modules.weather.entity.BasicCity;
import com.xck.modules.weather.service.BasicCityService;

/*
 * @version 2017-1-17
 * */

@Controller
@RequestMapping(value="/f/weather")
public class BasicCityController extends BaseCityController{
	
	@Autowired
	private BasicCityService basicCityService;
	
	@RequestMapping(value="/index", method={RequestMethod.POST, RequestMethod.GET})
	public String index(HttpServletRequest request, Model model) {
		return "weather/weather_index";
	}
	
	@RequestMapping(value="/query", method={RequestMethod.POST, RequestMethod.GET})
	public String query(HttpServletRequest request, String city, Model model) {
		String jsonData = basicCityService.get(city);
		System.out.println(jsonData);
		List<BasicCity> list = basicCityService.getData(jsonData);
		
		model.addAttribute("jsonData", jsonData);
		model.addAttribute("cityList", list);
		return "weather/weather_city";
	}
}
