package com.hekeyu.taotao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hekeyu.taotao.common.vo.DataGridResult;
import com.hekeyu.taotao.manage.pojo.Content;
import com.hekeyu.taotao.mapper.ContentMapper;
import com.hekeyu.taotao.service.ContentService;
import com.hekeyu.taotao.service.redis.RedisService;
@Service
@Transactional
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {
	@Value(value="${TAOTAO_PORTAL_INDEX_BIG_AD_NUMBER}")
	private Integer TAOTAO_PORTAL_INDEX_BIG_AD_NUMBER; 
	@Value(value="${CONTENT_CATEGORY_BIG_AD_ID}")
	private Long CONTENT_CATEGORY_BIG_AD_ID;
	
	@Autowired
	private RedisService redisService;
	// 大广告数据在redis对应的key的名称
	private static final String REDIS_BIG_AD_KEY = "PORTAL_INDEX_BIG_AD_DATA";
	// 大广告数据在redis对应的过期时间；1天
	private static final int REDIS_BIG_AD_EXPIRE_TIME = 60 * 60 * 24;
	//转换为json字符串
	private static final ObjectMapper Mapper = new ObjectMapper();
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
	
	/**
	 * 查找大广告
	 * @throws JsonProcessingException 
	 */
	
	@Override
	public String querybigAdData() throws Exception {
		//先redis库中找
		String str = redisService.get(REDIS_BIG_AD_KEY);
		if(StringUtils.isNotBlank(str)) {
			System.out.println("从redis中查找");
			return str;
		}
		//数据库中查找
		DataGridResult list = queryContentListByPage(1, TAOTAO_PORTAL_INDEX_BIG_AD_NUMBER, CONTENT_CATEGORY_BIG_AD_ID);
		List<Content> contentList = (List<Content>)list.getRows();
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> map = null;
		if(contentList != null){
			for (Content c : contentList) {
				map = new HashMap<>();
				map.put("alt", c.getTitle());
				map.put("height", 240);
				map.put("heightB", 240);
				map.put("href", c.getUrl());
				map.put("src", c.getPic());
				map.put("srcB", c.getPic());
				map.put("width", 670);
				map.put("widthB", 550);
				resultList.add(map);
			}
		}
		String str2 = Mapper.writeValueAsString(resultList);
		try {
			redisService.setex(REDIS_BIG_AD_KEY, REDIS_BIG_AD_EXPIRE_TIME, str2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str2;
	}
	

}
