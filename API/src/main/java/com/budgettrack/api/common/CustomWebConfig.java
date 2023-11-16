package com.budgettrack.api.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.budgettrack.api.DTO.RequestMeta;
import com.budgettrack.api.configuration.JwtInterceptor;


@Configuration
public class CustomWebConfig implements WebMvcConfigurer   {

	  @Autowired
	    private JwtInterceptor jwtInterceptor;
	  
		/*
		 * @Bean public JwtInterceptor jwtInterceptor(RequestMeta requestMeta){ return
		 * new JwtInterceptor(requestMeta); }
		 */
	    
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(jwtInterceptor);
	    }

	    @Bean
	    @RequestScope
	    public RequestMeta getRequestMeta(){
	        return new RequestMeta();
	    }

	
}