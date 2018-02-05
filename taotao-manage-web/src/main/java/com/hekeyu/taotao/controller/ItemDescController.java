package com.hekeyu.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hekeyu.taotao.manage.pojo.ItemDesc;
import com.hekeyu.taotao.service.ItemDescService;
@RequestMapping("/item/desc")
@Controller
public class ItemDescController {
	/**
	 * 根据商品id查找描述
	 * @param itemId
	 * @return
	 */
	@Autowired
	private ItemDescService itemDescService ;
	@RequestMapping("/{itemId}")
	public ResponseEntity<ItemDesc> queryItemDescByItemId(@PathVariable Long itemId) {
		try {
			ItemDesc list= itemDescService.queryById(itemId);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
