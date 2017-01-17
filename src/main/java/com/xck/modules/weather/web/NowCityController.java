package com.xck.modules.weather.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xck.modules.weather.entity.NowCity;
import com.xck.modules.weather.service.NowCityService;

@Controller
@RequestMapping(value="/f/weather/nowCity")
public class NowCityController extends BaseCityController{

	@Autowired
	private NowCityService nowCityService;
	
	@RequestMapping(value="/query", method={RequestMethod.POST, RequestMethod.GET})
	public String callWeatherApi(HttpServletRequest request, String city, Model model) {
		String jsonData = nowCityService.get(city);
		System.out.println(jsonData);
		List<NowCity> list = nowCityService.getData(jsonData);
		
		model.addAttribute("jsonData", jsonData);
		model.addAttribute("cityList", list);
		return "weather/weather_now";
	}
}
