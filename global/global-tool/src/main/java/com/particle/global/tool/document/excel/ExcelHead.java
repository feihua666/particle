package com.particle.global.tool.document.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>
 * 定义一个excel表头注解
 * 主要用来读取或写出 bean 数据时对读取数据的转换和你输出数据的表头
 * </p>
 *
 * @author yangwei
 * @since 2023-06-07 09:15
 */
@Target({ FIELD})
@Retention(RUNTIME)
@Documented
public @interface ExcelHead {
	/**
	 * 表头别名，主要用于读取excel,主要用于 {@link ExcelTool#readBeanAll(java.io.InputStream, java.lang.Class)}
	 * 值可以是excel的列名如：A、B等，也可以是第一行表头的值，如：姓名、性别等
	 * @return
	 */
	String readAlias() default "";

	/**
	 * 表头别名，主要用于写出excel,主要用于 {@link ExcelTool#writeBeanAll(java.io.OutputStream, java.util.List, java.lang.Class, boolean)}
	 * 注意：这是写出方向，值将做为表头行的值
	 * @return
	 */
	String writeAlias() default "";
}
