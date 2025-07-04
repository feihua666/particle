package com.particle.global.big.datasource.bigdatasource.api;

import com.particle.global.big.datasource.bigdatasource.api.config.*;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasource;
import com.particle.global.big.datasource.bigdatasource.dynamic.DynamicBigDatasourceRoutingKey;
import com.particle.global.big.datasource.bigdatasource.enums.BigDatasourceApiResponseWrapType;

import java.util.List;

/**
 * <p>
 * 大数据源api请求 api配置信息
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 09:54
 */
public interface BigDatasourceApi {

	/**
	 * api 名称
	 */
	String name();

	/**
	 * api的一个标识，一般是唯一的，用来标识该api是哪一个api
	 * @return
	 */
	String identifier();

	/**
	 * 应该永远不返回null
	 * @return
	 */
	BigDatasourceApiContext apiContext();

	/**
	 * 大数据源接口返回结果包装类型类型
	 * 根据不同的类型可以用来判断执行不同的对应方法，适用于直接调用 execute方法智能判断
	 * @return
	 */
	BigDatasourceApiResponseWrapType responseWrapType();

	/**
	 * 在 {@link BigDatasourceApi#responseWrapType()} = {@link BigDatasourceApiResponseWrapType#page} 时对分页请求参数和响应分页信息时进行处理
	 * @return
	 */
	PageableAdapterConfig pageableAdapterConfig();

	/**
	 * 请求参数扩展配置，主要是使用脚本处理一些请求参数数据
	 * @return
	 */
	BigDatasourceApiCommandExtConfig commandExtConfig();
	/**
	 * 请求参数校验相关配置
	 * @return
	 */
	BigDatasourceApiCommandValidateConfig commandValidateConfig();

	/**
	 * 配置信息
	 * 根据不同的数据源信息配置不同的配置，如：jdbc数据源可配置 sql模板、http数据源可配置请求方法 post还是get等
	 * @return
	 */
	IBigDatasourceApiConfig config();

	/**
	 * 返回结果扩展配置，主要是使用脚本处理一些返回结果数据
	 * @return
	 */
	BigDatasourceApiResultExtConfig resultExtConfig();
	/**
	 * 返回结果成功判断配置
	 * @return
	 */
	BigDatasourceApiSuccessValidateConfig successValidateConfig();

	/**
	 * 翻译配置
	 * @return
	 */
	BigDatasourceApiTransConfig transConfig();
	/**
	 * 接口涉及的字典
	 * 可对响应结果添加翻译支持
	 * @return
	 */
	List<DictGroup> dictGroups();
	/**
	 * 在请求api时，可以指定路由
	 * 仅适用于动态大数据源使用场景，参考 {@link DynamicBigDatasource}
	 * @return
	 */
	default DynamicBigDatasourceRoutingKey routingKey() {
		return null;
	}

	/**
	 * 是否使用缓存，默认不使用
	 * @return
	 */
	default boolean useCache(){
		return false;
	}

	/**
	 * 缓存配置，在开启缓存的情况下，需要配置缓存配置
	 * @return
	 */
	default BigDatasourceApiCacheConfig cacheConfig(){
		return null;
	}

	/**
	 * 连接超时时间，单位毫秒
	 * @return
	 */
	default Integer connectTimeout(){
		return 1000;
	}

	/**
	 * 读取超时时间，单位毫秒
	 * @return
	 */
	default Integer readTimeout(){
		return 3000;
	}
}
