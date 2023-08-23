package com.particle.openplatform.client.provider.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import com.particle.component.light.share.trans.TransConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 开放平台开放接口供应商 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Data
@Schema
public class OpenplatformProviderVO extends AbstractBaseIdVO {

    @Schema(description = "编码")
    private String code;
    
    @Schema(description = "供应商名称")
    private String name;
    
    @Schema(description = "数据查询供应商id")
    private Long dataQueryProviderId;

    @TransBy(type = TransConstants.TRANS_DATAQUERY_PROVIDER_BY_USER_ID,byFieldName = "dataQueryProviderId",mapValueField = "name")
    @Schema(description = "数据查询供应商名称")
    private String dataQueryProviderName;

    @Schema(description = "描述")
    private String remark;
    


}
