package com.particle.global.mybatis.plus.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.particle.global.dto.basic.TreeDO;
import lombok.Data;

/**
 * <p>
 * 数据持久树对象基础类
 * </p>
 *
 * @author yangwei
 * @since 2022-06-28 11:44
 */
@Data
public class BaseTreeDO extends BaseDO {


	public static final String PROPERTY_PARENT_ID = TreeDO.PROPERTY_PARENT_ID;
	public static final String COLUMN_PARENT_ID = TreeDO.COLUMN_PARENT_ID;
	public static final String PROPERTY_LEVEL = TreeDO.PROPERTY_LEVEL;
	public static final String COLUMN_LEVEL = TreeDO.COLUMN_LEVEL;
	/**
	 * 初始最顶级深度是 1
	 */
	public static final int INIT_LEVEL = TreeDO.INIT_LEVEL;
	/**
	 * 最大支持深度 11 级
	 */
	public static final Integer MAX_LEVEL = TreeDO.MAX_LEVEL;

	/**
	 * 树深度等级，最顶级是1
	 */
	@TableField(fill = FieldFill.INSERT)
	private Integer level;
	/**
	 * 直接父级id
	 */
	private Long parentId;
	/**
	 * level为1的父级id
	 */
	private Long parentId1;
	/**
	 * level为2的父级id
	 */
	private Long parentId2;
	/**
	 * level为3的父级id
	 */
	private Long parentId3;
	/**
	 * level为4的父级id
	 */
	private Long parentId4;
	/**
	 * level为5的父级id
	 */
	private Long parentId5;
	/**
	 * level为6的父级id
	 */
	private Long parentId6;
	/**
	 * level为7的父级id
	 */
	private Long parentId7;
	/**
	 * level为8的父级id
	 */
	private Long parentId8;
	/**
	 * level为9的父级id
	 */
	private Long parentId9;
	/**
	 * level为10的父级id
	 */
	private Long parentId10;
}
