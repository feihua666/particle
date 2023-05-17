package com.particle.dept.infrastructure.depttreeuserrel.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 部门树用户关系表
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Data
@TableName("component_dept_tree_user_rel")
public class DeptTreeUserRelDO extends BaseDO {

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 部门树id
    */
    private Long deptTreeId;


}
