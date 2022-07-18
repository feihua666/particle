package com.particle.generator.domain;

/**
 * <p>
 * 四层架构适配层类型
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 15:17
 */
public enum AdapterType {
	/**
	 * 移动端 和 {@link com.particle.common.adapter.mobile} 包对应
	 */
	MOBILE,
	/**
	 * wap端，一般指手机小屏网页 和 {@link com.particle.common.adapter.wap} 包对应
	 */
	WAP,
	/**
	 * pc等大屏 和 {@link com.particle.common.adapter.web} 包对应
	 */
	WEB
}
