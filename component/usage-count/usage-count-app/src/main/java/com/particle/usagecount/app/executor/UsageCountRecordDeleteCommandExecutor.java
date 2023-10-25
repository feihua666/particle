package com.particle.usagecount.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.usagecount.app.structmapping.UsageCountRecordAppStructMapping;
import com.particle.usagecount.client.dto.data.UsageCountRecordVO;
import com.particle.usagecount.domain.UsageCountRecord;
import com.particle.usagecount.domain.UsageCountRecordId;
import com.particle.usagecount.domain.gateway.UsageCountRecordGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 使用次数记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Component
@Validated
public class UsageCountRecordDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UsageCountRecordGateway usageCountRecordGateway;

	/**
	 * 执行 使用次数记录 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<UsageCountRecordVO> execute(@Valid IdCommand deleteCommand) {
		UsageCountRecordId usageCountRecordId = UsageCountRecordId.of(deleteCommand.getId());
		UsageCountRecord byId = usageCountRecordGateway.getById(usageCountRecordId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = usageCountRecordGateway.delete(usageCountRecordId);
		if (delete) {
			return SingleResponse.of(UsageCountRecordAppStructMapping.instance.toUsageCountRecordVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param usageCountRecordGateway
	 */
	@Autowired
	public void setUsageCountRecordGateway(UsageCountRecordGateway usageCountRecordGateway) {
		this.usageCountRecordGateway = usageCountRecordGateway;
	}
}
