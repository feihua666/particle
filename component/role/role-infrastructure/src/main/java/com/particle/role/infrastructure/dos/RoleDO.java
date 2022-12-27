package com.particle.role.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.particle.global.mybatis.plus.dto.BaseTreeDO;
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
public class RoleDO extends BaseTreeDO {
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
     * 排序,默认按该字段升序排序
     */
    private Integer seq;
    /**
    * 描述
    */
    private String remark;

}
