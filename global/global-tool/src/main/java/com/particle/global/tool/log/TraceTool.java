package com.particle.global.tool.log;

import com.particle.global.tool.spring.SpringContextHolder;
import io.micrometer.tracing.ScopedSpan;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 链路追踪相关工具，当前主要方便获取 traceId
 * </p>
 *
 * @author yangwei
 * @since 2021-10-12 10:07
 */
public class TraceTool {

	private static TraceIdGetter traceIdGetter = new MDCTraceIdGetter();
	private static List<TraceStarter> traceStarters = new ArrayList<>();

	/**
	 * 获取 traceId
	 * @return
	 */
	public static String getTraceId(){
		return traceIdGetter.getTraceId();
	}

	/**
	 * 获取 spanId
	 * @return
	 */
	public static String getSpanId(){
		return traceIdGetter.getSpanId();
	}

	/**
	 * 链路追踪开始
	 * 注意需要配合 {@link TraceTool#scopedSpanEnd(Object)} 使用
	 * @param spanName
	 * @return
	 */
	public static Object scopedSpanStart(String spanName) {
		for (TraceStarter traceStarter : traceStarters) {
			if (traceStarter.getClass().isAssignableFrom(MicrometerTracingTraceScopedStarter.class)) {
				return traceStarter.start(spanName);
			}
		}
		return null;
	}

	/**
	 * 链路追踪结束
	 * 注意需要配合 {@link TraceTool#scopedSpanStart(String)} 使用
	 * @param span
	 */
	public static void scopedSpanEnd(Object span) {
		if (span != null && span instanceof ScopedSpan) {
			for (TraceStarter traceStarter : traceStarters) {
				if (traceStarter.getClass().isAssignableFrom(MicrometerTracingTraceScopedStarter.class)) {
					traceStarter.end((ScopedSpan)span);
				}
			}
		}

	}

	@Autowired(required = false)
	public void setTraceIdGetter(TraceIdGetter traceIdGetter) {
		if (traceIdGetter != null) {
			TraceTool.traceIdGetter = traceIdGetter;
		}
	}
	@Autowired(required = false)
	public void setTraceStarters(List<TraceStarter> traceStarters) {
		TraceTool.traceStarters = traceStarters;
	}

	/**
	 * 链路追踪 id 获取接口
	 */
	public static interface TraceIdGetter{
		/**
		 * 获取 traceId
		 * @return
		 */
		String getTraceId();
		/**
		 * 获取 spanId
		 * @return
		 */
		String getSpanId();
	}

	/**
	 * 获取 traceId
	 * 基于 MDC
	 */
	public static class MDCTraceIdGetter implements TraceIdGetter{
		@Override
		public String getTraceId() {
			return MDC.get("traceId");
		}
		@Override
		public String getSpanId() {
			return MDC.get("spanId");
		}
	}

	/**
	 * 获取 traceId
	 * 基于 micrometer tracing
	 */
	public static class MicrometerTracingTraceIdGetter implements TraceIdGetter{

		private MDCTraceIdGetter mdcTraceIdGetter = new MDCTraceIdGetter();

		@Override
		public String getTraceId() {
			Span span = getCurrentSpan();
			// 在线程池中可能是null，这里使用日志的
			if (span == null) {
				return mdcTraceIdGetter.getTraceId();
			}
			return span.context().traceId();
		}
		@Override
		public String getSpanId() {
			Span span = MicrometerTracingTraceTool.getTracer().currentSpan();
			// 在线程池中可能是null，这里使用日志的
			if (span == null) {
				return mdcTraceIdGetter.getSpanId();
			}
			return span.context().spanId();
		}

		private static Span getCurrentSpan(){
			Tracer tracerTemp = MicrometerTracingTraceTool.getTracer();
			if (tracerTemp == null) {
				return null;
			}
			Span span = tracerTemp.currentSpan();
			return span;
		}
	}


	/**
	 * 链路追踪开始 结束 接口
	 * @param <T>
	 */
	public static interface TraceStarter<T>{
		T start(String spanName);

		void end(T span);
	}

	/**
	 * 链路追踪开始 结束 操作封装
	 * 基于 micrometer tracing
	 */
	public static class MicrometerTracingTraceScopedStarter implements TraceStarter<ScopedSpan>{
		@Override
		public ScopedSpan start(String spanName) {
			return MicrometerTracingTraceTool.getTracer().startScopedSpan(spanName);
		}

		@Override
		public void end(ScopedSpan span) {
			span.end();
		}
	}


	/**
	 * 获取 tracer 实例
	 * 基于 micrometer tracing
	 */
	private static class MicrometerTracingTraceTool{
		private static boolean hasInit = false;
		private static Tracer tracer;
		/**
		 * 获取 tracer 实例
		 * @return
		 */
		public static Tracer getTracer(){
			if (!hasInit) {
				if (tracer == null) {
					tracer = SpringContextHolder.getBean(Tracer.class);
				}
				hasInit = true;
			}
			return tracer;
		}
	}
}
