package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiParamValidateType;
import com.particle.global.dto.basic.Value;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateTool;
import com.particle.global.validation.ValidateTool;
import lombok.Data;

import javax.script.ScriptException;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 参数校验配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-19 22:32
 */
@Data
public class DataQueryDatasourceApiInParamValidateConfig extends Value {

	/**
	 * 入参校验项配置
	 */
	private List<ApiValidateItem> inParamValidateItems;

	public static DataQueryDatasourceApiInParamValidateConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiInParamValidateConfig config = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiInParamValidateConfig.class);
		return config;
	}
	@Data
	public static class ApiValidateItem{
		/**
		 * 名称，仅具可读性
		 */
		private String name;
		/**
		 * 类型
		 */
		private DataQueryDatasourceApiParamValidateType type;
		/**
		 * 脚本模板
		 */
		private String contentTemplate;

		private String errorMessage;

		/**
		 * 编译预热
		 * 因为依赖大数据，参考使用：{@link com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiSuccessValidateConfig#initDefaultParamValidators()}
		 * @throws ScriptException
		 */
		public void warmUpLight() throws ScriptException {
			if (type != null && contentTemplate != null) {
				switch (type) {
					case javascript_assert:
						ValidateTool.javascriptAssert(contentTemplate, Collections.emptyMap());
						break;
					case enjoy_template:
						TemplateTool.templateCompile(contentTemplate);
						break;
					case groovy_template:
						GroovyTool.compile(contentTemplate,true);
						break;
					default:
						break;
				}
			}
		}

		public static ApiValidateItem create(String name,DataQueryDatasourceApiParamValidateType type,String contentTemplate,String errorMessage){
			ApiValidateItem validateItem = new ApiValidateItem();
			validateItem.setName(name);
			validateItem.setType(type);
			validateItem.setContentTemplate(contentTemplate);
			validateItem.setErrorMessage(errorMessage);
			return validateItem;
		}
	}

}
