package com.particle.cms.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdTreeVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 模板 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Data
@Schema
public class CmsTemplateVO extends AbstractBaseIdTreeVO {

    @Schema(description = "唯一键")
    private String templateKey;
    
    @Schema(description = "文件名")
    private String name;
    
    @Schema(description = "是否为目录")
    private Boolean isDirectory;
    
    @Schema(description = "模板内容")
    private String content;
    
    @Schema(description = "排序")
    private Integer seq;
    


}
