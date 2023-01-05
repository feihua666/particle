package com.particle.lowcode.app.generator.api.impl;

import com.particle.lowcode.app.generator.executor.LowcodeModelItemCreateCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeModelItemDeleteCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeModelItemUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemUpdateCommand;
import com.particle.lowcode.client.generator.api.ILowcodeModelItemApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
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
 * 低代码模型项目 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Transactional
@Service
@CatchAndLog
public class LowcodeModelItemApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeModelItemApplicationService {

	private LowcodeModelItemCreateCommandExecutor lowcodeModelItemCreateCommandExecutor;

	private LowcodeModelItemDeleteCommandExecutor lowcodeModelItemDeleteCommandExecutor;

	private LowcodeModelItemUpdateCommandExecutor lowcodeModelItemUpdateCommandExecutor;


	@Override
	public SingleResponse<LowcodeModelItemVO> create(LowcodeModelItemCreateCommand lowcodeModelItemCreateCommand) {
		return lowcodeModelItemCreateCommandExecutor.execute(lowcodeModelItemCreateCommand);
	}

	@Override
	public SingleResponse<LowcodeModelItemVO> delete(IdCommand deleteCommand) {
		return lowcodeModelItemDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<LowcodeModelItemVO> update(LowcodeModelItemUpdateCommand lowcodeModelItemUpdateCommand) {
		return lowcodeModelItemUpdateCommandExecutor.execute(lowcodeModelItemUpdateCommand);
	}

	@Autowired
	public void setLowcodeModelItemCreateCommandExecutor(LowcodeModelItemCreateCommandExecutor lowcodeModelItemCreateCommandExecutor) {
		this.lowcodeModelItemCreateCommandExecutor = lowcodeModelItemCreateCommandExecutor;
	}

	@Autowired
	public void setLowcodeModelItemDeleteCommandExecutor(LowcodeModelItemDeleteCommandExecutor lowcodeModelItemDeleteCommandExecutor) {
		this.lowcodeModelItemDeleteCommandExecutor = lowcodeModelItemDeleteCommandExecutor;
	}
	@Autowired
	public void setLowcodeModelItemUpdateCommandExecutor(LowcodeModelItemUpdateCommandExecutor lowcodeModelItemUpdateCommandExecutor) {
		this.lowcodeModelItemUpdateCommandExecutor = lowcodeModelItemUpdateCommandExecutor;
	}

}
