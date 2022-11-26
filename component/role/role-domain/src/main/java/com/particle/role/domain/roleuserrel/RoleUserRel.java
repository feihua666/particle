package com.particle.role.domain.roleuserrel;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 角色用户关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Entity
public class RoleUserRel extends AggreateRoot {

	private RoleUserRelId id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 角色id
     */
    private Long roleId;


	/**
	 * 创建角色用户关系领域模型对象
	 * @return 角色用户关系领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static RoleUserRel create(){
		return DomainFactory.create(RoleUserRel.class);
	}
}
