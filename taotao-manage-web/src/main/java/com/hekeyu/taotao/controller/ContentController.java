package com.hekeyu.taotao.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hekeyu.taotao.common.vo.DataGridResult;
import com.hekeyu.taotao.manage.pojo.Content;
import com.hekeyu.taotao.service.ContentService;
@RequestMapping("/content")
@Controller
public class ContentController {
	@Autowired
	private ContentService contentService;
	
	/**
	 * 分页查找指定类目的内容
	 * @param page
	 * @param rows
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<DataGridResult> queryContentListByPage(@RequestParam(value="page",required=false,defaultValue="1")Integer page,@RequestParam(value="rows",required=false,defaultValue="20") Integer rows,@RequestParam(value = "categoryId", defaultValue = "0")Long categoryId) {
		try {
			DataGridResult dataGridResult = contentService.queryContentListByPage(page,rows,categoryId);
			return ResponseEntity.ok(dataGridResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	/**
	 * 添加内容
	 * @param content
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> addContent(Content content){
		try {
			contentService.saveSelective(content);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	/**
	 * 更新内容
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public ResponseEntity<Void> editContent(Content content){
		try {
			contentService.updateSelective(content);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> deleteContent(@RequestParam(value="ids") Long[] ids){
		Map<String, Object> map = new HashMap<>();
		map.put("status", 500);
		try {
			contentService.deleteByIds(ids);
			map.put("status", 200);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(map);
	}
}
