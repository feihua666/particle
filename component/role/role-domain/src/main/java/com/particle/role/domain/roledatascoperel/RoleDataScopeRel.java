package com.particle.role.domain.roledatascoperel;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;
/**
 * <p>
 * 角色数据范围关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Data
@Entity
public class RoleDataScopeRel extends AggreateRoot {

    private RoleDataScopeRelId id;

    /**
    * 角色id
    */
    private Long roleId;

	/**
	 * 数据对象id
	 */
	private Long dataObjectId;

    /**
    * 数据范围id
    */
    private Long dataScopeId;



    /**
     * 创建角色数据范围关系领域模型对象
     * @return 角色数据范围关系领域模型对象，该对应所有属性为空，需要进行初始化操作
     */
    public static RoleDataScopeRel create(){
        return DomainFactory.create(RoleDataScopeRel.class);
    }
}