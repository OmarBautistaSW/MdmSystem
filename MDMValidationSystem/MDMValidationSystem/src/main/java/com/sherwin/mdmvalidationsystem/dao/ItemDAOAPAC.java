package com.sherwin.mdmvalidationsystem.dao;

import java.util.List;

import com.sherwin.mdmvalidationsystem.model.Item;

public interface ItemDAOAPAC {
	public List<Item> listItems(List<String> items);
	public Boolean itemExist(String item);
}
