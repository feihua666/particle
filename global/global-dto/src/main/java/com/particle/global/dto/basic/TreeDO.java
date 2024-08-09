package com.particle.global.dto.basic;

/**
 * <p>
 * 数据库存储对象基类,树结构
 * </p>
 *
 * @author yangwei
 * @since 2022-06-27 21:47
 */
public class TreeDO extends DO{
	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_PARENT_ID = "parentId";
	public static final String COLUMN_PARENT_ID = "parent_id";
	public static final String PROPERTY_LEVEL = "level";
	public static final String COLUMN_LEVEL = "level";
	public static final String PROPERTY_SEQ = "seq";
	public static final String COLUMN_SEQ = "seq";
	/**
	 * 初始最顶级深度是 1
	 */
	public static final int INIT_LEVEL = 1;
	/**
	 * 最大支持深度 11 级
	 */
	public static final Integer MAX_LEVEL = 11;
}
