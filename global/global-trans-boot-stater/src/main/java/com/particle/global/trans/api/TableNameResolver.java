package com.particle.global.trans.api;

/**
 * <p>
 * 表名获取，主要是根据tableNameClass获取表名
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 11:07
 */
public interface TableNameResolver {

	String resolve(Class tableClass,String tableName);
}
