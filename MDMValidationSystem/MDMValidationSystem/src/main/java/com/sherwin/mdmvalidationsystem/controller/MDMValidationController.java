package com.sherwin.mdmvalidationsystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sherwin.mdmvalidationsystem.dao.ItemDAO;
import com.sherwin.mdmvalidationsystem.model.Item;

@Controller
public class MDMValidationController {
	
	@Autowired
	private ItemDAO itemDAO;

	@RequestMapping(path={"/"},method=RequestMethod.GET)
	public String indexMDM(Model model){
		model.addAttribute("existItems","N");
		return "ItemWFValidation";
	}
	
	@RequestMapping(path={"/ItemWFValidation"},method=RequestMethod.POST)
	public String itemWFValidation (HttpSession session, Model model){
		model.addAttribute("existItems","N");
		return "ItemWFValidation";
	}
	
	@RequestMapping(path={"/ValidateItems"},method=RequestMethod.POST)
	public String validateItems (@ModelAttribute("items") String items, Model model){
		String[] inputItems = items.split(",");
		List<String> existingItems = new ArrayList<String>();
		List<String> notExistingItems = new ArrayList<String>();
		List<Item> listItems = new ArrayList<Item>();
		for (int i = 0; i < inputItems.length; i++) {
			if(itemDAO.itemExist(inputItems[i].trim()))
				existingItems.add(inputItems[i].trim());
			else
				notExistingItems.add(inputItems[i].trim());
		}
		if(!existingItems.isEmpty()){
			model.addAttribute("existItems","S");
			listItems = itemDAO.listItems(existingItems); 
		}else{
			model.addAttribute("existItems","N");
		}
		model.addAttribute("listItems",listItems);
		model.addAttribute("error",notExistingItems);
		return "ItemWFValidation";
	}
}
