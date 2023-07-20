package com.particle.dataquery.client.provider.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 数据查询供应商 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Data
@Schema
public class DataQueryProviderVO extends AbstractBaseIdVO {

    @Schema(description = "供应商名称")
    private String name;
    
    @Schema(description = "是否禁用")
    private Boolean isDisabled;

    @Schema(description = "禁用原因")
    private String disabledReason;

    @Schema(description = "姓名")
    private String userName;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "手机号")
    private String mobile;
    
    @Schema(description = "描述")
    private String remark;
    


}
