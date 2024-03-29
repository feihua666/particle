package com.particle.dept.domain.deptuserrel;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 部门用户关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Data
@Entity
public class DeptUserRel extends AggreateRoot {

    private DeptUserRelId id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 部门id
    */
    private Long deptId;



    /**
     * 创建部门用户关系领域模型对象
     * @return 部门用户关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DeptUserRel create(){
        return DomainFactory.create(DeptUserRel.class);
    }
}
