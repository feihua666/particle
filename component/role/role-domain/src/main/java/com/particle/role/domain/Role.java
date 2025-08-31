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
	 * 是否超级管理员
	 */
	private Boolean isSuperadmin;

	/**
	 * 角色类型，字典id
	 */
	private Long typeDictId;
    /**
     * 排序,默认按该字段升序排序
     */
    private Integer seq;
    /**
     * 描述
     */
    private String remark;

    /**
     * 父级id
     */
    private Long parentId;

	/**
	 * 创建角色领域模型对象
	 * @return 角色领域模型对象，该对应所有属性为空，需要进行初始化操作
	 */
	public static Role create(){
		return DomainFactory.create(Role.class);
	}
}