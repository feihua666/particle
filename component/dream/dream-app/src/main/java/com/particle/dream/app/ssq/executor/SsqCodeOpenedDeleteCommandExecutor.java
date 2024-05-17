package com.particle.dream.app.ssq.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.dream.app.ssq.structmapping.SsqCodeOpenedAppStructMapping;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.dream.domain.ssq.SsqCodeOpened;
import com.particle.dream.domain.ssq.SsqCodeOpenedId;
import com.particle.dream.domain.ssq.gateway.SsqCodeOpenedGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 双色球开奖 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Component
@Validated
public class SsqCodeOpenedDeleteCommandExecutor  extends AbstractBaseExecutor {

	private SsqCodeOpenedGateway ssqCodeOpenedGateway;

	/**
	 * 执行 双色球开奖 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<SsqCodeOpenedVO> execute(@Valid IdCommand deleteCommand) {
		SsqCodeOpenedId ssqCodeOpenedId = SsqCodeOpenedId.of(deleteCommand.getId());
		SsqCodeOpened byId = ssqCodeOpenedGateway.getById(ssqCodeOpenedId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = ssqCodeOpenedGateway.delete(ssqCodeOpenedId);
		if (delete) {
			return SingleResponse.of(SsqCodeOpenedAppStructMapping.instance.toSsqCodeOpenedVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}

	/**
	 * 注入使用set方法
	 * @param ssqCodeOpenedGateway
	 */
	@Autowired
	public void setSsqCodeOpenedGateway(SsqCodeOpenedGateway ssqCodeOpenedGateway) {
		this.ssqCodeOpenedGateway = ssqCodeOpenedGateway;
	}
}
