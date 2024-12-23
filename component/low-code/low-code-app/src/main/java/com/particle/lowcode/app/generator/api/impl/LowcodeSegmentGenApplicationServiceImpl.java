package com.particle.lowcode.app.generator.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.app.generator.executor.*;
import com.particle.lowcode.client.generator.api.ILowcodeSegmentGenApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenRenderGenCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenRenderGenVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 低代码生成 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Transactional
@Service
@CatchAndLog
public class LowcodeSegmentGenApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeSegmentGenApplicationService {

	private LowcodeSegmentGenCreateCommandExecutor lowcodeSegmentGenCreateCommandExecutor;

	private LowcodeSegmentGenDeleteCommandExecutor lowcodeSegmentGenDeleteCommandExecutor;

	private LowcodeSegmentGenUpdateCommandExecutor lowcodeSegmentGenUpdateCommandExecutor;

	private LowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor;

	private LowcodeSegmentTemplateRenderCommandExecutor lowcodeSegmentTemplateRenderCommandExecutor;


	@Override
	public SingleResponse<LowcodeSegmentGenVO> create(LowcodeSegmentGenCreateCommand lowcodeSegmentGenCreateCommand) {
		return lowcodeSegmentGenCreateCommandExecutor.execute(lowcodeSegmentGenCreateCommand);
	}

	@Override
	public SingleResponse<LowcodeSegmentGenRenderGenVO> renderGen(LowcodeSegmentGenRenderGenCommand lowcodeSegmentGenRenderGenCommand) {
		return lowcodeSegmentTemplateRenderCommandExecutor.renderGen(lowcodeSegmentGenRenderGenCommand);
	}

	@Override
	public SingleResponse<LowcodeSegmentGenVO> delete(IdCommand deleteCommand) {
		return lowcodeSegmentGenDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<LowcodeSegmentGenVO> update(LowcodeSegmentGenUpdateCommand lowcodeSegmentGenUpdateCommand) {
		return lowcodeSegmentGenUpdateCommandExecutor.execute(lowcodeSegmentGenUpdateCommand);
	}

	@Override
	public SingleResponse<LowcodeSegmentGenVO> reloadLowcodeModelJson(IdCommand reloadCommand) {
		return lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor.reloadLowcodeModelJson(reloadCommand);
	}

	@Autowired
	public void setLowcodeSegmentGenCreateCommandExecutor(LowcodeSegmentGenCreateCommandExecutor lowcodeSegmentGenCreateCommandExecutor) {
		this.lowcodeSegmentGenCreateCommandExecutor = lowcodeSegmentGenCreateCommandExecutor;
	}

	@Autowired
	public void setLowcodeSegmentGenDeleteCommandExecutor(LowcodeSegmentGenDeleteCommandExecutor lowcodeSegmentGenDeleteCommandExecutor) {
		this.lowcodeSegmentGenDeleteCommandExecutor = lowcodeSegmentGenDeleteCommandExecutor;
	}
	@Autowired
	public void setLowcodeSegmentGenUpdateCommandExecutor(LowcodeSegmentGenUpdateCommandExecutor lowcodeSegmentGenUpdateCommandExecutor) {
		this.lowcodeSegmentGenUpdateCommandExecutor = lowcodeSegmentGenUpdateCommandExecutor;
	}

	@Autowired
	public void setLowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor(LowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor) {
		this.lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor = lowcodeSegmentGenReloadLowcodeModelJsonCommandExecutor;
	}
	@Autowired
	public void setLowcodeSegmentTemplateRenderCommandExecutor(LowcodeSegmentTemplateRenderCommandExecutor lowcodeSegmentTemplateRenderCommandExecutor) {
		this.lowcodeSegmentTemplateRenderCommandExecutor = lowcodeSegmentTemplateRenderCommandExecutor;
	}
}
