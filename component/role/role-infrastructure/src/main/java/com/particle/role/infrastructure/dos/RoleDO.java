package com.particle.role.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@TableName("component_role")
public class RoleDO extends BaseDO {
    /**
    * 角色编码,模糊查询
    */
    private String code;
    /**
    * 角色名称,模糊查询
    */
    private String name;
    /**
    * 是否禁用
    */
    private Boolean isDisabled;
    /**
    * 禁用原因
    */
    private String disabledReason;
    /**
    * 描述
    */
    private String remark;
    /**
    * 层级、深度
    */
    private Integer level;
    /**
    * 父级
    */
    private Long parentId;
    /**
    * LEVEL为1的父id
    */
    private Long parentId1;
    /**
    * LEVEL为2的父id
    */
    private Long parentId2;
    /**
    * LEVEL为3的父id
    */
    private Long parentId3;
    /**
    * LEVEL为4的父id
    */
    private Long parentId4;
    /**
    * LEVEL为5的父id
    */
    private Long parentId5;
    /**
    * LEVEL为6的父id
    */
    private Long parentId6;
    /**
    * LEVEL为7的父id
    */
    private Long parentId7;
    /**
    * LEVEL为8的父id
    */
    private Long parentId8;
    /**
    * LEVEL为9的父id
    */
    private Long parentId9;
    /**
    * LEVEL为10的父id
    */
    private Long parentId10;

}
