package com.particle.dataquery.domain.dataapi.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.dataquery.domain.dataapi.DataQueryDataApi;

import java.util.function.Supplier;

/**
 * <p>
 * 远程调用网关，主要是为了兼容开放接口调用，通过数据查询调用远程开放接口
 * </p>
 *
 * @author yangwei
 * @since 2023-08-23 15:59
 */
public interface DataApiRemoteQueryGateway  extends IGateway {

	/**
	 * 是否支持
	 * @param dataQueryDataApi
	 * @param param
	 * @param queryString
	 * @return
	 */
	boolean support(DataQueryDataApi dataQueryDataApi, Object param, String queryString);

	/**
	 * 执行查询
	 * @param dataQueryDataApi
	 * @param param
	 * @param queryString
	 * @param realTimeSupplier 保留原来的逻辑可继承使用
	 * @return
	 */
	Object query(DataQueryDataApi dataQueryDataApi, Object param, String queryString, Supplier realTimeSupplier);
}
