package com.particle.lowcode.app.generator.api.impl.representation;

import com.particle.lowcode.app.generator.executor.representation.LowcodeModelItemQueryCommandExecutor;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemUpdateCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemPageQueryCommand;
import com.particle.lowcode.client.generator.api.representation.ILowcodeModelItemRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.LowcodeModelItemCreateCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemQueryListCommand;
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
@Service
@CatchAndLog
public class LowcodeModelItemRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeModelItemRepresentationApplicationService {

	private LowcodeModelItemQueryCommandExecutor lowcodeModelItemQueryCommandExecutor;

	@Override
	public SingleResponse<LowcodeModelItemVO> queryDetail(IdCommand detailCommand) {
		return lowcodeModelItemQueryCommandExecutor.executeDetail(detailCommand);
	}

	@Override
	public SingleResponse<LowcodeModelItemVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
		return lowcodeModelItemQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
	}

	@Override
	public PageResponse<LowcodeModelItemVO> pageQuery(LowcodeModelItemPageQueryCommand lowcodeModelItemPageQueryCommand) {
		return lowcodeModelItemQueryCommandExecutor.execute(lowcodeModelItemPageQueryCommand);
	}

	@Override
	public MultiResponse<LowcodeModelItemVO> queryList(LowcodeModelItemQueryListCommand lowcodeModelItemQueryListCommand) {
		return lowcodeModelItemQueryCommandExecutor.execute(lowcodeModelItemQueryListCommand);
	}

	@Autowired
	public void setLowcodeModelItemQueryCommandExecutor(LowcodeModelItemQueryCommandExecutor lowcodeModelItemQueryCommandExecutor) {
		this.lowcodeModelItemQueryCommandExecutor = lowcodeModelItemQueryCommandExecutor;
	}
}
