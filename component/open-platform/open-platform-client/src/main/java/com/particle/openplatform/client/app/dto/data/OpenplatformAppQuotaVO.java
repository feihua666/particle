package com.particle.openplatform.client.app.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransTableNameConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import com.particle.component.light.share.trans.TransConstants;
/**
 * <p>
 * 开放平台应用额度 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Data
@Schema
public class OpenplatformAppQuotaVO extends AbstractBaseIdVO {

    @Schema(description = "开放平台应用id")
    private Long openplatformAppId;

    @TransBy(tableName = TransTableNameConstants.component_openplatform_app, byFieldName = "openplatformAppId", mapValueField = "name")
    @Schema(description = "开放平台应用名称")
    private String openplatformAppName;

    @Schema(description = "限制方式")
    private Long limitTypeDictId;

    @TransBy(type = TransConstants.TRANS_DICT_BY_ID,byFieldName = "limitTypeDictId",mapValueField = "name")
    @Schema(description = "限制方式对应字典名称")
    private String limitTypeDictName;
        
    @Schema(description = "限制条数")
    private Integer limitCount;
    
    @Schema(description = "限制金额费用")
    private Integer limitFee;
    
    @Schema(description = "描述")
    private String remark;
    


}
