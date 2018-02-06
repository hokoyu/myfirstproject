package com.hekeyu.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hekeyu.taotao.manage.pojo.ContentCategory;
import com.hekeyu.taotao.service.ContentCategoryService;
@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {
	/**
	 * 根据id查找节点
	 * @param itemId
	 * @return
	 */
	@Autowired
	private ContentCategoryService contentCategoryService ;
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ContentCategory>> queryContentCategoryByContentCategoryParentId(@RequestParam(value="id",defaultValue="0") Long parentId) {
		try {
			ContentCategory contentCategory = new ContentCategory();
			contentCategory.setParentId(parentId);
			List<ContentCategory> list = contentCategoryService.queryListByWhere(contentCategory);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 添加内容
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseEntity<ContentCategory> saveContentCategory(ContentCategory contentCategory){
		try {
			ContentCategory result = contentCategoryService.saveContentCategory(contentCategory);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 修改节点
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseEntity<Void> updateContentCategory(ContentCategory contentCategory){
		try {
			contentCategoryService.updateSelective(contentCategory);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	/**
	 * 删除节点
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResponseEntity<Void> deleteContentCategoryById(ContentCategory contentCategory){
		try {
			contentCategoryService.deleteContentCategoryById(contentCategory);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
