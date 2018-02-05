package com.hekeyu.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hekeyu.taotao.manage.pojo.ItemCat;
import com.hekeyu.taotao.service.ItemCatService;

@RequestMapping("/item/cat")
@Controller
public class ItemCatController {
	@Autowired
	private ItemCatService itemCatService;
	@RequestMapping(value="/query/{page}")
	public ResponseEntity<List<ItemCat>> queryListByPage(@PathVariable("page") Integer page,@RequestParam(value="rows",defaultValue="10") Integer rows) {
		System.out.println("111");
		try {
			List<ItemCat> list = itemCatService.queryItemCatListByPage(page,rows);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> queryItemCatListByParentId(@RequestParam(value="id",defaultValue="0") Long parentId){
		try {
			ItemCat itemCat = new ItemCat();
			itemCat.setParentId(parentId);
			List<ItemCat> list = itemCatService.queryListByWhere(itemCat);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	/**
	 * 根据id找商品项类目
	 * @param id
	 * @return
	 */
	@RequestMapping(value="{itemCatId}",method=RequestMethod.GET)
	public ResponseEntity<ItemCat> queryItemCatById(@PathVariable Long itemCatId){
		
		try {
			ItemCat itemCat = itemCatService.queryById(itemCatId);
			return ResponseEntity.ok(itemCat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
