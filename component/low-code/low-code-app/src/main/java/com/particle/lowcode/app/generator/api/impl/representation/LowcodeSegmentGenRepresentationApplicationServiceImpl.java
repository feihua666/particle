package com.particle.lowcode.app.generator.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.app.generator.executor.representation.LowcodeSegmentGenQueryCommandExecutor;
import com.particle.lowcode.client.generator.api.representation.ILowcodeSegmentGenRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 低代码生成 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Service
@CatchAndLog
public class LowcodeSegmentGenRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeSegmentGenRepresentationApplicationService {

	private LowcodeSegmentGenQueryCommandExecutor lowcodeSegmentGenQueryCommandExecutor;

	@Override
	public SingleResponse<LowcodeSegmentGenVO> queryDetail(IdCommand detailCommand) {
		return lowcodeSegmentGenQueryCommandExecutor.executeDetail(detailCommand);
	}

	@Override
	public SingleResponse<LowcodeSegmentGenVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
		return lowcodeSegmentGenQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
	}

	@Override
	public PageResponse<LowcodeSegmentGenVO> pageQuery(LowcodeSegmentGenPageQueryCommand lowcodeSegmentGenPageQueryCommand) {
		return lowcodeSegmentGenQueryCommandExecutor.execute(lowcodeSegmentGenPageQueryCommand);
	}

	@Override
	public MultiResponse<LowcodeSegmentGenVO> queryList(LowcodeSegmentGenQueryListCommand lowcodeSegmentGenQueryListCommand) {
		return lowcodeSegmentGenQueryCommandExecutor.execute(lowcodeSegmentGenQueryListCommand);
	}

	@Autowired
	public void setLowcodeSegmentGenQueryCommandExecutor(LowcodeSegmentGenQueryCommandExecutor lowcodeSegmentGenQueryCommandExecutor) {
		this.lowcodeSegmentGenQueryCommandExecutor = lowcodeSegmentGenQueryCommandExecutor;
	}
}
