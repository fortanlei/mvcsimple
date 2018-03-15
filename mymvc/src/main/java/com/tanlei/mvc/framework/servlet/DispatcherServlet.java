package com.tanlei.mvc.framework.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tanlei.mvc.framework.config.WebConfig;
import com.tanlei.mvc.framework.editor.PropertyEditorRegistry;
import com.tanlei.mvc.framework.handler.Handler;
import com.tanlei.mvc.framework.handler.HandlerMapping;
import com.tanlei.mvc.framework.scanner.PackageScanner;
import com.tanlei.mvc.framework.view.AbstractViewHandler;
import com.tanlei.mvc.framework.view.JspViewHandler;

public class DispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = -1494285092123014726L;
	
	private static HandlerMapping hanlderMapping;
	
	private static WebConfig webConfig;
	
	private static AbstractViewHandler viewHandler;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		loadConfig(config);//web-config配置
		
		initHandlerMappings(webConfig.getControllerPackageName());
		
		/** 加载后置视图处理器 */
		try {
			initViewHandler();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 加载配置文件信息
	 * @param string
	 */
	private void loadConfig(ServletConfig config) {
		if (webConfig == null) {
			synchronized (DispatcherServlet.class) {
				if (webConfig == null) {
					webConfig = new WebConfig();
					//获取属性值
					String controllerPackageName = config.getInitParameter("controllerPackageName");
					String suffix = config.getInitParameter("suffix");
					String prefix = config.getInitParameter("prefix");
					webConfig.setValues(suffix, prefix, controllerPackageName, webConfig);//设置属性值
				}
			}
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doDispatch(request, response); //请求处理
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 生成controller的元数据信息
	 * @param packageName
	 */
	private void initHandlerMappings(String packageName) {
		if (hanlderMapping == null) {
			synchronized (DispatcherServlet.class) {
				if (hanlderMapping == null) {
					hanlderMapping = PackageScanner.scan(packageName);
				}
			}
		}
	}
	
	private void initViewHandler() throws Exception {
		if (viewHandler == null) {
			synchronized (DispatcherServlet.class) {
				if (viewHandler == null) {
					if (webConfig == null) { //抛异常，程序顺序不能出现这种问题
						//info
					} else {
						String suffix = webConfig.getSuffix();
						if (suffix == null) {// 抛异常，可能出现这种问题
							throw new Exception("suffix is null");
						} else if (suffix.equals("jsp")) {
							viewHandler = new JspViewHandler();
						} else {//这里要继续完善处理器类
							//先抛个异常
							throw new Exception("suffix is mismatch");
						}
					}
				}
			}
		}
	}
	
	/**
	 * 请求核心处理方法
	 * @param request
	 * @param response
	 */
	private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
		if (hanlderMapping == null) {
			return;
		}
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		url = url.replace(contextPath, "").replaceAll("/+", "/");
		Map<String, Handler> metaMap = hanlderMapping.getMetaMap();
		if (metaMap == null) {
			return;
		}
		if (!metaMap.containsKey(url)) {
			return;
		}
		Handler handler = metaMap.get(url);
		if (handler == null) {
			return;
		}
		Class<?> clazz = handler.getControllerClazz();
		Method method = handler.getMethod();
		String[] parameterNames = handler.getParameterNames();
		Class<?>[] parameterTypes = handler.getParameterTypes();
		Object[] paramObjs = null;
		
		if (parameterNames != null && parameterNames.length > 0) {
			paramObjs = new Object[parameterNames.length];//method的参数值的集合
			for (int i = 0; i < parameterNames.length; i++) {
				Class parameterType = parameterTypes[i];
	            paramObjs[i] = PropertyEditorRegistry.getCustomEditor(parameterType).setValue(parameterNames, parameterNames[i], parameterType, request,
	            		response);
			}
		}
		Object obj = null;
		try {
			obj = clazz.newInstance();
			Object ob = method.invoke(obj, paramObjs);
			System.out.println(ob.toString());
			JspViewHandler viewHandler = new JspViewHandler();
			viewHandler.handleView(ob, webConfig, request, response);
			return;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
	}

}
