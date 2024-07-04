package com.particle.role.infrastructure.roledatascoperel.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * <p>
 * 角色数据范围关系表
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Accessors(chain = true)
@Data
@TableName("component_role_data_scope_rel")
public class RoleDataScopeRelDO extends BaseDO {

    /**
    * 角色id
    */
    private Long roleId;

    /**
     * 数据对象id
     */
    private Long dataObjectId;

    /**
    * 数据范围id
    */
    private Long dataScopeId;


}