package com.tanlei.mvc.framework.editor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于处理String类型的类型转换
 * @author tanlei
 *
 */
public class CustomStringEditor implements CustomEditor<String> {


	public String setValue(String[] parameterNames, String parameterName, Class<? extends String> targetClass,
			HttpServletRequest request, HttpServletResponse response) {
		String parameterValue = (String) request.getParameter(parameterName);
		return parameterValue;
	}
}
