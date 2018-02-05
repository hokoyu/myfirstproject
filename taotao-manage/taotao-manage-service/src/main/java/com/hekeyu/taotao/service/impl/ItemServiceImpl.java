package com.hekeyu.taotao.service.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hekeyu.taotao.common.vo.DataGridResult;
import com.hekeyu.taotao.manage.pojo.Item;
import com.hekeyu.taotao.manage.pojo.ItemDesc;
import com.hekeyu.taotao.mapper.ItemDescMapper;
import com.hekeyu.taotao.mapper.ItemMapper;
import com.hekeyu.taotao.service.ItemService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
@Transactional
public class ItemServiceImpl extends BaseServiceImpl<Item> implements ItemService {
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Autowired
	private ItemMapper itemMapper;
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
	/**
	 * 分页查询商品
	 */
	@Override
	public DataGridResult queryListItem(String title, Integer page, Integer rows) {
		Example example = new Example(Item.class);
		Criteria criteria = example.createCriteria();
		try {
			if(StringUtils.isNotBlank(title)) {
				//模糊查询条件
				title=URLDecoder.decode(title, "utf-8");
				criteria.andLike("title", "%"+title+"%");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//根据时间排序，降序
		example.orderBy("updated").desc();
		PageHelper.startPage(page,rows);
		List<Item> list = itemMapper.selectByExample(example);
		PageInfo<Item> pageInfo = new PageInfo<>(list);
		return new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
	}
	
	/**
	 * 下架
	 */
	@Override
	public void instockItemByIds(Serializable[] ids) {
		Item item = new Item();
		item.setStatus(2);
		Example example = new Example(Item.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", Arrays.asList(ids));
		itemMapper.updateByExampleSelective(item, example);
	}
	
	/**
	 * 上架reshelf
	 */
	
	@Override
	public void reshelfItemByIds(Serializable[] ids) {
		Item item = new Item();
		item.setStatus(1);
		Example example = new Example(Item.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", Arrays.asList(ids));
		itemMapper.updateByExampleSelective(item, example);
	}
}
