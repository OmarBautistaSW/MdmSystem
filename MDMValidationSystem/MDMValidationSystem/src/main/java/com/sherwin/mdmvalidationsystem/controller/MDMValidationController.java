package com.sherwin.mdmvalidationsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sherwin.mdmvalidationsystem.dao.ItemDAO;
import com.sherwin.mdmvalidationsystem.dao.ItemDAOAPAC;
import com.sherwin.mdmvalidationsystem.dao.ItemDAOEMEA;
import com.sherwin.mdmvalidationsystem.model.Item;

@Controller
public class MDMValidationController {
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private ItemDAOEMEA itemDAOEMEA;
	
	@Autowired
	private ItemDAOAPAC itemDAOAPAC;

	@RequestMapping(path={"/LACG"},method=RequestMethod.GET)
	public String indexMDM(Model model){
		model.addAttribute("existItems","N");
		model.addAttribute("region","LACG");
		return "ItemWFValidation";
	}
	
	@RequestMapping(path={"/EMEA"},method=RequestMethod.GET)
	public String indexMDMEMEA(Model model){
		model.addAttribute("existItems","N");
		model.addAttribute("region","EMEA");
		return "ItemWFValidation";
	}
	
	@RequestMapping(path={"/APAC"},method=RequestMethod.GET)
	public String indexMDMAPAC(Model model){
		model.addAttribute("existItems","N");
		model.addAttribute("region","APAC");
		return "ItemWFValidation";
	}	
	
	
	@RequestMapping(path={"/ValidateItems"},method=RequestMethod.POST)
	public String validateItems (@ModelAttribute("items") String items, @ModelAttribute("region") String region, Model model){
		String[] inputItems = items.split(",");
		System.out.println(region);
		List<String> existingItems = new ArrayList<String>();
		List<String> notExistingItems = new ArrayList<String>();
		List<Item> listItems = new ArrayList<Item>();
		if(region.equals("LACG")){
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
		}else{
			if(region.equals("EMEA")){
				for (int i = 0; i < inputItems.length; i++) {
					if(itemDAOEMEA.itemExist(inputItems[i].trim()))
						existingItems.add(inputItems[i].trim());
					else
						notExistingItems.add(inputItems[i].trim());
				}
				if(!existingItems.isEmpty()){
					model.addAttribute("existItems","S");
					listItems = itemDAOEMEA.listItems(existingItems); 
				}else{
					model.addAttribute("existItems","N");
				}	
			}else{
				for (int i = 0; i < inputItems.length; i++) {
					if(itemDAOAPAC.itemExist(inputItems[i].trim()))
						existingItems.add(inputItems[i].trim());
					else
						notExistingItems.add(inputItems[i].trim());
				}
				if(!existingItems.isEmpty()){
					model.addAttribute("existItems","S");
					listItems = itemDAOAPAC.listItems(existingItems); 
				}else{
					model.addAttribute("existItems","N");
				}	
			}
		}
		model.addAttribute("listItems",listItems);
		model.addAttribute("error",notExistingItems);
		return "ItemWFValidation";
	}
}
