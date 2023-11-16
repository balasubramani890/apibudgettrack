package com.budgettrack.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.budgettrack.api.DTO.RequestMeta;
import com.budgettrack.api.util.JwtUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor  {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RequestMeta requestMeta;

    public JwtInterceptor(RequestMeta requestMeta){
    	super();
        this.requestMeta = requestMeta;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String auth = request.getHeader("authorization");

		try {
			if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signup")
					|| request.getRequestURI().contains("forgetpassword"))) {
				System.out.println("Verifing the authorization");
				System.out.println("Token : " +auth);
				Claims claims = jwtUtils.verify(auth);

				requestMeta.setUserName(claims.get("name").toString());
				requestMeta.setUserId(Long.valueOf(claims.getIssuer()));
				requestMeta.setUserType(claims.get("type").toString());
			}
			return true;
		}
		catch(Exception e)
		{
			System.out.println("authorization Failed" +e);
			return false;
		}
		
        
    }
}