package com.particle.oplog.app.error.executor;

import com.particle.oplog.app.error.structmapping.OpLogErrorContentAppStructMapping;
import com.particle.oplog.client.error.dto.command.OpLogErrorContentCreateCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;
import com.particle.oplog.domain.error.OpLogErrorContent;
import com.particle.oplog.domain.error.gateway.OpLogErrorContentGateway;
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
 * 操作异常日志内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Component
@Validated
public class OpLogErrorContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpLogErrorContentGateway opLogErrorContentGateway;

	/**
	 * 执行操作异常日志内容添加指令
	 * @param opLogErrorContentCreateCommand
	 * @return
	 */
	public SingleResponse<OpLogErrorContentVO> execute(@Valid OpLogErrorContentCreateCommand opLogErrorContentCreateCommand) {
		OpLogErrorContent opLogErrorContent = createByOpLogErrorContentCreateCommand(opLogErrorContentCreateCommand);
		opLogErrorContent.setAddControl(opLogErrorContentCreateCommand);
		boolean save = opLogErrorContentGateway.save(opLogErrorContent);
		if (save) {
			return SingleResponse.of(OpLogErrorContentAppStructMapping.instance.toOpLogErrorContentVO(opLogErrorContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据操作异常日志内容创建指令创建操作异常日志内容模型
	 * @param opLogErrorContentCreateCommand
	 * @return
	 */
	private OpLogErrorContent createByOpLogErrorContentCreateCommand(OpLogErrorContentCreateCommand opLogErrorContentCreateCommand){
		OpLogErrorContent opLogErrorContent = OpLogErrorContent.create();
		OpLogErrorContentCreateCommandToOpLogErrorContentMapping.instance.fillOpLogErrorContentByOpLogErrorContentCreateCommand(opLogErrorContent, opLogErrorContentCreateCommand);
		return opLogErrorContent;
	}

	@Mapper
	interface  OpLogErrorContentCreateCommandToOpLogErrorContentMapping{
		OpLogErrorContentCreateCommandToOpLogErrorContentMapping instance = Mappers.getMapper( OpLogErrorContentCreateCommandToOpLogErrorContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param opLogErrorContent
		 * @param opLogErrorContentCreateCommand
		 */
		void fillOpLogErrorContentByOpLogErrorContentCreateCommand(@MappingTarget OpLogErrorContent opLogErrorContent, OpLogErrorContentCreateCommand opLogErrorContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param opLogErrorContentGateway
	 */
	@Autowired
	public void setOpLogErrorContentGateway(OpLogErrorContentGateway opLogErrorContentGateway) {
		this.opLogErrorContentGateway = opLogErrorContentGateway;
	}
}
