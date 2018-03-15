package com.tanlei.mvc.framework.scanner;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.tanlei.mvc.framework.annotation.Controller;
import com.tanlei.mvc.framework.annotation.RequestMapping;
import com.tanlei.mvc.framework.handler.HandlerMapping;
import com.tanlei.mvc.framework.handler.Handler;
import com.tanlei.mvc.framework.utils.ClassScannerUtils;
import com.tanlei.mvc.framework.utils.ReflectUtils;

/**
 * 用于扫描指定包下所有的类，生成ControllerMeta信息
 * 
 * @author tanlei
 *
 */
public class PackageScanner {
	
	public static void main(String[] args) {
		PackageScanner.scan("com.test.controller");
	}

	public static HandlerMapping scan(String basePackageName) {
		HandlerMapping handlerMapping = new HandlerMapping();
		Map<String, Handler> metaMap = new HashMap<String, Handler>();
		handlerMapping.setMetaMap(metaMap);

		Set<Class<?>> clazzs = ClassScannerUtils.getClasses(basePackageName);
		for (Class<?> clazz : clazzs) {
			Controller ct = clazz.getAnnotation(Controller.class);// 得到头部Controller注解
			if (ct == null) {
				continue;
			}
			String url = null;
			String headerUrl = ct.url();
			for (Method method : clazz.getDeclaredMethods()) {
				Handler handler = new Handler();
				RequestMapping r = method.getAnnotation(RequestMapping.class);
				String methodUrl = r.url();
				url = headerUrl + methodUrl;
				handler.setMethodName(method.getName());
				handler.setControllerClazz(clazz);
				handler.setMethod(method);
				Class<?> paramTypes[] = method.getParameterTypes();
				handler.setParameterTypes(paramTypes);
				String[] parameterNames = ReflectUtils.getMethodParamNames(clazz, method.getName(), paramTypes);
				handler.setParameterNames(parameterNames);
				metaMap.put(url, handler);
			}
		}
		return handlerMapping;
	}

}
