package com.report.teama.middleware;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.report.teama.Interface.CustomResponse;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
public class MyMiddleware implements Filter {
	
    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        response.setContentType("application/json");
        CustomResponse customResponse = new CustomResponse(1000, "Report created successfully!", null);
        
        ObjectMapper objectMapper = new ObjectMapper();
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        
        ArrayList<String> paths = new ArrayList<String>(Arrays.asList("/api/reports", "/api/reports", "/api/reports", "/api/reports", "/api/reports"));
        
        String headerValue = httpRequest.getHeader("Authorization");  
        
        
        httpResponse.setHeader("Authorization", "abc");
//        String headerValue = "abc";  
        
        if(paths.contains(requestURI) == true) {
        	if ( headerValue.contentEquals("abc") == false  ) {
            	customResponse.setStatusCode(2000);
            	customResponse.setMessage("Invalid token/Token exprire. Please Login again.");
            	customResponse.setData(requestURI);
            	String json = objectMapper.writeValueAsString(customResponse);
                ResponseEntity<String> responseEntity = new ResponseEntity<>(json, HttpStatus.UNAUTHORIZED);
                httpResponse.getWriter().write(responseEntity.getBody());
           }
    	   else {
          	 chain.doFilter(request, httpResponse);
          }
        }
        else {
       	 chain.doFilter(request, httpResponse);
       }
        
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Khởi tạo Filter (nếu cần)
    }

    @Override
    public void destroy() {
        // Dọn dẹp khi Filter bị hủy (nếu cần)
    }
}
