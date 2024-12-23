package com.particle.tenant.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 当前租户 数据通用响应对象
 * 字段名保持和{@link com.particle.global.security.tenant.GrantedTenant} 一致，和用户信息中一致
 * </p>
 *
 * @author yw
 * @since 2023-07-13 15:36:07
 */
@Data
@Schema
public class TenantCurrentVO extends AbstractBaseIdVO {

    @Schema(description = "租户编码")
    private String code;

    @Schema(description = "租户名称")
    private String name;

    @Schema(description = "租户主题")
    private String tenantThemeJson;

    @Schema(description = "租户默认的页面路由")
    private String tenantDefaultRouteJson;

    @Schema(description = "租户logo地址")
    private String tenantLogoJson;

    @Schema(description = "额外配置json")
    private String configJson;


    @Schema(description = "是否正式，1=正式，0=试用")
    private Boolean isFormal;
}
