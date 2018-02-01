package com.hekeyu.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hekeyu.taotao.service.TestService;
@RequestMapping("/test")
@RestController
public class TestController {
	@Autowired
	private TestService testService;
	@RequestMapping("/queryDate")
	public String queryDate() {
		return testService.getCurrentDate();
	}
}
