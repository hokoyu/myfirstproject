package com.hekeyu.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hekeyu.taotao.common.vo.DataGridResult;
import com.hekeyu.taotao.manage.pojo.Content;
import com.hekeyu.taotao.mapper.ContentMapper;
import com.hekeyu.taotao.service.ContentService;
@Service
@Transactional
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {
	@Autowired
	private ContentMapper contentMapper;
	/**
	 * 分页查找内容
	 */
	@Override
	public DataGridResult queryContentListByPage(Integer page, Integer rows, Long categoryId) {
		PageHelper.startPage(page, rows);
		Content content = new Content();
		content.setCategoryId(categoryId);
		List<Content> list = queryListByWhere(content);
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		
		return new DataGridResult(pageInfo.getTotal(), pageInfo.getList());
	}
	

}
