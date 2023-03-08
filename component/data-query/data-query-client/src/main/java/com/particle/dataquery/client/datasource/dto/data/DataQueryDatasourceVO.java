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
 * 数据查询数据源 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Data
@ApiModel
public class DataQueryDatasourceVO extends AbstractBaseIdVO {

    @ApiModelProperty("编码")
    private String code;
    
    @ApiModelProperty("名称")
    private String name;
    
    @ApiModelProperty("类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @ApiModelProperty("类型对应字典名称")
    private String typeDictName;
        
    @ApiModelProperty("json配置")
    private String configJson;
    
    @ApiModelProperty("用户名")
    private String username;
    
    @ApiModelProperty("密码")
    private String password;
    
    @ApiModelProperty("数据查询供应商id")
    private Long dataQueryProviderId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_provider, byFieldName = "dataQueryProviderId", mapValueField = "name")
    @ApiModelProperty("数据查询供应商名称")
    private String dataQueryProviderName;
    
    @ApiModelProperty("描述")
    private String remark;
    


}
