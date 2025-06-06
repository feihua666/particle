package com.particle.func.client.funcapplicationfuncrel.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.light.share.trans.anno.TransBy;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 功能应用功能关系 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Data
@Schema
public class FuncApplicationFuncRelVO extends AbstractBaseIdVO {

    @Schema(description = "功能应用id")
    private Long funcApplicationId;

    @TransBy(tableName = TransTableNameConstants.component_func_application, byFieldName = "funcApplicationId", mapValueField = "name")
    @Schema(description = "功能应用名称")
    private String funcApplicationName;

    @Schema(description = "功能id")
    private Long funcId;

    @TransBy(tableName = TransTableNameConstants.component_func, byFieldName = "funcId", mapValueField = "name")
    @Schema(description = "功能名称")
    private String funcName;


}
