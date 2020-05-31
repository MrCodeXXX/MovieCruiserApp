package com.stackroute.moviecruiser.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;

public class JwtFilter extends GenericFilterBean{

	
	
	public JwtFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		final HttpServletResponse res = (HttpServletResponse) response;
		final HttpServletRequest req = (HttpServletRequest) request;
		final String authHeader = req.getHeader("authorization");
		System.out.println("hiii"+authHeader);
		if("OPTIONS".equals(req.getMethod()))
		{
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
		}else if(authHeader == null || !authHeader.startsWith("Bearer ")){
			throw new ServletException("Invalid or missing Header");
			
			
		}else{
	
		final String token = authHeader.substring(7);
		final Claims claims1 = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
		request.setAttribute("claims", claims1);
		chain.doFilter(request, response);
		}
	}

}
