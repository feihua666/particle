package com.particle.dream.app.ssq.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dream.domain.ssq.gateway.SsqCodeGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 双色球号码 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class SsqCodeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private SsqCodeGateway ssqCodeGateway;

	/**
	 * 注入使用set方法
	 * @param ssqCodeGateway
	 */
	@Autowired
	public void setSsqCodeGateway(SsqCodeGateway ssqCodeGateway) {
		this.ssqCodeGateway = ssqCodeGateway;
	}
}
