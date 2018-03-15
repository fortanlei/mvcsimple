package com.tanlei.mvc.framework.editor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomHttpSessioinEditor implements CustomEditor<HttpSession> {

	public HttpSession setValue(String[] parameterNames, String parameterName, Class<? extends HttpSession> targetClass,
			HttpServletRequest request, HttpServletResponse response) {
		return request.getSession();
	}

}
