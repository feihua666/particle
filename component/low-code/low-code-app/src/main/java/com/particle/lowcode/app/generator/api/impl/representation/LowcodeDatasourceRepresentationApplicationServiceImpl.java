package com.particle.lowcode.app.generator.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.app.generator.executor.representation.LowcodeDatasourceQueryCommandExecutor;
import com.particle.lowcode.client.generator.api.representation.ILowcodeDatasourceRepresentationApplicationService;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourcePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourceQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 低代码数据源 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Service
@CatchAndLog
public class LowcodeDatasourceRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ILowcodeDatasourceRepresentationApplicationService {

	private LowcodeDatasourceQueryCommandExecutor lowcodeDatasourceQueryCommandExecutor;

	@Override
	public SingleResponse<LowcodeDatasourceVO> queryDetail(IdCommand detailCommand) {
		return lowcodeDatasourceQueryCommandExecutor.executeDetail(detailCommand);
	}

	@Override
	public SingleResponse<LowcodeDatasourceVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
		return lowcodeDatasourceQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
	}

	@Override
	public PageResponse<LowcodeDatasourceVO> pageQuery(LowcodeDatasourcePageQueryCommand lowcodeDatasourcePageQueryCommand) {
		return lowcodeDatasourceQueryCommandExecutor.execute(lowcodeDatasourcePageQueryCommand);
	}

	@Override
	public MultiResponse<LowcodeDatasourceVO> queryList(LowcodeDatasourceQueryListCommand lowcodeDatasourceQueryListCommand) {
		return lowcodeDatasourceQueryCommandExecutor.execute(lowcodeDatasourceQueryListCommand);
	}

	@Autowired
	public void setLowcodeDatasourceQueryCommandExecutor(LowcodeDatasourceQueryCommandExecutor lowcodeDatasourceQueryCommandExecutor) {
		this.lowcodeDatasourceQueryCommandExecutor = lowcodeDatasourceQueryCommandExecutor;
	}
}
