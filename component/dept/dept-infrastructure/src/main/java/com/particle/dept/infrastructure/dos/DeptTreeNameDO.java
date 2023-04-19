package com.particle.dept.infrastructure.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 部门树名称表
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Data
@TableName("component_dept_tree_name")
public class DeptTreeNameDO extends BaseDO {

    /**
    * 部门树名称编码,模糊查询
    */
    private String code;

    /**
    * 部门树名称,模糊查询
    */
    private String name;

    /**
    * 描述
    */
    private String remark;


}
