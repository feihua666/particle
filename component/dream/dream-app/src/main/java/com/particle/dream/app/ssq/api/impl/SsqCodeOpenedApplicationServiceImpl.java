package com.particle.dream.app.ssq.api.impl;

import com.particle.dream.app.ssq.executor.SsqCodeOpenedCreateCommandExecutor;
import com.particle.dream.app.ssq.executor.SsqCodeOpenedDeleteCommandExecutor;
import com.particle.dream.app.ssq.executor.SsqCodeOpenedUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedUpdateCommand;
import com.particle.dream.client.ssq.api.ISsqCodeOpenedApplicationService;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedCreateCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 双色球开奖 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Transactional
@Service
@CatchAndLog
public class SsqCodeOpenedApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISsqCodeOpenedApplicationService {

	private SsqCodeOpenedCreateCommandExecutor ssqCodeOpenedCreateCommandExecutor;

	private SsqCodeOpenedDeleteCommandExecutor ssqCodeOpenedDeleteCommandExecutor;

	private SsqCodeOpenedUpdateCommandExecutor ssqCodeOpenedUpdateCommandExecutor;


	@Override
	public SingleResponse<SsqCodeOpenedVO> create(SsqCodeOpenedCreateCommand ssqCodeOpenedCreateCommand) {
		return ssqCodeOpenedCreateCommandExecutor.execute(ssqCodeOpenedCreateCommand);
	}

	@Override
	public SingleResponse<SsqCodeOpenedVO> delete(IdCommand deleteCommand) {
		return ssqCodeOpenedDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<SsqCodeOpenedVO> update(SsqCodeOpenedUpdateCommand ssqCodeOpenedUpdateCommand) {
		return ssqCodeOpenedUpdateCommandExecutor.execute(ssqCodeOpenedUpdateCommand);
	}

	@Autowired
	public void setSsqCodeOpenedCreateCommandExecutor(SsqCodeOpenedCreateCommandExecutor ssqCodeOpenedCreateCommandExecutor) {
		this.ssqCodeOpenedCreateCommandExecutor = ssqCodeOpenedCreateCommandExecutor;
	}

	@Autowired
	public void setSsqCodeOpenedDeleteCommandExecutor(SsqCodeOpenedDeleteCommandExecutor ssqCodeOpenedDeleteCommandExecutor) {
		this.ssqCodeOpenedDeleteCommandExecutor = ssqCodeOpenedDeleteCommandExecutor;
	}
	@Autowired
	public void setSsqCodeOpenedUpdateCommandExecutor(SsqCodeOpenedUpdateCommandExecutor ssqCodeOpenedUpdateCommandExecutor) {
		this.ssqCodeOpenedUpdateCommandExecutor = ssqCodeOpenedUpdateCommandExecutor;
	}

}
