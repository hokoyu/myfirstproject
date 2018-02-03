package com.hekeyu.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.hekeyu.taotao.manage.pojo.Item;
import com.hekeyu.taotao.manage.pojo.ItemDesc;
import com.hekeyu.taotao.mapper.ItemDescMapper;
import com.hekeyu.taotao.mapper.ItemMapper;
import com.hekeyu.taotao.service.ItemService;
@Service
@Transactional
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {
	@Autowired
	private ItemDescMapper itemDescMapper;
	/**
	 * 保存商品
	 */
	@Override
	public Long saveItem(Item item, String desc) {
		saveSelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(itemDesc.getCreated());
		itemDescMapper.insertSelective(itemDesc);
		return item.getId();
	}
	/**
	 * 更新商品
	 */
	@Override
	public void updateItem(Item item, String desc) {
		updateSelective(item);
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setUpdated(new Date());
		itemDescMapper.updateByPrimaryKey(itemDesc);
	}
	

}
