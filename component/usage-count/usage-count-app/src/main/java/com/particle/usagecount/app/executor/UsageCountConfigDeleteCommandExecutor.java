package com.particle.usagecount.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.usagecount.app.structmapping.UsageCountConfigAppStructMapping;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;
import com.particle.usagecount.domain.UsageCountConfig;
import com.particle.usagecount.domain.UsageCountConfigId;
import com.particle.usagecount.domain.gateway.UsageCountConfigGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 使用次数配置 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Component
@Validated
public class UsageCountConfigDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UsageCountConfigGateway usageCountConfigGateway;

	/**
	 * 执行 使用次数配置 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<UsageCountConfigVO> execute(@Valid IdCommand deleteCommand) {
		UsageCountConfigId usageCountConfigId = UsageCountConfigId.of(deleteCommand.getId());
		UsageCountConfig byId = usageCountConfigGateway.getById(usageCountConfigId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = usageCountConfigGateway.delete(usageCountConfigId);
		if (delete) {
			return SingleResponse.of(UsageCountConfigAppStructMapping.instance.toUsageCountConfigVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param usageCountConfigGateway
	 */
	@Autowired
	public void setUsageCountConfigGateway(UsageCountConfigGateway usageCountConfigGateway) {
		this.usageCountConfigGateway = usageCountConfigGateway;
	}
}
