package com.particle.crm.client.tag.dto.data;

import java.time.LocalDateTime;
import com.particle.common.client.dto.data.AbstractBaseIdVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.particle.global.light.share.trans.anno.TransBy;
/**
 * <p>
 * 客户标签 数据通用响应对象
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Data
@Schema
public class CrmCustomerTagVO extends AbstractBaseIdVO {

    @Schema(description = "标签编码")
    private String code;
    
    @Schema(description = "标签名称")
    private String name;
    
    @Schema(description = "备注")
    private String remark;
    


}
