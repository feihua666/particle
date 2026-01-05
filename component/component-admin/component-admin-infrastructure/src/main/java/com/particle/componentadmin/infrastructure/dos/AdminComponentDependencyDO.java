package com.particle.componentadmin.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 组件依赖关系表
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:22:06
 */
@Accessors(chain = true)
@Data
@TableName("component_admin_component_dependency")
public class AdminComponentDependencyDO extends BaseDO {

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


}
