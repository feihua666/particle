package com.particle.lowcode.app.generator.api.impl;

import com.particle.lowcode.app.generator.executor.LowcodeSegmentTemplateCreateCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeSegmentTemplateDeleteCommandExecutor;
import com.particle.lowcode.app.generator.executor.LowcodeSegmentTemplateUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateUpdateCommand;
import com.particle.lowcode.client.generator.api.ILowcodeSegmentTemplateApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
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
 * 低代码片段模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Transactional
@Service
@CatchAndLog
public class LowcodeSegmentTemplateApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeSegmentTemplateApplicationService {

	private LowcodeSegmentTemplateCreateCommandExecutor lowcodeSegmentTemplateCreateCommandExecutor;

	private LowcodeSegmentTemplateDeleteCommandExecutor lowcodeSegmentTemplateDeleteCommandExecutor;

	private LowcodeSegmentTemplateUpdateCommandExecutor lowcodeSegmentTemplateUpdateCommandExecutor;


	@Override
	public SingleResponse<LowcodeSegmentTemplateVO> create(LowcodeSegmentTemplateCreateCommand lowcodeSegmentTemplateCreateCommand) {
		return lowcodeSegmentTemplateCreateCommandExecutor.execute(lowcodeSegmentTemplateCreateCommand);
	}

	@Override
	public SingleResponse<LowcodeSegmentTemplateVO> delete(IdCommand deleteCommand) {
		return lowcodeSegmentTemplateDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<LowcodeSegmentTemplateVO> update(LowcodeSegmentTemplateUpdateCommand lowcodeSegmentTemplateUpdateCommand) {
		return lowcodeSegmentTemplateUpdateCommandExecutor.execute(lowcodeSegmentTemplateUpdateCommand);
	}

	@Autowired
	public void setLowcodeSegmentTemplateCreateCommandExecutor(LowcodeSegmentTemplateCreateCommandExecutor lowcodeSegmentTemplateCreateCommandExecutor) {
		this.lowcodeSegmentTemplateCreateCommandExecutor = lowcodeSegmentTemplateCreateCommandExecutor;
	}

	@Autowired
	public void setLowcodeSegmentTemplateDeleteCommandExecutor(LowcodeSegmentTemplateDeleteCommandExecutor lowcodeSegmentTemplateDeleteCommandExecutor) {
		this.lowcodeSegmentTemplateDeleteCommandExecutor = lowcodeSegmentTemplateDeleteCommandExecutor;
	}
	@Autowired
	public void setLowcodeSegmentTemplateUpdateCommandExecutor(LowcodeSegmentTemplateUpdateCommandExecutor lowcodeSegmentTemplateUpdateCommandExecutor) {
		this.lowcodeSegmentTemplateUpdateCommandExecutor = lowcodeSegmentTemplateUpdateCommandExecutor;
	}

}
