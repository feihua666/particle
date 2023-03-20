package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 * jdbc数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-17 23:24
 */
@Data
public class DataQueryDatasourceJdbcConfig extends Value {

	private String driverClassName;
	/**
	 * 地址
	 */
	private String url;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;

	public static DataQueryDatasourceJdbcConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceJdbcConfig dataQueryDatasourceJdbcConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceJdbcConfig.class);
		return dataQueryDatasourceJdbcConfig;
	}
}
