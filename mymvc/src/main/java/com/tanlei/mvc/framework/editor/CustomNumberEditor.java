package com.tanlei.mvc.framework.editor;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanlei.mvc.framework.utils.NumberFormatUtils;

/**
 * 用于处理number数据的类型转换
 * @author tanlei
 *
 */
public class CustomNumberEditor implements CustomEditor<Number>{
	
	public Number setValue(String parameterNames[], String parameterName, Class<? extends Number> targetClass, HttpServletRequest requetst,
			HttpServletResponse response) {
		try {
			String parameterValue = (String) requetst.getParameter(parameterName);
			return NumberFormatUtils.convertNumberToTargetClass(parameterValue, targetClass);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
