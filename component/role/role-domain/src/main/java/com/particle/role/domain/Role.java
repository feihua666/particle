package com.particle.role.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

/**
 * <p>
 * 角色 领域模型
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Data
@Entity
public class Role extends AggreateRoot {

	private RoleId id;
    /**
     * 角色编码,模糊查询
     */
    private String code;
    /**
     * 角色名称,模糊查询
     */
    private String name;
    /**
     * 是否禁用
     */
    private Boolean isDisabled;
    /**
     * 禁用原因
     */
    private String disabledReason;
    /**
     * 描述
     */
    private String remark;
    /**
     * 层级、深度
     */
    private Integer level;
    /**
     * 父级
     */
    private Long parentId;
    /**
     * LEVEL为1的父id
     */
    private Long parentId1;
    /**
     * LEVEL为2的父id
     */
    private Long parentId2;
    /**
     * LEVEL为3的父id
     */
    private Long parentId3;
    /**
     * LEVEL为4的父id
     */
    private Long parentId4;
    /**
     * LEVEL为5的父id
     */
    private Long parentId5;
    /**
     * LEVEL为6的父id
     */
    private Long parentId6;
    /**
     * LEVEL为7的父id
     */
    private Long parentId7;
    /**
     * LEVEL为8的父id
     */
    private Long parentId8;
    /**
     * LEVEL为9的父id
     */
    private Long parentId9;
    /**
     * LEVEL为10的父id
     */
    private Long parentId10;


	/**
	 * 创建角色领域模型对象
	 * @return 角色领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static Role create(){
		return DomainFactory.create(Role.class);
	}
}
