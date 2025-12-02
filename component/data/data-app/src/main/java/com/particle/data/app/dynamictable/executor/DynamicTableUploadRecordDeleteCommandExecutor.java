package com.particle.data.app.dynamictable.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.data.app.dynamictable.structmapping.DynamicTableUploadRecordAppStructMapping;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecord;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecordId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableUploadRecordGateway;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableUploadRecordService;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableUploadRecordDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据表格上传记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Component
@Validated
public class DynamicTableUploadRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableUploadRecordGateway dynamicTableUploadRecordGateway;
	private IDynamicTableUploadRecordService iDynamicTableUploadRecordService;

	/**
	 * 执行 动态数据表格上传记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<DynamicTableUploadRecordVO> execute(@Valid IdCommand deleteCommand) {
		DynamicTableUploadRecordId dynamicTableUploadRecordId = DynamicTableUploadRecordId.of(deleteCommand.getId());
		DynamicTableUploadRecord byId = dynamicTableUploadRecordGateway.getById(dynamicTableUploadRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = dynamicTableUploadRecordGateway.delete(dynamicTableUploadRecordId,deleteCommand);
		if (delete) {
			return SingleResponse.of(DynamicTableUploadRecordAppStructMapping.instance.toDynamicTableUploadRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param dynamicTableUploadRecordGateway
	 */
	@Autowired
	public void setDynamicTableUploadRecordGateway(DynamicTableUploadRecordGateway dynamicTableUploadRecordGateway) {
		this.dynamicTableUploadRecordGateway = dynamicTableUploadRecordGateway;
	}
	@Autowired
	public void setIDynamicTableUploadRecordService(IDynamicTableUploadRecordService iDynamicTableUploadRecordService) {
		this.iDynamicTableUploadRecordService = iDynamicTableUploadRecordService;
	}
}
