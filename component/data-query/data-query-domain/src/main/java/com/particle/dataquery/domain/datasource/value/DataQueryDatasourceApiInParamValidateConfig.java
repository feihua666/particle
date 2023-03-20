package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiParamValidateType;
import com.particle.global.dto.basic.Value;
import lombok.Data;

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
