package com.tanlei.mvc.framework.config;

public class WebConfig {

	private String suffix;

	private String prefix;

	private String controllerPackageName;

	/**
	 * 设置属性值
	 * 
	 * @param suffix
	 * @param prefix
	 * @param controllerPackageName
	 * @param config
	 * @return
	 */
	public WebConfig setValues(String suffix, String prefix, String controllerPackageName, WebConfig config) {
		config.setSuffix(suffix);
		config.setPrefix(prefix);
		config.setControllerPackageName(controllerPackageName);
		return config;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getControllerPackageName() {
		return controllerPackageName;
	}

	public void setControllerPackageName(String controllerPackageName) {
		this.controllerPackageName = controllerPackageName;
	}

}
