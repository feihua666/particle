package com.particle.global.dto.tenant;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 租户上下文
 * </p>
 *
 * @author yangwei
 * @since 2026/1/30 09:21
 */
@Data
public class TenantContext extends DTO {

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 作用域 {@link Scope}
     */
    private String scope;


    /**
     * 创建系统级请求
     * @return
     */
    public static TenantContext createNone() {
        return create(null, Scope.NONE.name());
    }
    /**
     * 创建租户级请求
     * @param tenantId
     * @return
     */
    public static TenantContext create(Long tenantId) {
        return create(tenantId, Scope.TENANT.name());
    }
    /**
     * 创建
     * @param tenantId
     * @param scope
     * @return
     */
    public static TenantContext create(Long tenantId, String scope) {
        TenantContext tenantContext = new TenantContext();
        tenantContext.tenantId = tenantId;
        tenantContext.scope = scope;
        return tenantContext;
    }

    public enum Scope {
        NONE,   // 无租户
        TENANT    // 租户级请求
    }
}
