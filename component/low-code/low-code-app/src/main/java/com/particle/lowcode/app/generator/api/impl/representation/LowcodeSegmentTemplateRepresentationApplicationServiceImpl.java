package com.particle.lowcode.app.generator.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.app.generator.executor.representation.LowcodeSegmentTemplateQueryCommandExecutor;
import com.particle.lowcode.client.generator.api.representation.ILowcodeSegmentTemplateRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplatePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplateQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 低代码片段模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Service
@CatchAndLog
public class LowcodeSegmentTemplateRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeSegmentTemplateRepresentationApplicationService {

	private LowcodeSegmentTemplateQueryCommandExecutor lowcodeSegmentTemplateQueryCommandExecutor;

	@Override
	public SingleResponse<LowcodeSegmentTemplateVO> queryDetail(IdCommand detailCommand) {
		return lowcodeSegmentTemplateQueryCommandExecutor.executeDetail(detailCommand);
	}

	@Override
	public SingleResponse<LowcodeSegmentTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
		return lowcodeSegmentTemplateQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
	}

	@Override
	public PageResponse<LowcodeSegmentTemplateVO> pageQuery(LowcodeSegmentTemplatePageQueryCommand lowcodeSegmentTemplatePageQueryCommand) {
		return lowcodeSegmentTemplateQueryCommandExecutor.execute(lowcodeSegmentTemplatePageQueryCommand);
	}

	@Override
	public MultiResponse<LowcodeSegmentTemplateVO> queryList(LowcodeSegmentTemplateQueryListCommand lowcodeSegmentTemplateQueryListCommand) {
		return lowcodeSegmentTemplateQueryCommandExecutor.execute(lowcodeSegmentTemplateQueryListCommand);
	}

	@Autowired
	public void setLowcodeSegmentTemplateQueryCommandExecutor(LowcodeSegmentTemplateQueryCommandExecutor lowcodeSegmentTemplateQueryCommandExecutor) {
		this.lowcodeSegmentTemplateQueryCommandExecutor = lowcodeSegmentTemplateQueryCommandExecutor;
	}
}
