package com.particle.role.infrastructure.rolefuncrel.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 角色菜单功能关系表
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@TableName("component_role_func_rel")
public class RoleFuncRelDO extends BaseDO {
    /**
    * 角色id
    */
    private Long roleId;
    /**
    * 功能id
    */
    private Long funcId;

}
