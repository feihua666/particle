package com.particle.global.openapi.data;

import com.particle.global.dto.basic.DTO;
import lombok.Data;
import java.util.List;
/**
 * <p>
 * 接口逻辑调用规则配置信息
 * 该信息一般包括在调用接口时提供判断依据
 * </p>
 *
 * @author yangwei
 * @since 2023-08-17 15:38
 */
@Data
public class ApiLogicRuleInfo extends DTO {

	/**
	 * 指定供商配置信息
	 */
	private List<SpecifyProviderConfig> specifyProviderConfigs;
	/**
	 * 可用供商配置信息
	 */
	private List<AvailableProviderConfig> availableProviderConfigs;

	public static ApiLogicRuleInfo create(List<SpecifyProviderConfig> specifyProviderConfigs,
										  List<AvailableProviderConfig> availableProviderConfigs) {
		ApiLogicRuleInfo apiLogicRuleInfo = new ApiLogicRuleInfo();
		apiLogicRuleInfo.specifyProviderConfigs = specifyProviderConfigs;
		apiLogicRuleInfo.availableProviderConfigs = availableProviderConfigs;

		return apiLogicRuleInfo;
	}
	/**
	 * 可用供商配置信息
	 */
	@Data
	public static class AvailableProviderConfig{
		/**
		 * 配置id，唯一
		 */
		private String id;
		/**
		 * 供应商编码
		 */
		private String providerCode;
		/**
		 * 供应商名称
		 */
		private String providerName;
		/**
		 * 供应商api版本
		 */
		private String providerApiVersion;
		/**
		 * 数据滞后判断groovy脚本
		 */
		private String dataLagGroovyScript;

		public static AvailableProviderConfig create(String id,String providerCode,String providerName, String providerApiVersion) {
			AvailableProviderConfig availableProviderConfig = new AvailableProviderConfig();
			availableProviderConfig.id = id;
			availableProviderConfig.providerCode = providerCode;
			availableProviderConfig.providerName = providerName;
			availableProviderConfig.providerApiVersion = providerApiVersion;
			return availableProviderConfig;
		}
	}
	/**
	 * 指定供商配置信息
	 */
	@Data
	public static class SpecifyProviderConfig extends AvailableProviderConfig{
		/**
		 * 是否数据滞后时使用下一个
		 */
		Boolean isWhenDataLagNext;

		/**
		 * 是否数据不存在时使用下一个
		 */
		Boolean isWhenDataNotFoundNext;
		/**
		 * 是否数据存在时使用下一个
		 */
		Boolean isWhenDataExistNext;
		/**
		 * 是否请求异常时使用下一个
		 */
		Boolean isWhenErrorNext;
		/**
		 * 是否将结果入库
		 */
		Boolean isWarehouseResult;
		/**
		 * 是否异步入库
		 */
		Boolean isWarehouseAsync;
	}
}
