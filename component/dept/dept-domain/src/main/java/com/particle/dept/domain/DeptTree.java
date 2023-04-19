package com.particle.dept.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 部门树 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Data
@Entity
public class DeptTree extends AggreateRoot {

    private DeptTreeId id;

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

    /**
     * 父级id
     */
    private Long parentId;

    /**
     * 创建部门树领域模型对象
     * @return 部门树领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DeptTree create(){
        return DomainFactory.create(DeptTree.class);
    }
}
