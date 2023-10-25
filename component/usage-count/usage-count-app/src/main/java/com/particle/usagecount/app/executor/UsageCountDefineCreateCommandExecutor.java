package com.particle.usagecount.app.executor;

import com.particle.usagecount.app.structmapping.UsageCountDefineAppStructMapping;
import com.particle.usagecount.client.dto.command.UsageCountDefineCreateCommand;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;
import com.particle.usagecount.domain.UsageCountDefine;
import com.particle.usagecount.domain.gateway.UsageCountDefineGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 使用次数定义 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:08
 */
@Component
@Validated
public class UsageCountDefineCreateCommandExecutor  extends AbstractBaseExecutor {

	private UsageCountDefineGateway usageCountDefineGateway;

	/**
	 * 执行使用次数定义添加指令
	 * @param usageCountDefineCreateCommand
	 * @return
	 */
	public SingleResponse<UsageCountDefineVO> execute(@Valid UsageCountDefineCreateCommand usageCountDefineCreateCommand) {
		UsageCountDefine usageCountDefine = createByUsageCountDefineCreateCommand(usageCountDefineCreateCommand);
		usageCountDefine.setAddControl(usageCountDefineCreateCommand);
		boolean save = usageCountDefineGateway.save(usageCountDefine);
		if (save) {
			return SingleResponse.of(UsageCountDefineAppStructMapping.instance.toUsageCountDefineVO(usageCountDefine));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据使用次数定义创建指令创建使用次数定义模型
	 * @param usageCountDefineCreateCommand
	 * @return
	 */
	private UsageCountDefine createByUsageCountDefineCreateCommand(UsageCountDefineCreateCommand usageCountDefineCreateCommand){
		UsageCountDefine usageCountDefine = UsageCountDefine.create();
		UsageCountDefineCreateCommandToUsageCountDefineMapping.instance.fillUsageCountDefineByUsageCountDefineCreateCommand(usageCountDefine, usageCountDefineCreateCommand);
		return usageCountDefine;
	}

	@Mapper
	interface  UsageCountDefineCreateCommandToUsageCountDefineMapping{
		UsageCountDefineCreateCommandToUsageCountDefineMapping instance = Mappers.getMapper( UsageCountDefineCreateCommandToUsageCountDefineMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param usageCountDefine
		 * @param usageCountDefineCreateCommand
		 */
		void fillUsageCountDefineByUsageCountDefineCreateCommand(@MappingTarget UsageCountDefine usageCountDefine, UsageCountDefineCreateCommand usageCountDefineCreateCommand);
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
