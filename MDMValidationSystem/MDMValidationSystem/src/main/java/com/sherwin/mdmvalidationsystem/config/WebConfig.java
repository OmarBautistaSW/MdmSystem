package com.sherwin.mdmvalidationsystem.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.sherwin.mdmvalidationsystem.dao.ItemDAO;
import com.sherwin.mdmvalidationsystem.dao.ItemDAOImp;

@Configuration
@EnableWebMvc
@PropertySource("classpath:conf.properties")
@ComponentScan(basePackages = { "com.sherwin.mdmvalidationsystem.controller" })
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private Environment env;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public InternalResourceViewResolver resolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("database-driver"));
		ds.setUrl(env.getProperty("database-url"));
		ds.setUsername(env.getProperty("database-user"));
		ds.setPassword(env.getProperty("database-password"));
		
		return ds;
	}
	
	@Bean
	public ItemDAO getItemDAO(){
		return new ItemDAOImp(getDataSource());
	}
}
