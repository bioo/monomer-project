package com.practice.my.practice.commons.annotation;

import java.lang.annotation.*;

/**
 * 系统操作日志注解
 * 
 * @author FOM
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnno {
	String value() default "";
}
