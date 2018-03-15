package com.tanlei.mvc.framework.editor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomHttpServletResponseEditor implements CustomEditor<HttpServletResponse> {

	public HttpServletResponse setValue(String[] parameterNames, String parameterName,
			Class<? extends HttpServletResponse> targetClass, HttpServletRequest request, HttpServletResponse response) {
		return response;
	}

}
