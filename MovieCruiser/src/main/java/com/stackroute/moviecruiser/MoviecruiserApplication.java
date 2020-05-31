package com.stackroute.moviecruiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.stackroute.moviecruiser.filter.JwtFilter;

@SpringBootApplication
public class MoviecruiserApplication {

	/**
	 * @param args
	 * To Run Main class
	 */
	
	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		
		final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/*");
		return filterRegistrationBean;
		
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MoviecruiserApplication.class, args);
	}

}
