package com.tanlei.mvc.framework.handler;

import java.util.Map;

/**
 * 存放所有controller元数据信息
 * @author tanlei
 *
 */
public class HandlerMapping {
	
	/** 
	 * key为url，即Controller的value值和requestMapping的url值的组合
	 * */
	private Map<String, Handler> metaMap;

	public Map<String, Handler> getMetaMap() {
		return metaMap;
	}

	public void setMetaMap(Map<String, Handler> metaMap) {
		this.metaMap = metaMap;
	}
	
}
