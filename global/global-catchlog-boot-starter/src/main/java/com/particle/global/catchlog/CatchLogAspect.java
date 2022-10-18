package com.particle.global.catchlog;

import cn.hutool.core.util.ClassUtil;
import com.particle.global.exception.BaseException;
import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.system.SystemException;
import com.particle.global.tool.json.JsonTool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 拦截并打印切面
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 18:54
 */
@Data
@Aspect
@Slf4j
@ConfigurationProperties(prefix = "particle.catchlog")
public class CatchLogAspect {

	private boolean handleException;

	/**
	 * {@code @Pointcut} 注解参考 {@url https://blog.csdn.net/zhengchao1991/article/details/53391244}
	 */
	@Pointcut("@within(com.particle.global.catchlog.CatchAndLog) && execution(public * *(..))")
	public void pointcut(){
	}

	@Around(value = "pointcut()")
	public Object around(ProceedingJoinPoint joinPoint ) throws Throwable {
		// 记录处理开始时间
		long startTime =  System.currentTimeMillis();

		// 打印请求日志
		logRequest(joinPoint);

		Object response = null;
		try {
			response = joinPoint.proceed();
		}
		catch (Throwable e){
			if (!handleException) {
				throw e;
			}
			// 异常处理，包装
			response = handleException(joinPoint, e);
		}
		finally {
			logResponse(startTime, response);
		}

		return response ;
	}

	private Object handleException(ProceedingJoinPoint joinPoint, Throwable e) {
		MethodSignature ms = (MethodSignature)joinPoint.getSignature();
		Class returnType = ms.getReturnType();

		if (e instanceof BizException){
			log.warn("biz exception : {}" , e.getMessage());
			//在Debug的时候，对于BizException也打印堆栈
			if(log.isDebugEnabled()){
				log.error(e.getMessage(), e);
			}
			return ResponseHandler.handle(returnType, (BaseException)e);
		}
		if(e instanceof IllegalArgumentException){
			if (e.getMessage().startsWith("No enum constant ")) {
				log.warn("IllegalArgumentException : {}" , e.getMessage());
				//在Debug的时候，对于BizException也打印堆栈
				if(log.isDebugEnabled()){
					log.error(e.getMessage(), e);
				}
				return ResponseHandler.handle(returnType, ErrorCodeGlobalEnum.NO_ENUM_CONSTANT_ERROR,e.getMessage());

			}
		}
		if (e instanceof SystemException){
			log.error("system exception : ");
			log.error(e.getMessage(), e);
			return ResponseHandler.handle(returnType, (BaseException)e);
		}


		log.error("unknown exception :");
		log.error(e.getMessage(), e);

		return ResponseHandler.handle(returnType, ErrorCodeGlobalEnum.UNKNOWN_ERROR,e.getMessage());
	}


	private void logResponse(long startTime, Object response) {
		if(!log.isDebugEnabled()){
			return;
		}
		try{
			long endTime =  System.currentTimeMillis();

			log.debug("response : {}", JsonTool.toJsonStr(response));
			log.debug("cost : {}ms" , (endTime - startTime));
		}
		catch (Exception e){
			//swallow it
			log.error("logResponse error : {}" ,e.getMessage(), e);
		}
	}

	private void logRequest(ProceedingJoinPoint joinPoint) {
		if (!log.isDebugEnabled()) {
			return;
		}
		try {
			log.debug("start processing: ()" , joinPoint.getSignature().toShortString());
			Object[] args = joinPoint.getArgs();
			for (Object arg : args) {
				log.debug("request : {}" , ClassUtil.isSimpleValueType(arg.getClass())? arg:  JsonTool.toJsonStr(arg));
			}
		}
		catch (Exception e){
			//swallow it
			log.error("logReqeust error : {}" ,e.getMessage(), e);
		}
	}

}
