package com.particle.config.client.system.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 系统参数配置 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-05-30 10:29:04
 */
@Data
@Schema
public class SystemConfigVO extends AbstractBaseIdVO {

    @Schema(description = "参数配置编码")
    private String code;
    
    @Schema(description = "参数配置名称")
    private String name;
    
    @Schema(description = "参数配置值")
    private String value;
    
    @Schema(description = "是否内置")
    private Boolean isBuiltIn;
    
    @Schema(description = "是否禁用")
    private Boolean isDisabled;
    
    @Schema(description = "禁用原因")
    private String disabledReason;
    
    @Schema(description = "标签")
    private String tag;
    
    @Schema(description = "描述")
    private String remark;
    


}
