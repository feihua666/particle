package com.particle.openplatform.client.providerrecord.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Data
@Schema
public class OpenplatformProviderRecordParamVO extends AbstractBaseIdVO {

    @Schema(description = "供应商调用记录id")
    private Long openplatformProviderRecordId;
    
    @Schema(description = "请求参数")
    private String requestParam;
    
    @Schema(description = "响应结果")
    private String responseResult;
    


}
