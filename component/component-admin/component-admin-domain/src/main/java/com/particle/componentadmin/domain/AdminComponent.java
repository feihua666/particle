package com.particle.componentadmin.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 组件 领域模型
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Data
@Entity
public class AdminComponent extends AggreateRoot {

    private AdminComponentId id;

    /**
    * 组件英文名称,如：area、user
    */
    private String code;

    /**
    * 组件中文名称，一般对应名称的中文
    */
    private String name;

    /**
    * 组件路径，相对于项目路径如：component/area
    */
    private String path;

    /**
    * 备注
    */
    private String remark;



    /**
     * 创建组件领域模型对象
     * @return 组件领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static AdminComponent create(){
        return DomainFactory.create(AdminComponent.class);
    }
}
