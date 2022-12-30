package com.particle.role.infrastructure.roleuserrel.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色用户关系表
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Accessors(chain = true)
@Data
@TableName("component_role_user_rel")
public class RoleUserRelDO extends BaseDO {
    /**
    * 用户id
    */
    private Long userId;
    /**
    * 角色id
    */
    private Long roleId;

}
