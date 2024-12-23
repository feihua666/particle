package com.particle.usagecount.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.usagecount.app.structmapping.UsageCountRecordDetailAppStructMapping;
import com.particle.usagecount.client.dto.data.UsageCountRecordDetailVO;
import com.particle.usagecount.domain.UsageCountRecordDetail;
import com.particle.usagecount.domain.UsageCountRecordDetailId;
import com.particle.usagecount.domain.gateway.UsageCountRecordDetailGateway;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 使用次数记录明细 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Component
@Validated
public class UsageCountRecordDetailDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UsageCountRecordDetailGateway usageCountRecordDetailGateway;

	/**
	 * 执行 使用次数记录明细 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<UsageCountRecordDetailVO> execute(@Valid IdCommand deleteCommand) {
		UsageCountRecordDetailId usageCountRecordDetailId = UsageCountRecordDetailId.of(deleteCommand.getId());
		UsageCountRecordDetail byId = usageCountRecordDetailGateway.getById(usageCountRecordDetailId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = usageCountRecordDetailGateway.delete(usageCountRecordDetailId,deleteCommand);
		if (delete) {
			return SingleResponse.of(UsageCountRecordDetailAppStructMapping.instance.toUsageCountRecordDetailVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param usageCountRecordDetailGateway
	 */
	@Autowired
	public void setUsageCountRecordDetailGateway(UsageCountRecordDetailGateway usageCountRecordDetailGateway) {
		this.usageCountRecordDetailGateway = usageCountRecordDetailGateway;
	}
}
