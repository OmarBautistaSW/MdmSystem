package com.sherwin.mdmvalidationsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MDMValidationController {

	@RequestMapping(path={"/"},method=RequestMethod.GET)
	public String indexMDM(Model model){
		return "index";
	}
	
	@RequestMapping(path={"/ItemWFValidation"},method=RequestMethod.POST)
	public String itemWFValidation (@ModelAttribute("region") String region,Model model){
		model.addAttribute("region",region.toUpperCase());
		return "ItemWFValidation";
	}
}
