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
 * 组件表
 * </p>
 *
 * @author yw
 * @since 2025-12-30 13:21:31
 */
@Accessors(chain = true)
@Data
@TableName("component_admin_component")
public class AdminComponentDO extends BaseDO {

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


}
