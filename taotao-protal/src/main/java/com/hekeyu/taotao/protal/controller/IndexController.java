package com.hekeyu.taotao.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hekeyu.taotao.manage.pojo.Content;
import com.hekeyu.taotao.service.ContentService;
@RequestMapping("/index")
@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView toIndex() {
		ModelAndView model = new ModelAndView("index");
		try {
			model.addObject("bigAdData",contentService.querybigAdData());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
