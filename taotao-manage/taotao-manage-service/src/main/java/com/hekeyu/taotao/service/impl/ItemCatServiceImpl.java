package com.hekeyu.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.hekeyu.taotao.manage.pojo.ItemCat;
import com.hekeyu.taotao.mapper.ItemCatMapper;
import com.hekeyu.taotao.service.ItemCatService;
@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public List<ItemCat> queryItemCatListByPage(Integer page, Integer rows) {
		
		
		PageHelper.startPage(page, rows);
		return itemCatMapper.selectAll();
		
	}

}
