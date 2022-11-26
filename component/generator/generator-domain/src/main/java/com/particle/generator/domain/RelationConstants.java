package com.particle.generator.domain;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关系
 * </p>
 *
 * @author yangwei
 * @since 2022-07-06 18:07
 */
public class RelationConstants {

	/**
	 * 定义了模块分层和要生成的目标文件依赖关系
	 */
	public static Map<SubModule, List<OutputFileEnum>> subModuleAndOutputFileRelation = new HashMap<>();

	static {
		subModuleAndOutputFileRelation.put(SubModule.ADAPTER, Lists.newArrayList(
				OutputFileEnum.adminWebController,
				OutputFileEnum.adminWapController,
				OutputFileEnum.adminMobileController,
				OutputFileEnum.frontWebController,
				OutputFileEnum.frontWapController,
				OutputFileEnum.frontMobileController,
				OutputFileEnum.rpcController
		));
		subModuleAndOutputFileRelation.put(SubModule.ADAPTER_FEIGN_CLIENT, Lists.newArrayList(
				OutputFileEnum.rpcFeignClient
		));
		subModuleAndOutputFileRelation.put(SubModule.APP, Lists.newArrayList(
				OutputFileEnum.applicationServiceImpl,
				OutputFileEnum.representationApplicationServiceImpl,
				OutputFileEnum.createCommandExecutor,
				OutputFileEnum.updateCommandExecutor,
				OutputFileEnum.deleteCommandExecutor,
				OutputFileEnum.queryCommandExecutor,
				OutputFileEnum.appStructMapping
		));
		subModuleAndOutputFileRelation.put(SubModule.CLIENT, Lists.newArrayList(
				OutputFileEnum.applicationService,
				OutputFileEnum.representationApplicationService,
				OutputFileEnum.createCommand,
				OutputFileEnum.updateCommand,
				OutputFileEnum.queryDetailForUpdateCommand,
				OutputFileEnum.queryDetailCommand,
				OutputFileEnum.deleteCommand,
				OutputFileEnum.queryListCommand,
				OutputFileEnum.pageQueryCommand,
				OutputFileEnum.vo
		));
		subModuleAndOutputFileRelation.put(SubModule.DOMAIN, Lists.newArrayList(
				OutputFileEnum.domainObject,
				OutputFileEnum.gateway,
				OutputFileEnum.idObject
		));
		subModuleAndOutputFileRelation.put(SubModule.INFRASTRUCTURE, Lists.newArrayList(
				OutputFileEnum.gatewayImpl,
				OutputFileEnum.infrastructureStructMapping,
				OutputFileEnum.entity,
				OutputFileEnum.service,
				OutputFileEnum.serviceImpl,
				OutputFileEnum.mapper,
				OutputFileEnum.mapperXml,
				OutputFileEnum.tableDdl
		));
		// 这里暂没有输出文件，占位，保持统一，为避免根据 subModule 获取引起空指针
		subModuleAndOutputFileRelation.put(SubModule.START, Lists.newArrayList(
		));
	}

	/**
	 * 获取 OutputFileEnum 归属的 subModule
	 * @param outputFileEnum
	 * @return
	 */
	public static SubModule ownerSubModule(OutputFileEnum outputFileEnum) {
		for (Map.Entry<SubModule, List<OutputFileEnum>> subModuleListEntry : subModuleAndOutputFileRelation.entrySet()) {
			if (subModuleListEntry.getValue().contains(outputFileEnum)) {
				return subModuleListEntry.getKey();
			}
		}
		return null;
	}

}
