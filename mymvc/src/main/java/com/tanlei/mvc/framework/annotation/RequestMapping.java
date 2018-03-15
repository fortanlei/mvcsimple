package com.tanlei.mvc.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
	
	String url() default ""; // 描述路径,例如： /article/1,则这里写成 url = /1

	String method() default "GET"; // 用于描述请求方式

}
