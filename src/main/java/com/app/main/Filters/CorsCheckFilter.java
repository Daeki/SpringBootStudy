package com.app.main.Filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsCheckFilter implements Filter{ //extends OncePerRequestFilter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Cors Filter In");
		HttpServletRequest req = (HttpServletRequest) request;
	    HttpServletResponse res = (HttpServletResponse) response;

	    res.setHeader("Access-Control-Allow-Origin", "*");
	    res.setHeader("Access-Control-Allow-Credentials", "true");
	    res.setHeader("Access-Control-Allow-Methods","*");
	    res.setHeader("Access-Control-Max-Age", "3600");
	    res.setHeader("Access-Control-Allow-Headers",
	            "Origin, X-Requested-With, Content-Type, Accept, Authorization");
	        
	    if("OPTIONS".equalsIgnoreCase(req.getMethod())) {
	        res.setStatus(HttpServletResponse.SC_OK);
	    }
	        
	    chain.doFilter(request, response);
	    System.out.println("Cors Filter out");
		
	}
	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//			System.out.println("Cors Check Filter");
//		// TODO Auto-generated method stub
//	       HttpServletRequest req = (HttpServletRequest) request;
//	        HttpServletResponse res = (HttpServletResponse) response;
//
//	        res.setHeader("Access-Control-Allow-Origin", "*");
//	        res.setHeader("Access-Control-Allow-Credentials", "true");
//	        res.setHeader("Access-Control-Allow-Methods","*");
//	        res.setHeader("Access-Control-Max-Age", "3600");
//	        res.setHeader("Access-Control-Allow-Headers",
//	                "Origin, X-Requested-With, Content-Type, Accept, Authorization");
//
//	        if("OPTIONS".equalsIgnoreCase(req.getMethod())) {
//	        	res.setStatus(HttpServletResponse.SC_OK);
//	        }else {
//	            filterChain.doFilter(request, response);
//	        }
//	        filterChain.doFilter(request, response);
//		// TODO Auto-generated method stub
//		
//	}
	


}
