package com.hekeyu.taotao.service;

import java.util.List;

import com.hekeyu.taotao.manage.pojo.ItemCat;

public interface ItemCatService extends BaseService<ItemCat>{
	public List<ItemCat> queryItemCatListByPage(Integer page, Integer rows);
}
