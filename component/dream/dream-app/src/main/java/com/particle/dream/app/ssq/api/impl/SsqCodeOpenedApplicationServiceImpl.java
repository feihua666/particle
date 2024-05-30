package com.particle.dream.app.ssq.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.dream.app.ssq.executor.SsqCodeOpenedCreateCommandExecutor;
import com.particle.dream.app.ssq.executor.SsqCodeOpenedUpdateCommandExecutor;
import com.particle.dream.client.ssq.api.ISsqCodeOpenedApplicationService;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

	private SsqCodeOpenedUpdateCommandExecutor ssqCodeOpenedUpdateCommandExecutor;


	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Response allCodeInit() {
		return ssqCodeOpenedCreateCommandExecutor.allCodeInit();
	}

	@Override
	public Response allCodeStop() {
		return ssqCodeOpenedCreateCommandExecutor.allCodeStop();
	}



	@Autowired
	public void setSsqCodeOpenedCreateCommandExecutor(SsqCodeOpenedCreateCommandExecutor ssqCodeOpenedCreateCommandExecutor) {
		this.ssqCodeOpenedCreateCommandExecutor = ssqCodeOpenedCreateCommandExecutor;
	}

	@Autowired
	public void setSsqCodeOpenedUpdateCommandExecutor(SsqCodeOpenedUpdateCommandExecutor ssqCodeOpenedUpdateCommandExecutor) {
		this.ssqCodeOpenedUpdateCommandExecutor = ssqCodeOpenedUpdateCommandExecutor;
	}

}
