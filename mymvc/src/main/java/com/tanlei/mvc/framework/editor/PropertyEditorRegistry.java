package com.tanlei.mvc.framework.editor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PropertyEditorRegistry {

	private static Map<Class<?>, CustomEditor<?>> cacheMap = new HashMap<Class<?>, CustomEditor<?>>();

	static {
		//CustomNumberEditor
		cacheMap.put(byte.class, new CustomNumberEditor());
		cacheMap.put(Byte.class, new CustomNumberEditor());
		cacheMap.put(short.class, new CustomNumberEditor());
		cacheMap.put(Short.class, new CustomNumberEditor());
		cacheMap.put(int.class, new CustomNumberEditor());
		cacheMap.put(Integer.class, new CustomNumberEditor());
		cacheMap.put(long.class, new CustomNumberEditor());
		cacheMap.put(Long.class, new CustomNumberEditor());
		cacheMap.put(float.class, new CustomNumberEditor());
		cacheMap.put(Float.class, new CustomNumberEditor());
		cacheMap.put(double.class, new CustomNumberEditor());
		cacheMap.put(Double.class, new CustomNumberEditor());
		cacheMap.put(BigDecimal.class, new CustomNumberEditor());
		cacheMap.put(BigInteger.class, new CustomNumberEditor());
		//CustomStringEditor
		cacheMap.put(String.class, new CustomStringEditor());
		cacheMap.put(HttpServletRequest.class, new CustomHttpServletRequestEditor());
		cacheMap.put(HttpServletResponse.class, new CustomHttpServletResponseEditor());
		cacheMap.put(HttpSession.class, new CustomHttpSessioinEditor());
	}
	
	/**
	 * 获取对应的类型处理器
	 * @param clazz
	 * @return
	 */
	public static CustomEditor getCustomEditor(Class<?> clazz) {
		if (cacheMap.containsKey(clazz)) {
			return cacheMap.get(clazz);
		} else {
			return new CustomObjectEditor();
		}
	}

}
