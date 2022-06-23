package com.particle.global.catchlog;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.response.Response;
import com.particle.global.exception.BaseException;
import com.particle.global.exception.code.IErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * <p>
 * catchAndLog 返回值处理
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 18:56
 */
@Slf4j
public class ResponseHandler {

	public static Object handle(Class returnType, IErrorCode errorCode){
		if (isResponse(returnType)){
			return handleResponse(returnType, errorCode,null);
		}
		return null;
	}
	public static Object handle(Class returnType, IErrorCode errorCode,String userTip){
		if (isResponse(returnType)){
			return handleResponse(returnType, errorCode,userTip);
		}
		return null;
	}
	public static Object handle(Class returnType, BaseException e){
		return handle(returnType, e.getError());
	}



	private static Object handleResponse(Class returnType, IErrorCode errorCode,String userTip) {
		try {
			Response response = (Response)returnType.newInstance();
			response.setSuccess(false);
			response.setStatus(errorCode.getStatus());
			response.setErrCode(errorCode.getErrCode());
			String handleUserTip = handleUserTip(errorCode, userTip);
			response.setErrMessage(handleUserTip);
			return response;
		}
		catch (Exception ex){
			log.error(ex.getMessage(), ex);
			return  null;
		}
	}


	private static boolean isResponse(Class returnType) {
		return  returnType == Response.class || returnType.getGenericSuperclass() == Response.class;
	}


	private static String handleUserTip(IErrorCode errorCode, String userTip) {
		return Optional.ofNullable(userTip).map(ut -> StrUtil.format(userTip,errorCode.getErrMessage())).orElse(errorCode.getErrMessage());

	}
}
