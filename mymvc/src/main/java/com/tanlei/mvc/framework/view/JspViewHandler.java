package com.tanlei.mvc.framework.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanlei.mvc.framework.config.WebConfig;

public class JspViewHandler extends AbstractViewHandler {
	
	private static final String REDIRECT = "redirect";
	
	private static final String FORWARD = "forward";

	@Override
	public boolean handleView(Object object, WebConfig webConfig, HttpServletRequest request, HttpServletResponse response) {
		if (object != null) {
			String result = (String) object;
			if (result.contains(FORWARD)) {
				String prefix =  webConfig.getPrefix();
				String location = result.substring(result.lastIndexOf(":") + 1, result.length());
					try {
						request.getRequestDispatcher(prefix + location).forward(request, response);
					} catch (ServletException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
			
			} else if (result.contains(REDIRECT)) {
				String contextPath = request.getContextPath();
				String location = result.substring(result.lastIndexOf(":") + 1, result.length());
				try {
					response.sendRedirect(contextPath + location);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
}
