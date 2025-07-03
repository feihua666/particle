package com.particle.cms.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 模板内容 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Data
@Schema
public class CmsTemplateContentVO extends AbstractBaseIdVO {

    @Schema(description = "数据id")
    private Long dataId;
    
    @Schema(description = "模板类型")
    private String typeName;
    
    @Schema(description = "模板内容")
    private String content;
    


}
