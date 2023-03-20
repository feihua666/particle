package com.particle.global.big.datasource.bigdatasource.enums;

/**
 * <p>
 * 大数据源接口返回包装类型
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 18:34
 */
public enum BigDatasourceApiResponseWrapType {
	/**
	 * 单条数据
	 * 如果返回多条时会抛出异常
	 * 对应 {@link com.particle.global.dto.response.SingleResponse}
	 */
	single,
	/**
	 * 多条数据
	 * 对应 {@link com.particle.global.dto.response.MultiResponse}
	 */
	multiple,
	/**
	 * 分页数据
	 * 对应 {@link com.particle.global.dto.response.PageResponse}
	 */
	page,
	/**
	 * 仅包装，数据域为原生数据
	 * 对应 {@link com.particle.global.dto.response.RawResponse}
	 */
	raw,
	/**
	 * 仅代理
	 * 没有对应包装，原生返回
	 */
	proxy,
}
