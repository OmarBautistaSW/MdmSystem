package com.sherwin.mdmvalidationsystem.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sherwin.mdmvalidationsystem.model.Item;

public class ItemDAOImp implements ItemDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public ItemDAOImp (DataSource datasource){
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	@Override
	public List <Item> listItems(){
		String sql = "";
		List <Item> listItems = jdbcTemplate.query(sql, new RowMapper<Item>() {
			
			@Override
			public Item mapRow(ResultSet rs, int rowNum) throws SQLException{
				Item item = new Item();
				item.setNumber(rs.getString(0));
				
				return item;
			}
		});
		
		return listItems;
	}
}
