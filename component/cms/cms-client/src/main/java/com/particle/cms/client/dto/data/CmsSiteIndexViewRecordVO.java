package com.particle.cms.client.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
import java.time.LocalDateTime;
/**
 * <p>
 * 站点首页访问记录 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Data
@Schema
public class CmsSiteIndexViewRecordVO extends AbstractBaseIdVO {

    @Schema(description = "站点id")
    private Long cmsSiteId;
    
    @Schema(description = "访问终端设备id")
    private String deviceId;
    
    @Schema(description = "访问终端设备ip")
    private String ip;
    
    @Schema(description = "访问时间")
    private LocalDateTime viewAt;
        


}
