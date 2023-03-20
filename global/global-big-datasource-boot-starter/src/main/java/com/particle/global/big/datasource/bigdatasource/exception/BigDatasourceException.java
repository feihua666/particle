package com.particle.global.big.datasource.bigdatasource.exception;

import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.IErrorCode;

import java.util.Map;

/**
 * <p>
 * 大数据源异常
 * </p>
 *
 * @author yangwei
 * @since 2023-03-09 11:43
 */
public class BigDatasourceException extends BizException {
	public BigDatasourceException(String userTip) {
		super(userTip);
	}

	public BigDatasourceException(IErrorCode error, String userTip) {
		super(error, userTip);
	}

	public BigDatasourceException(IErrorCode error, Throwable cause) {
		super(error, cause);
	}

	public BigDatasourceException(IErrorCode error) {
		super(error);
	}

	public BigDatasourceException(IErrorCode error, Map<String, Object> data) {
		super(error, data);
	}

	public BigDatasourceException(IErrorCode error, Map<String, Object> data, Throwable cause) {
		super(error, data, cause);
	}
}
