package com.particle.oplog.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oplog.app.structmapping.OpLogAuditDataAppStructMapping;
import com.particle.oplog.client.dto.command.OpLogAuditDataCreateCommand;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;
import com.particle.oplog.domain.OpLogAuditData;
import com.particle.oplog.domain.gateway.OpLogAuditDataGateway;
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
 * 操作日志审计数据 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-12-27 15:26:51
 */
@Component
@Validated
public class OpLogAuditDataCreateCommandExecutor extends AbstractBaseExecutor {

	private OpLogAuditDataGateway opLogAuditDataGateway;

	/**
	 * 执行操作日志审计数据添加指令
	 * @param opLogAuditDataCreateCommand
	 * @return
	 */
	public SingleResponse<OpLogAuditDataVO> execute(@Valid OpLogAuditDataCreateCommand opLogAuditDataCreateCommand) {
		OpLogAuditData opLogAuditData = createByOpLogAuditDataCreateCommand(opLogAuditDataCreateCommand);
        opLogAuditData.initForAdd();
		opLogAuditData.setAddControl(opLogAuditDataCreateCommand);
		boolean save = opLogAuditDataGateway.save(opLogAuditData);
		if (save) {
			return SingleResponse.of(OpLogAuditDataAppStructMapping.instance.toOpLogAuditDataVO(opLogAuditData));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据操作日志审计数据创建指令创建操作日志审计数据模型
	 * @param opLogAuditDataCreateCommand
	 * @return
	 */
	private OpLogAuditData createByOpLogAuditDataCreateCommand(OpLogAuditDataCreateCommand opLogAuditDataCreateCommand){
		OpLogAuditData opLogAuditData = OpLogAuditData.create();
		OpLogAuditDataCreateCommandToOpLogAuditDataMapping.instance.fillOpLogAuditDataByOpLogAuditDataCreateCommand(opLogAuditData, opLogAuditDataCreateCommand);
		return opLogAuditData;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpLogAuditDataCreateCommandToOpLogAuditDataMapping{
		OpLogAuditDataCreateCommandToOpLogAuditDataMapping instance = Mappers.getMapper( OpLogAuditDataCreateCommandToOpLogAuditDataMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param opLogAuditData
		 * @param opLogAuditDataCreateCommand
		 */
		void fillOpLogAuditDataByOpLogAuditDataCreateCommand(@MappingTarget OpLogAuditData opLogAuditData, OpLogAuditDataCreateCommand opLogAuditDataCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param opLogAuditDataGateway
	 */
	@Autowired
	public void setOpLogAuditDataGateway(OpLogAuditDataGateway opLogAuditDataGateway) {
		this.opLogAuditDataGateway = opLogAuditDataGateway;
	}
}
