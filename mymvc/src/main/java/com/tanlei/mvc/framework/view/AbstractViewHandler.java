package com.tanlei.mvc.framework.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanlei.mvc.framework.config.WebConfig;

public abstract class AbstractViewHandler {

	public abstract boolean handleView(Object object, WebConfig webConfig, HttpServletRequest request, HttpServletResponse response);
}
