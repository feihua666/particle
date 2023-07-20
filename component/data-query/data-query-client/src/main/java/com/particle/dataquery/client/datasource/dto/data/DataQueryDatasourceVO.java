package com.particle.dataquery.client.datasource.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema
public class DataQueryDatasourceVO extends AbstractBaseIdVO {

    @Schema(description = "编码")
    private String code;
    
    @Schema(description = "名称")
    private String name;
    
    @Schema(description = "类型")
    private Long typeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "name")
    @Schema(description = "类型对应字典名称")
    private String typeDictName;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "typeDictId",mapValueField = "value")
    @Schema(description = "类型对应字典值")
    private String typeDictValue;
        
    @Schema(description = "json配置")
    private String configJson;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "密码")
    private String password;
    
    @Schema(description = "数据查询供应商id")
    private Long dataQueryProviderId;

    @TransBy(tableName = TransTableNameConstants.component_data_query_provider, byFieldName = "dataQueryProviderId", mapValueField = "name")
    @Schema(description = "数据查询供应商名称")
    private String dataQueryProviderName;
    
    @Schema(description = "描述")
    private String remark;
    


}
