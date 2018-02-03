package com.hekeyu.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hekeyu.taotao.manage.pojo.Item;
import com.hekeyu.taotao.service.ItemService;
@RequestMapping("/item")
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	/**
	 * 保存商品
	 */
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item,@RequestParam(value="desc",required=false) String desc){
		try {
			itemService.saveItem(item, desc);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 更新商品
	 */
	public ResponseEntity<Void> updateItem(Item item,@RequestParam(value="desc",required=false) String desc){
		try {
			itemService.updateItem(item,desc);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
