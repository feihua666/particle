package com.particle.dataquery.domain.dataapi.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiCustomScriptType;
import lombok.Data;

/**
 * <p>
 * 自定义脚本适配
 * </p>
 *
 * @author yangwei
 * @since 2023-03-24 09:12
 */
@Data
public class DataQueryDataApiCustomScriptAdaptConfig {

	/**
	 * 自定义脚本类型
	 */
	private DataQueryDataApiCustomScriptType customScriptType;
	/**
	 * 脚本模板内容
	 */
	private String scriptTemplate;



	public static DataQueryDataApiCustomScriptAdaptConfig createFromJsonStr(String jsonStr) {
		DataQueryDataApiCustomScriptAdaptConfig config = JSONUtil.toBean(jsonStr, DataQueryDataApiCustomScriptAdaptConfig.class);
		return config;
	}
}
