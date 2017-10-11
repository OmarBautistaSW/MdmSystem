package com.sherwin.mdmvalidationsystem.dao;

import java.util.List;

import com.sherwin.mdmvalidationsystem.model.Item;

public interface ItemDAO {
	
	public List<Item> listItems(String items);

}
