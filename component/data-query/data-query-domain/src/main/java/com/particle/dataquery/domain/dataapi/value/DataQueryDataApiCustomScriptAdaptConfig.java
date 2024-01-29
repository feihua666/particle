package com.particle.dataquery.domain.dataapi.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.dataapi.enums.DataQueryDataApiCustomScriptType;
import com.particle.global.tool.script.GroovyTool;
import lombok.Data;

import javax.script.ScriptException;

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


	/**
	 * 编译预热
	 * @throws ScriptException
	 */
	public void warmUpLight() throws ScriptException {
		if (null != customScriptType && null != scriptTemplate) {
			switch (customScriptType) {
				case groovy_script:
					GroovyTool.compile(scriptTemplate,true);
					break;
				default:
					break;
			}
		}
	}

	public static DataQueryDataApiCustomScriptAdaptConfig createFromJsonStr(String jsonStr) {
		DataQueryDataApiCustomScriptAdaptConfig config = JSONUtil.toBean(jsonStr, DataQueryDataApiCustomScriptAdaptConfig.class);
		return config;
	}
}
