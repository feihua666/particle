package com.particle.usagecount.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.usagecount.app.structmapping.UsageCountDefineAppStructMapping;
import com.particle.usagecount.client.dto.command.UsageCountDefineUpdateCommand;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;
import com.particle.usagecount.domain.UsageCountDefine;
import com.particle.usagecount.domain.UsageCountDefineId;
import com.particle.usagecount.domain.gateway.UsageCountDefineGateway;
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
 * 使用次数定义 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class UsageCountDefineUpdateCommandExecutor  extends AbstractBaseExecutor {

	private UsageCountDefineGateway usageCountDefineGateway;

	/**
	 * 执行 使用次数定义 更新指令
	 * @param usageCountDefineUpdateCommand
	 * @return
	 */
	public SingleResponse<UsageCountDefineVO> execute(@Valid UsageCountDefineUpdateCommand usageCountDefineUpdateCommand) {
		UsageCountDefine usageCountDefine = createByUsageCountDefineUpdateCommand(usageCountDefineUpdateCommand);
		usageCountDefine.setUpdateControl(usageCountDefineUpdateCommand);
		boolean save = usageCountDefineGateway.save(usageCountDefine);
		if (save) {
			return SingleResponse.of(UsageCountDefineAppStructMapping.instance.toUsageCountDefineVO(usageCountDefine));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据使用次数定义更新指令创建使用次数定义模型
	 * @param usageCountDefineUpdateCommand
	 * @return
	 */
	private UsageCountDefine createByUsageCountDefineUpdateCommand(UsageCountDefineUpdateCommand usageCountDefineUpdateCommand){
		UsageCountDefine usageCountDefine = UsageCountDefine.create();
		UsageCountDefineUpdateCommandToUsageCountDefineMapping.instance.fillUsageCountDefineByUsageCountDefineUpdateCommand(usageCountDefine, usageCountDefineUpdateCommand);
		return usageCountDefine;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface UsageCountDefineUpdateCommandToUsageCountDefineMapping{
		UsageCountDefineUpdateCommandToUsageCountDefineMapping instance = Mappers.getMapper(UsageCountDefineUpdateCommandToUsageCountDefineMapping.class );

		default UsageCountDefineId map(Long id){
			if (id == null) {
				return null;
			}
			return UsageCountDefineId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param usageCountDefine
		 * @param usageCountDefineUpdateCommand
		 */
		void fillUsageCountDefineByUsageCountDefineUpdateCommand(@MappingTarget UsageCountDefine usageCountDefine, UsageCountDefineUpdateCommand usageCountDefineUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param usageCountDefineGateway
	 */
	@Autowired
	public void setUsageCountDefineGateway(UsageCountDefineGateway usageCountDefineGateway) {
		this.usageCountDefineGateway = usageCountDefineGateway;
	}
}
