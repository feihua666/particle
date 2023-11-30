package com.particle.dataquery.client.datasource.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 数据查询数据源接口 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Data
@Schema
public class DataQueryDatasourceApiVO extends AbstractBaseIdVO {

    @Schema(description = "编码")
    private String code;
    
    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "分类")
    private Long categoryDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "categoryDictId",mapValueField = "name")
    @Schema(description = "分类对应字典名称")
    private String categoryDictName;
        
    @Schema(description = "数据查询供应商id")
    private Long dataQueryProviderId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_provider, byFieldName = "dataQueryProviderId", mapValueField = "name")
    @Schema(description = "数据查询供应商名称")
    private String dataQueryProviderName;
    
    @Schema(description = "文档链接")
    private String dataQueryProviderDocLinkUrl;
    
    @Schema(description = "数据查询数据源id")
    private Long dataQueryDatasourceId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_datasource, byFieldName = "dataQueryDatasourceId", mapValueField = "name")
    @Schema(description = "数据查询数据源名称")
    private String dataQueryDatasourceName;

    @Schema(description = "入参类型，字典id")
    private Long inParamTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "inParamTypeDictId",mapValueField = "name")
    @Schema(description = "入参类型字典名称")
    private String inParamTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "inParamTypeDictId",mapValueField = "value")
    @Schema(description = "入参类型字典值")
    private String inParamTypeDictValue;

    @Schema(description = "入参示例")
    private String inParamExampleConfigJson;
    
    @Schema(description = "入参测试用例数据")
    private String inParamTestCaseDataConfigJson;
    
    @Schema(description = "入参文档配置json")
    private String inParamDocConfigJson;
    
    @Schema(description = "入参校验配置json")
    private String inParamValidateConfigJson;
    
    @Schema(description = "入参扩展配置json")
    private String inParamExtConfigJson;

    @Schema(description = "出参类型，字典id")
    private Long outParamTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outParamTypeDictId",mapValueField = "name")
    @Schema(description = "出参类型字典名称")
    private String outParamTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outParamTypeDictId",mapValueField = "value")
    @Schema(description = "出参类型字典值")
    private String outParamTypeDictValue;

    @Schema(description = "出参示例")
    private String outParamExampleConfigJson;
    
    @Schema(description = "出参文档配置json")
    private String outParamDocConfigJson;
    
    @Schema(description = "出参成功或失败配置json")
    private String outParamSuccessConfigJson;


    @Schema(description = "出参翻译配置json")
    private String outParamTransConfigJson;

    @Schema(description = "出参扩展配置json")
    private String outParamExtConfigJson;

    @Schema(description = "输出类型")
    private Long responseTypeDictId;


    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseTypeDictId",mapValueField = "name")
    @Schema(description = "输出类型对应字典名称")
    private String responseTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseTypeDictId",mapValueField = "value")
    @Schema(description = "输出类型对应字典值")
    private String responseTypeDictValue;

    @Schema(description = "分页适配信息配置json")
    private String pageableAdapterConfigJson;

        
    @Schema(description = "基础配置json")
    private String configJson;
    
    @Schema(description = "字典配置json")
    private String dictConfigJson;
    
    @Schema(description = "流量控制配置json")
    private String rateLimitControlConfigJson;
    
    @Schema(description = "熔断控制配置json")
    private String circuitBreakerControlConfigJson;
    
    @Schema(description = "读取等待时间")
    private Integer readTimeout;
    
    @Schema(description = "连接等待时间")
    private Integer connectTimeout;

	@Schema(description = "是否使用缓存")
	private Boolean isUseCache;
    
    @Schema(description = "等同标签")
    private String sameTag;
    
    @Schema(description = "描述")
    private String remark;
    


}