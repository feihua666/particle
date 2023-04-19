package com.particle.tenant.domain.tenantfunc;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 租户功能菜单 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Data
@Entity
public class TenantFunc extends AggreateRoot {

    private TenantFuncId id;

    /**
    * 功能id
    */
    private Long funcId;

    /**
    * 名称
    */
    private String name;
    /**
     * 租户id
     */
    private Long tenantId;


    /**
     * 创建租户功能菜单领域模型对象
     * @return 租户功能菜单领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static TenantFunc create(){
        return DomainFactory.create(TenantFunc.class);
    }
}
