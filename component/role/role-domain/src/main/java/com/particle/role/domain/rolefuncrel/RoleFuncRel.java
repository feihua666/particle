package com.particle.role.domain.rolefuncrel;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 角色菜单功能关系 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Entity
public class RoleFuncRel extends AggreateRoot {

	private RoleFuncRelId id;
    /**
     * 角色id
     */
    private Long roleId;
    /**
     * 功能id
     */
    private Long funcId;


	/**
	 * 创建角色菜单功能关系领域模型对象
	 * @return 角色菜单功能关系领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static RoleFuncRel create(){
		return DomainFactory.create(RoleFuncRel.class);
	}
}
