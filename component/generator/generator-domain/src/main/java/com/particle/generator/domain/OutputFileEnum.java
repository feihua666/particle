package com.particle.generator.domain;

/**
 * <p>
 * 自定义输出文件，
 * {@link com.baomidou.mybatisplus.generator.config.OutputFile} 的扩展
 * </p>
 *
 * @author yangwei
 * @since 2022-07-06 16:56
 */
public enum OutputFileEnum {


	/**
	 * adapter
	 */
	adminWebController,
	adminWapController,
	adminMobileController,
	frontWebController,
	frontWapController,
	frontMobileController,
	rpcController,
	/**
	 * feign-client
	 */
	rpcFeignClient,
	/**
	 * app
	 */
	applicationServiceImpl,
	createCommandExecutor,
	updateCommandExecutor,
	deleteCommandExecutor,
	queryCommandExecutor,
	// 实体映射
	appStructMapping,
	/**
	 * client
	 */
	applicationService,
	createCommand,
	updateCommand,
	queryDetailForUpdateCommand,
	queryDetailCommand,
	deleteCommand,
	queryListCommand,
	pageQueryCommand,
	vo,
	/**
	 * domain
	 */
	domainObject,
	gateway,
	idObject,

	/**
	 * infrastructure
	 */
	gatewayImpl,
	infrastructureStructMapping,
	/**
	 * mybatisplus 原始文件
	 * 去掉了controller和other
	 * 用在基础设施层
	 */
	entity,
	service,
	serviceImpl,
	mapper,
	mapperXml,
	/**
	 * 建表语句
	 */
	tableDdl,


}
