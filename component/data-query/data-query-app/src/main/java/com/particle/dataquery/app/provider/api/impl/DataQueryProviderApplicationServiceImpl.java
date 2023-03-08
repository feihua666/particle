package com.particle.dataquery.app.provider.api.impl;

import com.particle.dataquery.app.provider.executor.DataQueryProviderCreateCommandExecutor;
import com.particle.dataquery.app.provider.executor.DataQueryProviderDeleteCommandExecutor;
import com.particle.dataquery.app.provider.executor.DataQueryProviderUpdateCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.client.provider.dto.command.DataQueryProviderUpdateCommand;
import com.particle.dataquery.client.provider.api.IDataQueryProviderApplicationService;
import com.particle.dataquery.client.provider.dto.command.DataQueryProviderCreateCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
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
 * 数据查询供应商 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Transactional
@Service
@CatchAndLog
public class DataQueryProviderApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataQueryProviderApplicationService {

	private DataQueryProviderCreateCommandExecutor dataQueryProviderCreateCommandExecutor;

	private DataQueryProviderDeleteCommandExecutor dataQueryProviderDeleteCommandExecutor;

	private DataQueryProviderUpdateCommandExecutor dataQueryProviderUpdateCommandExecutor;


	@Override
	public SingleResponse<DataQueryProviderVO> create(DataQueryProviderCreateCommand dataQueryProviderCreateCommand) {
		return dataQueryProviderCreateCommandExecutor.execute(dataQueryProviderCreateCommand);
	}

	@Override
	public SingleResponse<DataQueryProviderVO> delete(IdCommand deleteCommand) {
		return dataQueryProviderDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataQueryProviderVO> update(DataQueryProviderUpdateCommand dataQueryProviderUpdateCommand) {
		return dataQueryProviderUpdateCommandExecutor.execute(dataQueryProviderUpdateCommand);
	}

	@Autowired
	public void setDataQueryProviderCreateCommandExecutor(DataQueryProviderCreateCommandExecutor dataQueryProviderCreateCommandExecutor) {
		this.dataQueryProviderCreateCommandExecutor = dataQueryProviderCreateCommandExecutor;
	}

	@Autowired
	public void setDataQueryProviderDeleteCommandExecutor(DataQueryProviderDeleteCommandExecutor dataQueryProviderDeleteCommandExecutor) {
		this.dataQueryProviderDeleteCommandExecutor = dataQueryProviderDeleteCommandExecutor;
	}
	@Autowired
	public void setDataQueryProviderUpdateCommandExecutor(DataQueryProviderUpdateCommandExecutor dataQueryProviderUpdateCommandExecutor) {
		this.dataQueryProviderUpdateCommandExecutor = dataQueryProviderUpdateCommandExecutor;
	}

}
