package com.sherwin.mdmvalidationsystem.utils;

public class MDMValidationUtils {

	public String formatParameter(String items){
		String[] lstItems = items.split(",");
		String parameter = "";
		for (int i = 0; i<lstItems.length;i++){
			if(i+1 == lstItems.length)
				parameter += "'" + lstItems[i] + "'";
			else
				parameter += "'" + lstItems[i] + "',";
		}
		return parameter;		
	}
}
