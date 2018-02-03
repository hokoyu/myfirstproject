package com.hekeyu.taotao.service;

import java.util.List;

import com.hekeyu.taotao.manage.pojo.Item;

public interface ItemService extends BaseService<Item>{
	/**
	 * 保存商品
	 * @param item
	 * @param desc
	 * @return
	 */
	Long saveItem(Item item,String desc);
	/**
	 * 更新商品
	 * @param item
	 * @param desc
	 */
	void updateItem(Item item, String desc);
}
