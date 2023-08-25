package com.app.main.filters;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
	
	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean(){
		FilterRegistrationBean<Filter> f1 = new FilterRegistrationBean<>();
		f1.setFilter(new CustomFilter1());
		f1.setOrder(0);
		return f1;
	}
	
	
	@Bean
	public FilterRegistrationBean<Filter> filterRegistrationBean2(){
		FilterRegistrationBean<Filter> f2 = new FilterRegistrationBean<>();		
		f2.setFilter(new CustomFilter2());
		f2.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return f2;
	} 
}
