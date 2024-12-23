package com.particle.lowcode.app.generator.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.app.generator.executor.representation.LowcodeModelQueryCommandExecutor;
import com.particle.lowcode.client.generator.api.representation.ILowcodeModelRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 低代码模型 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Service
@CatchAndLog
public class LowcodeModelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeModelRepresentationApplicationService {

	private LowcodeModelQueryCommandExecutor lowcodeModelQueryCommandExecutor;

	@Override
	public SingleResponse<LowcodeModelVO> queryDetail(IdCommand detailCommand) {
		return lowcodeModelQueryCommandExecutor.executeDetail(detailCommand);
	}

	@Override
	public SingleResponse<LowcodeModelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
		return lowcodeModelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
	}

	@Override
	public PageResponse<LowcodeModelVO> pageQuery(LowcodeModelPageQueryCommand lowcodeModelPageQueryCommand) {
		return lowcodeModelQueryCommandExecutor.execute(lowcodeModelPageQueryCommand);
	}

	@Override
	public MultiResponse<LowcodeModelVO> queryList(LowcodeModelQueryListCommand lowcodeModelQueryListCommand) {
		return lowcodeModelQueryCommandExecutor.execute(lowcodeModelQueryListCommand);
	}

	@Autowired
	public void setLowcodeModelQueryCommandExecutor(LowcodeModelQueryCommandExecutor lowcodeModelQueryCommandExecutor) {
		this.lowcodeModelQueryCommandExecutor = lowcodeModelQueryCommandExecutor;
	}
}
