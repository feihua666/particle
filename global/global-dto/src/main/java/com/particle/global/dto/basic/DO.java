package com.particle.global.dto.basic;

/**
 * <p>
 * 数据库存储对象基类
 * </p>
 *
 * @author yangwei
 * @since 2022-06-27 21:47
 */
public class DO extends DTO{
	private static final long serialVersionUID = 1L;

	public static final String PROPERTY_ID = "id";
	public static final String COLUMN_ID = "id";
	public static final String PROPERTY_CREATE_AT = "createAt";
	public static final String COLUMN_CREATE_AT = "create_at";
	public static final String PROPERTY_CREATE_BY = "createBy";
	public static final String COLUMN_CREATE_BY = "create_by";
	public static final String PROPERTY_UPDATE_AT = "updateAt";
	public static final String COLUMN_UPDATE_AT = "update_at";
	public static final String PROPERTY_UPDATE_BY = "updateBy";
	public static final String COLUMN_UPDATE_BY = "update_by";
	public static final String PROPERTY_VERSION = "version";
	public static final String COLUMN_VERSION = "version";
	public static final String PROPERTY_TENANT_ID = "tenantId";
	public static final String COLUMN_TENANT_ID = "tenant_id";
	// 初始化版本
	public static final int INIT_VERSION = 1;
}
