package com.particle.openplatform.app.doc.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocDirAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocDirUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;
import com.particle.openplatform.domain.doc.OpenplatformDocDir;
import com.particle.openplatform.domain.doc.OpenplatformDocDirId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocDirGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放接口目录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformDocDirUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformDocDirGateway openplatformDocDirGateway;

	/**
	 * 执行 开放接口目录 更新指令
	 * @param openplatformDocDirUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocDirVO> execute(@Valid OpenplatformDocDirUpdateCommand openplatformDocDirUpdateCommand) {
		OpenplatformDocDir openplatformDocDir = createByOpenplatformDocDirUpdateCommand(openplatformDocDirUpdateCommand);
		openplatformDocDir.setUpdateControl(openplatformDocDirUpdateCommand);
		boolean save = openplatformDocDirGateway.save(openplatformDocDir);
		if (save) {
			return SingleResponse.of(OpenplatformDocDirAppStructMapping.instance.toOpenplatformDocDirVO(openplatformDocDir));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口目录更新指令创建开放接口目录模型
	 * @param openplatformDocDirUpdateCommand
	 * @return
	 */
	private OpenplatformDocDir createByOpenplatformDocDirUpdateCommand(OpenplatformDocDirUpdateCommand openplatformDocDirUpdateCommand){
		OpenplatformDocDir openplatformDocDir = OpenplatformDocDir.create();
		OpenplatformDocDirUpdateCommandToOpenplatformDocDirMapping.instance.fillOpenplatformDocDirByOpenplatformDocDirUpdateCommand(openplatformDocDir, openplatformDocDirUpdateCommand);
		return openplatformDocDir;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformDocDirUpdateCommandToOpenplatformDocDirMapping{
		OpenplatformDocDirUpdateCommandToOpenplatformDocDirMapping instance = Mappers.getMapper(OpenplatformDocDirUpdateCommandToOpenplatformDocDirMapping.class );

		default OpenplatformDocDirId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformDocDirId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformDocDir
		 * @param openplatformDocDirUpdateCommand
		 */
		void fillOpenplatformDocDirByOpenplatformDocDirUpdateCommand(@MappingTarget OpenplatformDocDir openplatformDocDir, OpenplatformDocDirUpdateCommand openplatformDocDirUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformDocDirGateway
	 */
	@Autowired
	public void setOpenplatformDocDirGateway(OpenplatformDocDirGateway openplatformDocDirGateway) {
		this.openplatformDocDirGateway = openplatformDocDirGateway;
	}
}
