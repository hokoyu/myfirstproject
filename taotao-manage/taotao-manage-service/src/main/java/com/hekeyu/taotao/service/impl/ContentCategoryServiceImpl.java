package com.hekeyu.taotao.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hekeyu.taotao.manage.pojo.ContentCategory;
import com.hekeyu.taotao.manage.pojo.ItemDesc;
import com.hekeyu.taotao.mapper.ContentCategoryMapper;
import com.hekeyu.taotao.mapper.ItemDescMapper;
import com.hekeyu.taotao.mapper.TestMapper;
import com.hekeyu.taotao.service.ContentCategoryService;
import com.hekeyu.taotao.service.ItemDescService;
import com.hekeyu.taotao.service.TestService;
@Service
@Transactional
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {
	@Autowired
	private ContentCategoryMapper contentCategoryMapper;
	/**
	 * 保存内容分类
	 */
	@Override
	public ContentCategory saveContentCategory(ContentCategory contentCategory) {
		//保存contentCategory
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(contentCategory.getCreated());
		contentCategory.setIsParent(false);
		contentCategoryMapper.insert(contentCategory);
		//更新父节点
		ContentCategory parent = new ContentCategory();
		parent.setId(contentCategory.getParentId());
		parent.setIsParent(true);
		contentCategoryMapper.updateByPrimaryKeySelective(parent);
		return contentCategory;
	}
	/**
	 * 删除分类节点，记得删除底下的所有节点，并且更新父节点
	 */
	@Override
	public void deleteContentCategoryById(ContentCategory contentCategory) {
		Long parentId = contentCategory.getParentId();
		//将所有需要删除的id出到集合中删除
		List<Long> ids = new ArrayList<>();
		ids.add(contentCategory.getId());
		getContentCategoryId(ids,contentCategory.getId());
		deleteByIds(ids.toArray(new Long[] {}));
		//查找是否还有父id和删除的节点一样的节点
		ContentCategory contentCate = new ContentCategory();
		contentCate.setParentId(parentId);
		Integer count = queryCountByWhere(contentCate);
		if(count==0) {
			ContentCategory t = new ContentCategory();
			t.setId(parentId);
			t.setIsParent(false);
			updateSelective(t);
		}
	}
	
	private void getContentCategoryId(List<Long> ids, Long parentId) {
		ContentCategory category = new ContentCategory();
		category.setParentId(parentId);
		List<ContentCategory> list = queryListByWhere(category);
		for (ContentCategory contentCategory : list) {
			ids.add(contentCategory.getId());
			if(contentCategory.getIsParent()) {
				getContentCategoryId(ids, contentCategory.getId());
			}
		}
	}
	

}
