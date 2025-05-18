package com.particle.global.openapi.api.portal;

import com.particle.global.dto.basic.DTO;
import com.particle.global.openapi.enums.FeeRuleDeduplicateType;
import com.particle.global.openapi.enums.FeeRuleFeeType;
import lombok.Data;

/**
 * <p>
 * 开放接口供应商执行器结果包装
 * 该信息一般包括在调用接口时提供计费依据
 * </p>
 *
 * @author yangwei
 * @since 2024-09-27 11:41:50
 */
@Data
public class OpenapiExecuteProviderResult<T> extends DTO {

	/**
	 * 最终结果
	 */
	private T result;
	/**
	 * 是否数据滞后
	 */
	private Boolean isDataLag;
	/**
	 * 是否存在数据
	 */
	private Boolean isDataExist;
	/**
	 * 是否存在错误
	 */
	private Boolean isHasError;
	/**
	 * 是否本地数据
	 */
	private Boolean isLocalData;

	public static <T> OpenapiExecuteProviderResult<T> create(T result,Boolean isDataLag,Boolean isDataExist,Boolean isHasError,Boolean isLocalData){
		OpenapiExecuteProviderResult<T> openapiExecuteProviderResult = new OpenapiExecuteProviderResult<>();
		openapiExecuteProviderResult.setResult(result);
		openapiExecuteProviderResult.setIsDataLag(isDataLag);
		openapiExecuteProviderResult.setIsDataExist(isDataExist);
		openapiExecuteProviderResult.setIsHasError(isHasError);
		openapiExecuteProviderResult.setIsLocalData(isLocalData);
		return openapiExecuteProviderResult;
	}
}
