package com.weather.app.myweather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class WeatherController  {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);

    @RequestMapping("/root")
    public String getRootPage(HttpServletRequest request){
        /*logger.debug("Inside Root method in controller..");
        if(request.getHeader("cookie")!=null){
            for(Cookie cookie : request.getCookies()){
                logger.debug(cookie.getName()+","+cookie.getValue());
            }
        }*/
        return "/view/index.html";
    }
}
