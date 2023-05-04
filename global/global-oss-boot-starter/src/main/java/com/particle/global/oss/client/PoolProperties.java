package com.particle.global.oss.client;

import lombok.Data;

import static org.apache.commons.pool2.impl.GenericKeyedObjectPoolConfig.DEFAULT_MAX_TOTAL;
import static org.apache.commons.pool2.impl.GenericObjectPoolConfig.DEFAULT_MAX_IDLE;
import static org.apache.commons.pool2.impl.GenericObjectPoolConfig.DEFAULT_MIN_IDLE;

/**
 * <p>
 * 主要用来 ftp 和 sftp 池化配置
 * </p>
 *
 * @author yangwei
 * @since 2023-05-04 11:12
 */
@Data
public class PoolProperties {

	/**
	 * 最大数量
	 */
	private int maxTotal = DEFAULT_MAX_TOTAL;
	/**
	 * 最大空闲数量
	 */
	private int maxIdle = DEFAULT_MAX_IDLE;
	/**
	 * 最小空闲数量
	 */
	private int minIdle = DEFAULT_MIN_IDLE;
}
