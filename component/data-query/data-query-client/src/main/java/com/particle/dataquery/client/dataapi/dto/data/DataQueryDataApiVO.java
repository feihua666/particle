package com.particle.dataquery.client.dataapi.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 数据查询数据接口 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Data
@ApiModel
public class DataQueryDataApiVO extends AbstractBaseIdVO {

    @ApiModelProperty("接口地址")
    private String url;
    
    @ApiModelProperty("接口名称")
    private String name;
    
    @ApiModelProperty("数据查询数据源接口id")
    private Long dataQueryDatasourceApiId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_datasource_api, byFieldName = "dataQueryDatasourceApiId", mapValueField = "name")
    @ApiModelProperty("数据查询数据源接口名称")
    private String dataQueryDatasourceApiName;

    @ApiModelProperty("适配类型")
    private Long adaptTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "adaptTypeDictId",mapValueField = "name")
    @ApiModelProperty("适配类型对应字典名称")
    private String adaptTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "adaptTypeDictId",mapValueField = "value")
    @ApiModelProperty("适配类型对应字典值")
    private String adaptTypeDictValue;
    
    @ApiModelProperty("入参类型")
    private Long inParamTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "inParamTypeDictId",mapValueField = "name")
    @ApiModelProperty("入参类型对应字典名称")
    private String inParamTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "inParamTypeDictId",mapValueField = "value")
    @ApiModelProperty("入参类型字典值")
    private String inParamTypeDictValue;
    
    @ApiModelProperty("出参类型")
    private Long outParamTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outParamTypeDictId",mapValueField = "name")
    @ApiModelProperty("出参类型对应字典名称")
    private String outParamTypeDictName;


    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "outParamTypeDictId",mapValueField = "value")
    @ApiModelProperty("出参类型字典值")
    private String outParamTypeDictValue;
    
    @ApiModelProperty("输出类型")
    private Long responseTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseTypeDictId",mapValueField = "name")
    @ApiModelProperty("输出类型对应字典名称")
    private String responseTypeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "responseTypeDictId",mapValueField = "value")
    @ApiModelProperty("输出类型对应字典值")
    private String responseTypeDictValue;

    @ApiModelProperty("描述")
    private String remark;
    


}