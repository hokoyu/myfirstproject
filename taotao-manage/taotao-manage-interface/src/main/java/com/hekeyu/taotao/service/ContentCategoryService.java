package com.hekeyu.taotao.service;


import com.hekeyu.taotao.manage.pojo.ContentCategory;

public interface ContentCategoryService extends BaseService<ContentCategory>{

	ContentCategory saveContentCategory(ContentCategory contentCategory);

	void deleteContentCategoryById(ContentCategory contentCategory);
	
}
