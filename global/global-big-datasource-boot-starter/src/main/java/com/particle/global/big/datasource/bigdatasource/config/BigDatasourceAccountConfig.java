package com.particle.global.big.datasource.bigdatasource.config;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 大数据源账号配置类
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 13:47
 */
@Data
public class BigDatasourceAccountConfig extends DTO {

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
}
