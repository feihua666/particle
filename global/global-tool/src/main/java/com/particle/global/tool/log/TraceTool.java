package com.particle.global.tool.log;

import brave.Span;
import brave.Tracer;
import com.particle.global.tool.spring.SpringContextHolder;
import org.slf4j.MDC;

/**
 * <p>
 * 链路追踪相关工具，当前主要方便获取traceId
 * </p>
 *
 * @author yangwei
 * @since 2021-10-12 10:07
 */
public class TraceTool {

	private static Tracer tracer;
	/**
	 * 获取 tracer 实例
	 * @return
	 */
	public static Tracer getTracer(){
        if (tracer == null) {
			tracer = SpringContextHolder.getBean(Tracer.class);
        }
		return tracer;
	}

	/**
	 * 获取 traceId
	 * @return
	 */
	public static String getTraceId(){
		Span span = getTracer().currentSpan();
		// 在线程池中可能是null，这里使用日志的
        if (span == null) {
			return MDC.get("traceId");
		}
		return span.context().traceIdString();
	}

	/**
	 * 获取 spanId
	 * @return
	 */
	public static String getSpanId(){
		Span span = getTracer().currentSpan();
		// 在线程池中可能是null，这里使用日志的
		if (span == null) {
			return MDC.get("spanId");
		}
		return span.context().spanIdString();
	}
}
