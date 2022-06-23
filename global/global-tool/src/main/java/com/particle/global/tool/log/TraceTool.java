package com.particle.global.tool.log;

import brave.Tracer;
import com.particle.global.tool.spring.SpringContextHolder;

/**
 * <p>
 * 链路追踪相关工具，当前主要方便获取traceId
 * </p>
 *
 * @author yangwei
 * @since 2021-10-12 10:07
 */
public class TraceTool {

	/**
	 * 获取 tracer 实例
	 * @return
	 */
	public static Tracer getTracer(){
		return SpringContextHolder.getBean(Tracer.class);
	}

	/**
	 * 获取 traceId
	 * @return
	 */
	public static String getTraceId(){
		return getTracer().currentSpan().context().traceIdString();
	}

	/**
	 * 获取 spanId
	 * @return
	 */
	public static String getSpanId(){
		return getTracer().currentSpan().context().spanIdString();
	}
}
