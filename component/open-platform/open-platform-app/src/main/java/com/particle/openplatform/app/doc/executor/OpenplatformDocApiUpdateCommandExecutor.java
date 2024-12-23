package com.particle.openplatform.app.doc.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiVO;
import com.particle.openplatform.domain.doc.OpenplatformDocApi;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.OpenplatformDocApiId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDirRelGateway;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiGateway;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDirRelDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDirRelService;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放接口文档接口 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocApiUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocApiGateway openplatformDocApiGateway;
	private OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway;
	private IOpenplatformDocApiDirRelService openplatformDocApiDirRelService;
	/**
	 * 执行 开放接口文档接口 更新指令
	 * @param openplatformDocApiUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiVO> execute(@Valid OpenplatformDocApiUpdateCommand openplatformDocApiUpdateCommand) {
		OpenplatformDocApi openplatformDocApi = createByOpenplatformDocApiUpdateCommand(openplatformDocApiUpdateCommand);
		openplatformDocApi.setUpdateControl(openplatformDocApiUpdateCommand);
		// 保存之前先查询一下
		OpenplatformDocApi openplatformDocApiInDb = openplatformDocApiGateway.getById(openplatformDocApi.getId());
		boolean save = openplatformDocApiGateway.save(openplatformDocApi);
		if (save) {
			// 添加到目录
			Long openplatformDocDirId = openplatformDocApiUpdateCommand.getOpenplatformDocDirId();
			if (openplatformDocDirId != null) {
				List<OpenplatformDocApiDirRelDO> byOpenplatformDocApiId = openplatformDocApiDirRelService.getByOpenplatformDocApiId(openplatformDocApi.getId().getId());
				if (CollectionUtil.isEmpty(byOpenplatformDocApiId)) {
					openplatformDocApiDirRelGateway.save(OpenplatformDocApiDirRel.create(openplatformDocApi.getId().getId(), openplatformDocDirId));
				}
			}
			return SingleResponse.of(OpenplatformDocApiAppStructMapping.instance.toOpenplatformDocApiVO(openplatformDocApi));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口文档接口更新指令创建开放接口文档接口模型
	 * @param openplatformDocApiUpdateCommand
	 * @return
	 */
	private OpenplatformDocApi createByOpenplatformDocApiUpdateCommand(OpenplatformDocApiUpdateCommand openplatformDocApiUpdateCommand){
		OpenplatformDocApi openplatformDocApi = OpenplatformDocApi.create();
		OpenplatformDocApiUpdateCommandToOpenplatformDocApiMapping.instance.fillOpenplatformDocApiByOpenplatformDocApiUpdateCommand(openplatformDocApi, openplatformDocApiUpdateCommand);
		return openplatformDocApi;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformDocApiUpdateCommandToOpenplatformDocApiMapping{
		OpenplatformDocApiUpdateCommandToOpenplatformDocApiMapping instance = Mappers.getMapper(OpenplatformDocApiUpdateCommandToOpenplatformDocApiMapping.class );

		default OpenplatformDocApiId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocApiId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocApi
		 * @param openplatformDocApiUpdateCommand
		 */
		void fillOpenplatformDocApiByOpenplatformDocApiUpdateCommand(@MappingTarget OpenplatformDocApi openplatformDocApi, OpenplatformDocApiUpdateCommand openplatformDocApiUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocApiGateway
	 */
	@Autowired
	public void setOpenplatformDocApiGateway(OpenplatformDocApiGateway openplatformDocApiGateway) {
		this.openplatformDocApiGateway = openplatformDocApiGateway;
	}
	@Autowired
	public void setOpenplatformDocApiDirRelGateway(OpenplatformDocApiDirRelGateway openplatformDocApiDirRelGateway) {
		this.openplatformDocApiDirRelGateway = openplatformDocApiDirRelGateway;
	}
	@Autowired
	public void setOpenplatformDocApiDirRelService(IOpenplatformDocApiDirRelService openplatformDocApiDirRelService) {
		this.openplatformDocApiDirRelService = openplatformDocApiDirRelService;
	}
}
