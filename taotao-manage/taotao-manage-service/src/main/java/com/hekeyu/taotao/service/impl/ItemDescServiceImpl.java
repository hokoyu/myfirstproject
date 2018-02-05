package com.hekeyu.taotao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hekeyu.taotao.manage.pojo.ItemDesc;
import com.hekeyu.taotao.mapper.ItemDescMapper;
import com.hekeyu.taotao.mapper.TestMapper;
import com.hekeyu.taotao.service.ItemDescService;
import com.hekeyu.taotao.service.TestService;
@Service
@Transactional
public class ItemDescServiceImpl extends BaseServiceImpl<ItemDesc> implements ItemDescService {
	@Autowired
	private ItemDescMapper itemDescMapper;
	

}
