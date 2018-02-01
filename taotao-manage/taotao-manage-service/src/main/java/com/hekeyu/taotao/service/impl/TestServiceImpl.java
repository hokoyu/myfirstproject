package com.hekeyu.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hekeyu.taotao.mapper.TestMapper;
import com.hekeyu.taotao.service.TestService;
@Service
@Transactional
public class TestServiceImpl implements TestService {
	@Autowired
	private TestMapper testMapper;
	@Override
	public String getCurrentDate() {
		return testMapper.queryCurrentDate();
	}

}
