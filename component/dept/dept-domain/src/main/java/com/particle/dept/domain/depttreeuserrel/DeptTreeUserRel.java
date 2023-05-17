package com.particle.dept.domain.depttreeuserrel;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 部门树用户关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Data
@Entity
public class DeptTreeUserRel extends AggreateRoot {

    private DeptTreeUserRelId id;

    /**
    * 用户id
    */
    private Long userId;

    /**
    * 部门树id
    */
    private Long deptTreeId;



    /**
     * 创建部门树用户关系领域模型对象
     * @return 部门树用户关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DeptTreeUserRel create(){
        return DomainFactory.create(DeptTreeUserRel.class);
    }
}
