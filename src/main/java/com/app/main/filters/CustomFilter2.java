package com.app.main.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
//@WebFilter(urlPatterns = "/*")
//@Order(0)
public class CustomFilter2 implements Filter{//, Ordered {

//	@Override
//	public int getOrder() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("custom filter2 요청");
		
		chain.doFilter(request, response);
		
		System.out.println("custom filter2 응답");
		
	}
	
	

}
