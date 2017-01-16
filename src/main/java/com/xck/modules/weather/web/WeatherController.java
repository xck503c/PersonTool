package com.xck.modules.weather.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xck.modules.weather.entity.Weather;
import com.xck.modules.weather.service.WeatherService;

@Controller
@RequestMapping(value = "/f/weather")
public class WeatherController {
	@Autowired
	private WeatherService weatherService;
	
	@RequestMapping(value="/query", method={RequestMethod.POST, RequestMethod.GET})
	public String callWeatherApi(HttpServletRequest request, String city, Model model) {
		String jsonData = weatherService.get(city);
		System.out.println(jsonData);
		List<Weather> list = weatherService.parseJsonData(jsonData);
		
		model.addAttribute("jsonData", jsonData);
		model.addAttribute("cityList", list);
		return "weather";
	}
}
