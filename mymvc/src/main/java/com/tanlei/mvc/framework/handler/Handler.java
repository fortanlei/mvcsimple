package com.tanlei.mvc.framework.handler;

import java.lang.reflect.Method;

public class Handler {

	private Class<?>[] parameterTypes;

	private String methodName;

	private Class<?> controllerClazz;
	
	private Method method;
	
	private String[] parameterNames;

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?> getControllerClazz() {
		return controllerClazz;
	}

	public void setControllerClazz(Class<?> controllerClazz) {
		this.controllerClazz = controllerClazz;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public String[] getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(String[] parameterNames) {
		this.parameterNames = parameterNames;
	}
	
}
