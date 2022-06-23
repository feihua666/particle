package com.particle.global.exception.code;

import java.util.Optional;

/**
 * <p>
 * 全局错误编码接口，一般为枚举实现
 * </p>
 *
 * @author yangwei
 * @since 2022-05-12 21:50
 */
public interface IErrorCode {

	/**
	 * 错误状态编码
	 * 规则：总共10位，xxx xxxx xxxxx,一般分为三部分
	 * 前三位,和http状态码保持一致
	 * 400 客户端参数请求错误
	 * 500 系统内部错误
	 * 中四位可根据业务区分
	 * 0000 为全局暂未区分业务
	 * 后4位具体异常编码
	 * 0001 开始
	 * @return
	 */
	long getStatus();

	/**
	 * 错误编码，一般为枚举的名称，英文
	 * @return
	 */
	String getErrCode();

	/**
	 * 错误内容提示信息
	 * @return
	 */
	String getErrMessage();

	/**
	 * 对应httpStatus,一般取 {@link IErrorCode#getStatus()} 的前三位即为http状态码
	 * @return
	 */
	default int getHttpStatus(){
		return Optional.of(getStatus())
				.map(String::valueOf)
				.map(status->status.substring(0,3))
				.map(Integer::valueOf)
				.get();
	}
}
