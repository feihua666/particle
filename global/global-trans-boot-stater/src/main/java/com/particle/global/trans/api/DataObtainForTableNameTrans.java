package com.particle.global.trans.api;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 获取翻译真实数据
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 11:29
 */
public interface DataObtainForTableNameTrans {

	List<Map<String, Object>> dataObtain(String tableName, String selectColumn, String whereColumn, Collection<Object> keys);
}
