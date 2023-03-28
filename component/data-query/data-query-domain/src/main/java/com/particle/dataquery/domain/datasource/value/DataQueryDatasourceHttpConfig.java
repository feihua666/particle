package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceHttpAuthScriptType;
import lombok.Data;

/**
 * <p>
 * jdbc数据源配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-29 00:37:59
 */
@Data
public class DataQueryDatasourceHttpConfig extends DataQueryDatasourceAccountConfig {


	/**
	 * 域名地址，如：http://example.com
	 */
	private String domainUrl;

	/**
	 * 认证脚本类型
	 */
	private DataQueryDatasourceHttpAuthScriptType authScriptType;
	/**
	 * 认证脚本内容，该脚本用来处理认证逻辑
	 * 一般http接口认证，需要对用户和密码处理一些逻辑，添加到请求头中
	 */
	private String authScriptTemplate;

	public static DataQueryDatasourceHttpConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceHttpConfig config = JSONUtil.toBean(jsonStr, DataQueryDatasourceHttpConfig.class);
		return config;
	}
}
