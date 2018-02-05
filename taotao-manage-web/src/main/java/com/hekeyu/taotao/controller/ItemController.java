package com.hekeyu.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hekeyu.taotao.common.vo.DataGridResult;
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
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Void> updateItem(Item item,@RequestParam(value="desc",required=false) String desc){
		try {
			itemService.updateItem(item,desc);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 根据页号，页面大小和标题模糊查询并排序
	 * @param title
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<DataGridResult> queryListItem(@RequestParam(value="title",required=false) String title,@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows",defaultValue="30") Integer rows) {
		try {
			DataGridResult dataGridResult = itemService.queryListItem(title,page,rows);
			return ResponseEntity.ok(dataGridResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 删除商品根据id
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public ResponseEntity<Void> delItemById(@RequestParam(value="ids") String[] ids){
		try {
			itemService.deleteByIds(ids);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 下架商品
	 */
	@RequestMapping(value="/instock",method=RequestMethod.POST)
	public ResponseEntity<Void> instockItemById(@RequestParam(value="ids") String[] ids){
		itemService.instockItemByIds(ids);
		return ResponseEntity.ok(null);
	}
	/**
	 * 下架商品
	 */
	@RequestMapping(value="/reshelf",method=RequestMethod.POST)
	public ResponseEntity<Void> reshelfItemById(@RequestParam(value="ids") String[] ids){
		itemService.reshelfItemByIds(ids);
		return ResponseEntity.ok(null);
	}
}
