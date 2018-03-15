package com.tanlei.mvc.framework.editor;

import java.lang.reflect.Field;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanlei.mvc.framework.utils.NumberFormatUtils;

/**
 * 用于处理对象
 * 
 * @author tanlei
 *
 */
public class CustomObjectEditor implements CustomEditor<Object> {

	public Object setValue(String parameterNames[], String parameterName, Class<? extends Object> targetClass,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			Object obj = targetClass.newInstance();
			Field[] fields = targetClass.getDeclaredFields();
			if (parameterNames != null && parameterNames.length > 0) {

				Enumeration<String> e = request.getParameterNames();
				while (e.hasMoreElements()) {
					String fileName = (String) e.nextElement();// 调用nextElement方法获得元素
					if (hasField(fields, fileName)) {
						Field f = targetClass.getDeclaredField(fileName);
						if (f != null) {
							String value = (String) request.getParameter(fileName);
							Class clazz = f.getType();// 方法参数类型
							f.setAccessible(true);
							if (clazz == String.class) {
								f.set(obj, value);
							} else {
								Number number = NumberFormatUtils.convertNumberToTargetClass(value, clazz);
								f.set(obj, number);
							}
						}
					}
				}
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean hasField(Field[] fields, String fileName) {
		if (fields == null || fields.length < 1) {
			return false;
		}
		boolean flag = false;
		for (Field f : fields) {
			if (f.getName().equals(fileName)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

}
