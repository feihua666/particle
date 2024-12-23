package com.particle.dept.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import lombok.Data;
/**
 * <p>
 * 部门树表
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Data
@TableName("component_dept_tree")
public class DeptTreeDO extends BaseTreeDO {

    /**
    * 部门id
    */
    private Long deptId;

    /**
    * 部门树名称id
    */
    private Long deptTreeNameId;

    /**
    * 描述
    */
    private String remark;


}
