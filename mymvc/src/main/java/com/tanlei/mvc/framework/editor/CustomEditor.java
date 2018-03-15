package com.tanlei.mvc.framework.editor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CustomEditor<T> {
	
	public T setValue(String parameterNames[], String parameterName, Class<? extends T> targetClass, HttpServletRequest request,
			HttpServletResponse response);
}
