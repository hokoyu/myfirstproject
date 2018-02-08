package com.hekeyu.taotao.service;


import com.hekeyu.taotao.common.vo.DataGridResult;
import com.hekeyu.taotao.manage.pojo.Content;

public interface ContentService extends BaseService<Content>{

	DataGridResult queryContentListByPage(Integer page, Integer rows, Long categoryId);

	String querybigAdData() throws Exception;
	
}
