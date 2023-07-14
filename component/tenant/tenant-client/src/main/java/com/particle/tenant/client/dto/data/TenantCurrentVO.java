package com.particle.tenant.client.dto.data;

import com.particle.common.client.dto.data.AbstractBaseIdVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

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
@ApiModel
public class TenantCurrentVO extends AbstractBaseIdVO {

    @ApiModelProperty("租户编码")
    private String code;
    
    @ApiModelProperty("租户名称")
    private String name;

    @ApiModelProperty("租户主题")
    private String tenantThemeJson;
    
    @ApiModelProperty("租户默认的页面路由")
    private String tenantDefaultRouteJson;

    @ApiModelProperty("租户logo地址")
    private String tenantLogoJson;

    @ApiModelProperty("额外配置json")
    private String configJson;

}