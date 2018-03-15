package com.tanlei.mvc.framework.editor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomHttpServletRequestEditor implements CustomEditor<HttpServletRequest> {

	public HttpServletRequest setValue(String[] parameterNames, String parameterName,
			Class<? extends HttpServletRequest> targetClass, HttpServletRequest request, HttpServletResponse response) {
		return request;
	}

}
