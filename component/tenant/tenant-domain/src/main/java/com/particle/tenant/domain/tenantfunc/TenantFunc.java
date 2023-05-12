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
     * 功能应用id
     */
    private Long funcApplicationId;
    /**
     * 租户id
     */
    private Long tenantId;


    /**
     * 简单填充属性，用于简单添加
     * 当前添加该方法主要是租户申请审批通过后添加租户功能，所以不需要复杂的属性
     * @param funcId
     * @param funcApplicationId
     * @param tenantId
     */
    public void simpleFill(Long funcId, Long funcApplicationId, Long tenantId) {
        this.funcId = funcId;
        this.funcApplicationId = funcApplicationId;
        this.tenantId = tenantId;
    }

    /**
     * 创建租户功能菜单领域模型对象
     * @return 租户功能菜单领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static TenantFunc create(){
        return DomainFactory.create(TenantFunc.class);
    }
}
