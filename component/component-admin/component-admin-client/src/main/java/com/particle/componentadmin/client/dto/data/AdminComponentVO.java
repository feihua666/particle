package com.particle.componentadmin.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 组件 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Data
@Schema
public class AdminComponentVO extends AbstractBaseIdVO {

    @Schema(description = "组件英文名称")
    private String code;
    
    @Schema(description = "组件中文名称")
    private String name;
    
    @Schema(description = "组件路径")
    private String path;
    
    @Schema(description = "备注")
    private String remark;
    


}
