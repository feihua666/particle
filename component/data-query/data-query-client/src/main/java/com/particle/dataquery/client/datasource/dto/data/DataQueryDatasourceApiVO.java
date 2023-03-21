package com.particle.dataquery.client.datasource.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
public class DataQueryDatasourceApiVO extends AbstractBaseIdVO {

    @ApiModelProperty("编码")
    private String code;
    
    @ApiModelProperty("名称")
    private String name;
    
    @ApiModelProperty("分类")
    private Long categoryDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "categoryDictId",mapValueField = "name")
    @ApiModelProperty("分类对应字典名称")
    private String categoryDictName;
        
    @ApiModelProperty("数据查询供应商id")
    private Long dataQueryProviderId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_provider, byFieldName = "dataQueryProviderId", mapValueField = "name")
    @ApiModelProperty("数据查询供应商名称")
    private String dataQueryProviderName;
    
    @ApiModelProperty("文档链接")
    private String dataQueryProviderDocLinkUrl;
    
    @ApiModelProperty("数据查询数据源id")
    private Long dataQueryDatasourceId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_datasource, byFieldName = "dataQueryDatasourceId", mapValueField = "name")
    @ApiModelProperty("数据查询数据源名称")
    private String dataQueryDatasourceName;

    @ApiModelProperty(value = "入参类型，字典id")
    private Long inParamTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "inParamTypeDictId",mapValueField = "name")
    @ApiModelProperty("入参类型字典名称")
    private String inParamTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "inParamTypeDictId",mapValueField = "value")
    @ApiModelProperty("入参类型字典值")
    private String inParamTypeDictValue;

    @ApiModelProperty("入参示例")
    private String inParamExampleConfigJson;
    
    @ApiModelProperty("入参测试用例数据")
    private String inParamTestCaseDataConfigJson;
    
    @ApiModelProperty("入参文档配置json")
    private String inParamDocConfigJson;
    
    @ApiModelProperty("入参校验配置json")
    private String inParamValidateConfigJson;
    
    @ApiModelProperty("入参扩展配置json")
    private String inParamExtConfigJson;

    @ApiModelProperty(value = "出参类型，字典id")
    private Long outParamTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outParamTypeDictId",mapValueField = "name")
    @ApiModelProperty("出参类型字典名称")
    private String outParamTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outParamTypeDictId",mapValueField = "value")
    @ApiModelProperty("出参类型字典值")
    private String outParamTypeDictValue;

    @ApiModelProperty("出参示例")
    private String outParamExampleConfigJson;
    
    @ApiModelProperty("出参文档配置json")
    private String outParamDocConfigJson;
    
    @ApiModelProperty("出参成功或失败配置json")
    private String outParamSuccessConfigJson;


    @ApiModelProperty(value = "出参翻译配置json")
    private String outParamTransConfigJson;

    @ApiModelProperty(value = "出参扩展配置json")
    private String outParamExtConfigJson;

    @ApiModelProperty("输出类型")
    private Long responseTypeDictId;


    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseTypeDictId",mapValueField = "name")
    @ApiModelProperty("输出类型对应字典名称")
    private String responseTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseTypeDictId",mapValueField = "value")
    @ApiModelProperty("输出类型对应字典值")
    private String responseTypeDictValue;

    @ApiModelProperty(value = "分页适配信息配置json")
    private String pageableAdapterConfigJson;

        
    @ApiModelProperty("基础配置json")
    private String configJson;
    
    @ApiModelProperty("字典配置json")
    private String dictConfigJson;
    
    @ApiModelProperty("流量控制配置json")
    private String rateLimitControlConfigJson;
    
    @ApiModelProperty("熔断控制配置json")
    private String circuitBreakerControlConfigJson;
    
    @ApiModelProperty("读取等待时间")
    private Integer readTimeout;
    
    @ApiModelProperty("连接等待时间")
    private Integer connectTimeout;
    
    @ApiModelProperty("等同标签")
    private String sameTag;
    
    @ApiModelProperty("描述")
    private String remark;
    


}
