package org.zerock.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGIN = "login";
	
	
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response,Object handler,
			ModelAndView modelAndView) throws Exception{
		
		HttpSession session = request.getSession();
		
		ModelMap modelMap = modelAndView.getModelMap();
		Object userVO = modelMap.get("userVO");   //확인 한번
		
		if(userVO != null){
			session.setAttribute(LOGIN, userVO);
			response.sendRedirect("/");
		}
		
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,Object handler)throws Exception{
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) != null){
			session.removeAttribute(LOGIN);
		}
		
		return true;
	}
	


}
