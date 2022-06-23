package com.particle.global.exception.biz;

import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 数据版本不正确异常
 * 一般为在修改数据时数据已经被修改
 * </p>
 *
 * @author yangwei
 * @since 2022-05-14 12:18
 */
public class InvalidDataVersionException extends BizException {
	public static IErrorCode DEFAULT_ERR_CODE = ErrorCodeGlobalEnum.UPDATE_DATA_VERSION_ERROR;

	public InvalidDataVersionException() {
		super(DEFAULT_ERR_CODE);
	}

	public InvalidDataVersionException(int currentVersion,int originVersion) {
		super(DEFAULT_ERR_CODE, wrapData(currentVersion,originVersion));

	}

	private static Map<String, Object> wrapData(int currentVersion, int originVersion) {

		Map<String, Object> data = new HashMap<>(4);
		data.put("currentVersion", currentVersion);
		data.put("originVersion", originVersion);
		return data;
	}

}
