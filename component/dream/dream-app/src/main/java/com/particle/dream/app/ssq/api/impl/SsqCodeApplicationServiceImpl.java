package com.particle.dream.app.ssq.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.dream.app.ssq.executor.SsqCodeCreateCommandExecutor;
import com.particle.dream.app.ssq.executor.SsqCodeUpdateCommandExecutor;
import com.particle.dream.client.ssq.api.ISsqCodeApplicationService;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 双色球号码 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Transactional
@Service
@CatchAndLog
public class SsqCodeApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ISsqCodeApplicationService {

	private SsqCodeCreateCommandExecutor ssqCodeCreateCommandExecutor;


	private SsqCodeUpdateCommandExecutor ssqCodeUpdateCommandExecutor;


	/**
	 * 初始化不添加事务，因为里面有大量数据循环添加
	 * @return
	 */
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Response allCodeInit() {
		return ssqCodeCreateCommandExecutor.allCodeInit();
	}
	@Transactional(propagation = Propagation.NEVER)
	@Override
	public Response allCodeUpdate() {
		return ssqCodeCreateCommandExecutor.allCodeUpdate();
	}

	@Override
	public Response allCodeStop() {
		return ssqCodeCreateCommandExecutor.allCodeStop();
	}

	@Autowired
	public void setSsqCodeCreateCommandExecutor(SsqCodeCreateCommandExecutor ssqCodeCreateCommandExecutor) {
		this.ssqCodeCreateCommandExecutor = ssqCodeCreateCommandExecutor;
	}
	@Autowired
	public void setSsqCodeUpdateCommandExecutor(SsqCodeUpdateCommandExecutor ssqCodeUpdateCommandExecutor) {
		this.ssqCodeUpdateCommandExecutor = ssqCodeUpdateCommandExecutor;
	}

}
