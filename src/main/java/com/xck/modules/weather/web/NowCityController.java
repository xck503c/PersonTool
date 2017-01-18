package com.xck.modules.weather.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xck.modules.weather.entity.NowCity;
import com.xck.modules.weather.service.NowCityService;

/*
 * 实时城市天气信息Controller
 * @author xck503c
 * @version 2017-1-18
 * */
@Controller
@RequestMapping(value="/f/weather/nowCity")
public class NowCityController extends BaseCityController{

	@Autowired
	private NowCityService nowCityService;
	
	@RequestMapping(value="/query", method={RequestMethod.POST, RequestMethod.GET})
	public String query(HttpServletRequest request, String city, String provC, String provE, Model model) {
		String jsonData = nowCityService.get(city);
		NowCity now = nowCityService.getData(jsonData, provC);
		
		model.addAttribute("city", now);
		model.addAttribute("provC", provC);
		model.addAttribute("provE", provE);
		return "weather/weather_now";
	}
	
	@RequestMapping(value="/index", method={RequestMethod.POST, RequestMethod.GET})
	public String returnUp(HttpServletRequest request, String provC, String provE, Model model) {
		model.addAttribute("provC", provC);
		model.addAttribute("provE", provE);
		 return "redirect:/f/weather/basicCity/selectProv";
	}
}
