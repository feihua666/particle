package com.particle.dept.infrastructure.deptuserrel.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.particle.global.mybatis.plus.dto.BaseDO;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
/**
 * <p>
 * 部门用户关系表
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Data
@TableName("component_dept_user_rel")
public class DeptUserRelDO extends BaseDO {

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 部门id
    */
    private Long deptId;


}
