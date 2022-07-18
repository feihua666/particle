package com.particle.generator.domain;

/**
 * <p>
 * 表类型
 * </p>
 *
 * @author yangwei
 * @since 2022-07-04 15:17
 */
public enum TableType {
	/**
	 * 树表
	 * 包括parentId{x}的表
	 */
	TREE,
	/**
	 * 关系表
	 * 如：用户角色关系表
	 */
	REL,
	/**
	 * 标准表
	 * 不是树表也不是关系表
	 */
	NORMAL
}
