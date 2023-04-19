package com.particle.dept.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 部门表
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Data
@TableName("component_dept")
public class DeptDO extends BaseTreeDO {

    /**
    * 部门编码,模糊查询
    */
    private String code;

    /**
    * 部门名称,模糊查询
    */
    private String name;

    /**
    * 类型,字典id
    */
    private Long typeDictId;

    /**
    * 负责人用户id，该id可用来填充审批人
    */
    private Long masterUserId;

    /**
    * 是否虚拟部门
    */
    private Boolean isVirtual;

    /**
    * 是否为公司
    */
    private Boolean isComp;

    /**
    * 描述
    */
    private String remark;


}
