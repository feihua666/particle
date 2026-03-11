package com.particle.global.big.datasource.bigdatasource.enums;

import com.particle.global.light.share.code.ErrorCodeGlobalStatusMax;
import com.particle.global.light.share.code.IErrorCode;

/**
 * <p>
 * 添加枚举常量 需要同步修改 {@link ErrorCodeGlobalStatusMax} 以记录当前最大值
 * </p>
 *
 * @author yangwei
 * @since 2023-03-09 11:46
 */
public enum BigDatasourceErrorCode implements IErrorCode {

	/**
	 * 大数据源错误
	 */
	BIG_DATASOURCE_ERROR(50000000016L, "大数据源错误");


	BigDatasourceErrorCode(long status, String errMessage) {
		this.status = status;
		this.errCode = this.name();
		this.errMessage = errMessage;
	}
	/**
	 * 状态业务码码
	 */
	private long status;
	/**
	 * 错误代码
	 */
	private String errCode;
	/**
	 * 错误信息描述
	 */
	private String errMessage;

	@Override
	public long getStatus() {
		return status;
	}

	@Override
	public String getErrCode() {
		return errCode;
	}

	@Override
	public String getErrMessage() {
		return errMessage;
	}
}
