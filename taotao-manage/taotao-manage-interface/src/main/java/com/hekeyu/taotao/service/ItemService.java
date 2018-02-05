package com.hekeyu.taotao.service;

import java.io.Serializable;
import java.util.List;

import com.hekeyu.taotao.common.vo.DataGridResult;
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
	/**
	 * 分页查询商品
	 * @param title
	 * @param page
	 * @param rows
	 * @return
	 */
	DataGridResult queryListItem(String title, Integer page, Integer rows);
	
	//根据ids更新
	void instockItemByIds(Serializable[] ids);
	//上架
	void reshelfItemByIds(Serializable[] ids);
	
}
