package com.particle.oplog.client.error.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 操作异常日志内容 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Data
@Schema
public class OpLogErrorContentVO extends AbstractBaseIdVO {

    @Schema(description = "异常日志id")
    private Long opLogErrorId;
    
    @Schema(description = "异常内容")
    private String content;
    


}
