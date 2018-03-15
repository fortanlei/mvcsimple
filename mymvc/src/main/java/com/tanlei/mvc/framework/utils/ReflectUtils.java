package com.tanlei.mvc.framework.utils;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class ReflectUtils {

	/**
	 * 获取方法参数名称，按给定的参数类型匹配方法
	 * 
	 * @param clazz
	 * @param method
	 * @param paramTypes
	 * @return
	 */
	public static String[] getMethodParamNames(Class<?> clazz, String method, Class<?>... paramTypes) {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = null;
		CtMethod cm = null;
		try {
			cc = pool.get(clazz.getName());

			String[] paramTypeNames = new String[paramTypes.length];
			for (int i = 0; i < paramTypes.length; i++)
				paramTypeNames[i] = paramTypes[i].getName();

			cm = cc.getDeclaredMethod(method, pool.get(paramTypeNames));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return getMethodParamNames(cm);
	}

	protected static String[] getMethodParamNames(CtMethod cm) {
		MethodInfo methodInfo = cm.getMethodInfo();
		CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
		LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
		if (attr == null) {
			return null;
		}

		String[] paramNames = null;
		try {
			paramNames = new String[cm.getParameterTypes().length];
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
		for (int i = 0; i < paramNames.length; i++) {
			paramNames[i] = attr.variableName(i + pos);
		}
		return paramNames;
	}

}
