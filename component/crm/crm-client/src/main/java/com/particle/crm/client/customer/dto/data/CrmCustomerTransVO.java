package com.particle.crm.client.customer.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 客户 翻译结果
 * </p>
 *
 * @author yw
 * @since 2024-05-06 16:25:59
 */
@Accessors(chain = true)
@Data
@Schema
public class CrmCustomerTransVO extends AbstractBaseIdVO {

    @Schema(description = "客户编码")
    private String code;

    @Schema(description = "客户名称")
    private String name;

    @Schema(description = "客户称呼")
    private String appellation;
}
