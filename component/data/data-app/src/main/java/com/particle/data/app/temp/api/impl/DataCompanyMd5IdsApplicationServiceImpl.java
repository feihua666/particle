package com.particle.data.app.temp.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.temp.executor.DataCompanyMd5IdsCommandExecutor;
import com.particle.data.app.temp.executor.DataCompanyMd5IdsCreateCommandExecutor;
import com.particle.data.app.temp.executor.DataCompanyMd5IdsDeleteCommandExecutor;
import com.particle.data.app.temp.executor.DataCompanyMd5IdsUpdateCommandExecutor;
import com.particle.data.client.temp.api.IDataCompanyMd5IdsApplicationService;
import com.particle.data.client.temp.dto.command.DataCompanyMd5IdsCreateCommand;
import com.particle.data.client.temp.dto.command.DataCompanyMd5IdsUpdateCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 企业md5ids 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyMd5IdsApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyMd5IdsApplicationService {

	private DataCompanyMd5IdsCreateCommandExecutor dataCompanyMd5IdsCreateCommandExecutor;

	private DataCompanyMd5IdsDeleteCommandExecutor dataCompanyMd5IdsDeleteCommandExecutor;

	private DataCompanyMd5IdsUpdateCommandExecutor dataCompanyMd5IdsUpdateCommandExecutor;

	private DataCompanyMd5IdsCommandExecutor dataCompanyMd5IdsCommandExecutor;


	@Override
	public SingleResponse<DataCompanyMd5IdsVO> create(DataCompanyMd5IdsCreateCommand dataCompanyMd5IdsCreateCommand) {
		return dataCompanyMd5IdsCreateCommandExecutor.execute(dataCompanyMd5IdsCreateCommand);
	}

	@Override
	public SingleResponse<DataCompanyMd5IdsVO> delete(IdCommand deleteCommand) {
		return dataCompanyMd5IdsDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataCompanyMd5IdsVO> update(DataCompanyMd5IdsUpdateCommand dataCompanyMd5IdsUpdateCommand) {
		return dataCompanyMd5IdsUpdateCommandExecutor.execute(dataCompanyMd5IdsUpdateCommand);
	}


	@Autowired
	public void setDataCompanyMd5IdsCreateCommandExecutor(DataCompanyMd5IdsCreateCommandExecutor dataCompanyMd5IdsCreateCommandExecutor) {
		this.dataCompanyMd5IdsCreateCommandExecutor = dataCompanyMd5IdsCreateCommandExecutor;
	}

	@Autowired
	public void setDataCompanyMd5IdsDeleteCommandExecutor(DataCompanyMd5IdsDeleteCommandExecutor dataCompanyMd5IdsDeleteCommandExecutor) {
		this.dataCompanyMd5IdsDeleteCommandExecutor = dataCompanyMd5IdsDeleteCommandExecutor;
	}
	@Autowired
	public void setDataCompanyMd5IdsUpdateCommandExecutor(DataCompanyMd5IdsUpdateCommandExecutor dataCompanyMd5IdsUpdateCommandExecutor) {
		this.dataCompanyMd5IdsUpdateCommandExecutor = dataCompanyMd5IdsUpdateCommandExecutor;
	}
	@Autowired
	public void setDataCompanyMd5IdsCommandExecutor(DataCompanyMd5IdsCommandExecutor dataCompanyMd5IdsCommandExecutor) {
		this.dataCompanyMd5IdsCommandExecutor = dataCompanyMd5IdsCommandExecutor;
	}
}
