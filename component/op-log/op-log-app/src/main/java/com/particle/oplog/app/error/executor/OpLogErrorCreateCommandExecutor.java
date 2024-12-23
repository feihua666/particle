package com.particle.oplog.app.error.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oplog.app.error.structmapping.OpLogErrorAppStructMapping;
import com.particle.oplog.client.error.dto.command.OpLogErrorContentCreateCommand;
import com.particle.oplog.client.error.dto.command.OpLogErrorCreateCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import com.particle.oplog.domain.error.OpLogError;
import com.particle.oplog.domain.error.gateway.OpLogErrorGateway;
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
 * 操作异常日志 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Component
@Validated
public class OpLogErrorCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpLogErrorGateway opLogErrorGateway;
	@Autowired
	private OpLogErrorContentCreateCommandExecutor opLogErrorContentCreateCommandExecutor;

	/**
	 * 执行操作异常日志添加指令
	 * @param opLogErrorCreateCommand
	 * @return
	 */
	public SingleResponse<OpLogErrorVO> execute(@Valid OpLogErrorCreateCommand opLogErrorCreateCommand) {
		OpLogError opLogError = createByOpLogErrorCreateCommand(opLogErrorCreateCommand);
		opLogError.setAddControl(opLogErrorCreateCommand);

		opLogError.initForAdd();

		boolean save = opLogErrorGateway.save(opLogError);
		if (save) {
			// 添加成功，添加内容
			OpLogErrorContentCreateCommand opLogErrorContentCreateCommand = OpLogErrorContentCreateCommand.create(opLogError.getId().getId(), opLogErrorCreateCommand.getContent());
			opLogErrorContentCreateCommandExecutor.execute(opLogErrorContentCreateCommand);
			return SingleResponse.of(OpLogErrorAppStructMapping.instance.toOpLogErrorVO(opLogError));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据操作异常日志创建指令创建操作异常日志模型
	 * @param opLogErrorCreateCommand
	 * @return
	 */
	private OpLogError createByOpLogErrorCreateCommand(OpLogErrorCreateCommand opLogErrorCreateCommand){
		OpLogError opLogError = OpLogError.create();
		OpLogErrorCreateCommandToOpLogErrorMapping.instance.fillOpLogErrorByOpLogErrorCreateCommand(opLogError, opLogErrorCreateCommand);
		return opLogError;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpLogErrorCreateCommandToOpLogErrorMapping{
		OpLogErrorCreateCommandToOpLogErrorMapping instance = Mappers.getMapper( OpLogErrorCreateCommandToOpLogErrorMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param opLogError
		 * @param opLogErrorCreateCommand
		 */
		void fillOpLogErrorByOpLogErrorCreateCommand(@MappingTarget OpLogError opLogError, OpLogErrorCreateCommand opLogErrorCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param opLogErrorGateway
	 */
	@Autowired
	public void setOpLogErrorGateway(OpLogErrorGateway opLogErrorGateway) {
		this.opLogErrorGateway = opLogErrorGateway;
	}

	@Autowired
	public void setOpLogErrorContentCreateCommandExecutor(OpLogErrorContentCreateCommandExecutor opLogErrorContentCreateCommandExecutor) {
		this.opLogErrorContentCreateCommandExecutor = opLogErrorContentCreateCommandExecutor;
	}
}
