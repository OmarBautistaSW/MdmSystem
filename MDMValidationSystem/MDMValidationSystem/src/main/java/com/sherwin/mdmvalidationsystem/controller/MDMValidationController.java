package com.sherwin.mdmvalidationsystem.controller;

import java.util.List;

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
		return "index";
	}
	
	@RequestMapping(path={"/ItemWFValidation"},method=RequestMethod.POST)
	public String itemWFValidation (@ModelAttribute("region") String region,Model model){
		model.addAttribute("region",region.toUpperCase());
		return "ItemWFValidation";
	}
	
	@RequestMapping(path={"/ValidateItems"},method=RequestMethod.POST)
	public String validateItems (@ModelAttribute("items") String items, Model model){
		List<Item> listItems = itemDAO.listItems(items);
		model.addAttribute("listItems",listItems);
		return "ItemWFValidation";
	}
}
