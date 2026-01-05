package com.particle.oplog.app.executor;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.oplog.app.structmapping.OpLogAppStructMapping;
import com.particle.oplog.client.dto.command.OpLogAuditDataCreateCommand;
import com.particle.oplog.client.dto.command.OpLogCreateCommand;
import com.particle.oplog.client.dto.data.OpLogVO;
import com.particle.oplog.domain.OpLog;
import com.particle.oplog.domain.OpLogId;
import com.particle.oplog.domain.gateway.OpLogDictGateway;
import com.particle.oplog.domain.gateway.OpLogGateway;
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
 * 操作日志 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-12-27 15:26:51
 */
@Component
@Validated
public class OpLogCreateCommandExecutor extends AbstractBaseExecutor {

	private OpLogGateway opLogGateway;
    private OpLogDictGateway opLogDictGateway;
    private OpLogAuditDataCreateCommandExecutor opLogAuditDataCreateCommandExecutor;
	/**
	 * 执行操作日志添加指令
	 * @param opLogCreateCommand
	 * @return
	 */
	public SingleResponse<OpLogVO> execute(@Valid OpLogCreateCommand opLogCreateCommand) {
		OpLog opLog = createByOpLogCreateCommand(opLogCreateCommand);
        opLog.initForAdd();
		opLog.setAddControl(opLogCreateCommand);
        opLog.changeForceAddIfIdExist();
		boolean save = opLogGateway.save(opLog);
		if (save) {
            List<OpLogAuditDataCreateCommand> auditDataCreateCommandList = opLogCreateCommand.getAuditDataCreateCommandList();
            if (CollectionUtil.isNotEmpty(auditDataCreateCommandList)) {
                for (OpLogAuditDataCreateCommand opLogAuditDataCreateCommand : auditDataCreateCommandList) {
                    if (opLogAuditDataCreateCommand.getOpLogId() == null) {
                        opLogAuditDataCreateCommand.setOpLogId(opLog.getId().getId());
                    }
                    opLogAuditDataCreateCommandExecutor.execute(opLogAuditDataCreateCommand);
                }
            }
            return SingleResponse.of(OpLogAppStructMapping.instance.toOpLogVO(opLog));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据操作日志创建指令创建操作日志模型
	 * @param opLogCreateCommand
	 * @return
	 */
	private OpLog createByOpLogCreateCommand(OpLogCreateCommand opLogCreateCommand){
		OpLog opLog = OpLog.create();
		OpLogCreateCommandToOpLogMapping.instance.fillOpLogByOpLogCreateCommand(opLog, opLogCreateCommand);
		return opLog;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpLogCreateCommandToOpLogMapping{
		OpLogCreateCommandToOpLogMapping instance = Mappers.getMapper( OpLogCreateCommandToOpLogMapping.class );
        default OpLogId map(Long id){
            if (id == null) {
                return null;
            }
            return OpLogId.of(id);
        }
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param opLog
		 * @param opLogCreateCommand
		 */
		void fillOpLogByOpLogCreateCommand(@MappingTarget OpLog opLog, OpLogCreateCommand opLogCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param opLogGateway
	 */
	@Autowired
	public void setOpLogGateway(OpLogGateway opLogGateway) {
		this.opLogGateway = opLogGateway;
	}

    @Autowired
    public void setOpLogDictGateway(OpLogDictGateway opLogDictGateway) {
        this.opLogDictGateway = opLogDictGateway;
    }
    @Autowired
    public void setOpLogAuditDataCreateCommandExecutor(OpLogAuditDataCreateCommandExecutor opLogAuditDataCreateCommandExecutor) {
        this.opLogAuditDataCreateCommandExecutor = opLogAuditDataCreateCommandExecutor;
    }
}
