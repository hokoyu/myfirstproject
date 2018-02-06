package com.hekeyu.taotao.protal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@RequestMapping("/index")
@Controller
public class IndexController {
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView toIndex() {
		ModelAndView model = new ModelAndView("index");
		return model;
	}
}
