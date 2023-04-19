package com.particle.dept.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 部门 领域模型
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Data
@Entity
public class Dept extends AggreateRoot {

    private DeptId id;

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

    /**
     * 父级id
     */
    private Long parentId;


    /**
     * 创建部门领域模型对象
     * @return 部门领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static Dept create(){
        return DomainFactory.create(Dept.class);
    }
}
