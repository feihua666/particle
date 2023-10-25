package com.particle.usagecount.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.usagecount.app.structmapping.UsageCountDefineAppStructMapping;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;
import com.particle.usagecount.domain.UsageCountDefine;
import com.particle.usagecount.domain.UsageCountDefineId;
import com.particle.usagecount.domain.gateway.UsageCountDefineGateway;
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
public class UsageCountDefineDeleteCommandExecutor  extends AbstractBaseExecutor {

	private UsageCountDefineGateway usageCountDefineGateway;

	/**
	 * 执行 使用次数定义 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<UsageCountDefineVO> execute(@Valid IdCommand deleteCommand) {
		UsageCountDefineId usageCountDefineId = UsageCountDefineId.of(deleteCommand.getId());
		UsageCountDefine byId = usageCountDefineGateway.getById(usageCountDefineId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = usageCountDefineGateway.delete(usageCountDefineId);
		if (delete) {
			return SingleResponse.of(UsageCountDefineAppStructMapping.instance.toUsageCountDefineVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
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
