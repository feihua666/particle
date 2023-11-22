package com.particle.global.big.datasource.bigdatasource.exception;

import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.IErrorCode;

import java.util.Map;

/**
 * <p>
 * 大数据源路由，在路由数据源时未找到数据源将抛出异常
 * </p>
 *
 * @author yangwei
 * @since 2023-11-22 14:36:21
 */
public class BigDatasourceNotFoundException extends BigDatasourceException {

	public BigDatasourceNotFoundException(Throwable cause) {
		super(cause);
	}
	public BigDatasourceNotFoundException(String userTip) {
		super(userTip);
	}

	public BigDatasourceNotFoundException(IErrorCode error, String userTip) {
		super(error, userTip);
	}

	public BigDatasourceNotFoundException(IErrorCode error, Throwable cause) {
		super(error, cause);
	}

	public BigDatasourceNotFoundException(IErrorCode error) {
		super(error);
	}

	public BigDatasourceNotFoundException(IErrorCode error, Map<String, Object> data) {
		super(error, data);
	}

	public BigDatasourceNotFoundException(IErrorCode error, Map<String, Object> data, Throwable cause) {
		super(error, data, cause);
	}
}
