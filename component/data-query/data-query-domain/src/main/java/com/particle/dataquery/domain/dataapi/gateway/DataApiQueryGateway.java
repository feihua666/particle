package com.particle.dataquery.domain.dataapi.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;

/**
 * <p>
 * 数据接口查询网关，隔离底层逻辑
 * </p>
 *
 * @author yangwei
 * @since 2023-03-21 22:00:28
 */
public interface DataApiQueryGateway extends IGateway {
	/**
	 * 执行查询
	 * @param dataQueryDataApi
	 * @param param
	 * @return
	 */
	Object query(DataQueryDataApi dataQueryDataApi, Object param,String queryString);

	/**
	 * 实时查询
	 * @param dataQueryDataApi
	 * @param param
	 * @return
	 */
	Object queryRealtime(DataQueryDataApi dataQueryDataApi, Object param,String queryString);

	/**
	 * 仅为使用数据源接口提供翻译使用
	 * @param code
	 * @param param
	 * @param queryString
	 * @return
	 */
	public Object doExecuteByDatasourceApiCode(String code, Object param, String queryString);
}
