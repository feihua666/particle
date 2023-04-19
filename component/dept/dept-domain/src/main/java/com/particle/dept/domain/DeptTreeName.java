package com.particle.dept.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 部门树名称 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Data
@Entity
public class DeptTreeName extends AggreateRoot {

    private DeptTreeNameId id;

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



    /**
     * 创建部门树名称领域模型对象
     * @return 部门树名称领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static DeptTreeName create(){
        return DomainFactory.create(DeptTreeName.class);
    }
}
