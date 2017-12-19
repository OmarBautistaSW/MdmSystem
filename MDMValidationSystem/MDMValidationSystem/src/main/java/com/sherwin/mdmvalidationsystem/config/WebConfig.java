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
import com.sherwin.mdmvalidationsystem.dao.ItemDAOAPAC;
import com.sherwin.mdmvalidationsystem.dao.ItemDAOAPACImp;
import com.sherwin.mdmvalidationsystem.dao.ItemDAOEMEA;
import com.sherwin.mdmvalidationsystem.dao.ItemDAOEMEAImp;
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

	//EMEA
	@Bean
	public DataSource getDataSourceEMEA(){
		DriverManagerDataSource dsemea = new DriverManagerDataSource();
		dsemea.setDriverClassName(env.getProperty("database-driver"));
		dsemea.setUrl(env.getProperty("database-url-emea"));
		dsemea.setUsername(env.getProperty("database-user-emea"));
		dsemea.setPassword(env.getProperty("database-password-emea"));		
		return dsemea;
	}
	
	@Bean
	public ItemDAOEMEA getItemDAOEMEA(){
		return new ItemDAOEMEAImp(getDataSourceEMEA());
	}
	
	//LATAM - BR
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("database-driver"));
		ds.setUrl(env.getProperty("database-url-lacg"));
		ds.setUsername(env.getProperty("database-user-lacg"));
		ds.setPassword(env.getProperty("database-password-lacg"));		
		return ds;
	}
	
	@Bean
	public ItemDAO getItemDAO(){
		return new ItemDAOImp(getDataSource());
	}
	
	
	//APAC
	@Bean
	public DataSource getDataSourceAPAC(){
		DriverManagerDataSource dsapac = new DriverManagerDataSource();
		dsapac.setDriverClassName(env.getProperty("database-driver"));
		dsapac.setUrl(env.getProperty("database-url-apac"));
		dsapac.setUsername(env.getProperty("database-user-apac"));
		dsapac.setPassword(env.getProperty("database-password-apac"));		
		return dsapac;
	}
	
	@Bean
	public ItemDAOAPAC getItemDAOAPAC(){
		return new ItemDAOAPACImp(getDataSourceAPAC());
	}	
}
