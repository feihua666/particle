package com.particle.componentadmin.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 组件依赖关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Data
@Entity
public class AdminComponentDependency extends AggreateRoot {

    private AdminComponentDependencyId id;

    /**
    * 源组件id
    */
    private Long componentId;

    /**
    * 依赖组件id
    */
    private Long dependComponentId;

    /**
    * 是否必需：1=必须，0=可选
    */
    private Boolean isRequired;

    /**
    * 备注
    */
    private String remark;



    /**
     * 创建组件依赖关系领域模型对象
     * @return 组件依赖关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AdminComponentDependency create(){
        return DomainFactory.create(AdminComponentDependency.class);
    }
}
