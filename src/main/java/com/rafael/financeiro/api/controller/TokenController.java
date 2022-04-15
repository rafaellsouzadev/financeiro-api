package com.rafael.financeiro.api.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.financeiro.api.config.property.FinanceiroApiProperty;

@RestController
@RequestMapping("/tokens")
public class TokenController {
	
	@Autowired
	private FinanceiroApiProperty financeiroProperty;
	
	
	//metdo responsovel para invalidar um token ou removelo
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest req, HttpServletResponse resp) {
		
		Cookie cookie = new Cookie("refreshToken", null);
		
		cookie.setHttpOnly(true);
		cookie.setSecure(financeiroProperty.getSeguranca().isEnableHttps()); 
		cookie.setPath(req.getContextPath() + "/oauth/token");
		cookie.setMaxAge(0);
		
		resp.addCookie(cookie);
		resp.setStatus(HttpStatus.NO_CONTENT.value());
	}

}
