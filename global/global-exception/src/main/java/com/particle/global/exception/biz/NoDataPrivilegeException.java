package com.particle.global.exception.biz;

import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.exception.code.IErrorCode;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 无数据权限异常
 * 一般为越界使用功能，但没有数据权限，或数据权限配置不正确
 * </p>
 *
 * @author yangwei
 * @since 2022-05-14 12:18
 */
public class NoDataPrivilegeException extends BizException {
	public static IErrorCode DEFAULT_ERR_CODE = ErrorCodeGlobalEnum.NO_DATA_PRIVILEGE_ERROR;

	public NoDataPrivilegeException() {
		super(DEFAULT_ERR_CODE);
	}

	public NoDataPrivilegeException(int currentVersion, int originVersion) {
		super(DEFAULT_ERR_CODE, wrapData(currentVersion,originVersion));

	}

	private static Map<String, Object> wrapData(int currentVersion, int originVersion) {

		Map<String, Object> data = new HashMap<>(4);
		data.put("currentVersion", currentVersion);
		data.put("originVersion", originVersion);
		return data;
	}

}
