package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.*;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyCreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyWarehouseCommand;
import com.particle.data.client.company.dto.data.DataCompanyUniqueExWarehouseVO;
import com.particle.data.client.company.dto.data.DataCompanyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 企业 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyApplicationService {

	private DataCompanyCreateCommandExecutor dataCompanyCreateCommandExecutor;

	private DataCompanyDeleteCommandExecutor dataCompanyDeleteCommandExecutor;

	private DataCompanyUpdateCommandExecutor dataCompanyUpdateCommandExecutor;

	private DataCompanyCommandExecutor dataCompanyCommandExecutor;

	private DataCompanyWarehouseCommandExecutor dataCompanyWarehouseCommandExecutor;


	@Override
	public SingleResponse<DataCompanyVO> create(DataCompanyCreateCommand dataCompanyCreateCommand) {
		return dataCompanyCreateCommandExecutor.execute(dataCompanyCreateCommand);
	}

	@Override
	public SingleResponse<DataCompanyVO> delete(IdCommand deleteCommand) {
		return dataCompanyDeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataCompanyVO> update(DataCompanyUpdateCommand dataCompanyUpdateCommand) {
		return dataCompanyUpdateCommandExecutor.execute(dataCompanyUpdateCommand);
	}

	@Override
	public SingleResponse<DataCompanyUniqueExWarehouseVO> warehouse(DataCompanyWarehouseCommand dataCompanyWarehouseCommand) {
		return dataCompanyWarehouseCommandExecutor.warehouse(dataCompanyWarehouseCommand);
	}


	@Autowired
	public void setDataCompanyCreateCommandExecutor(DataCompanyCreateCommandExecutor dataCompanyCreateCommandExecutor) {
		this.dataCompanyCreateCommandExecutor = dataCompanyCreateCommandExecutor;
	}

	@Autowired
	public void setDataCompanyDeleteCommandExecutor(DataCompanyDeleteCommandExecutor dataCompanyDeleteCommandExecutor) {
		this.dataCompanyDeleteCommandExecutor = dataCompanyDeleteCommandExecutor;
	}
	@Autowired
	public void setDataCompanyUpdateCommandExecutor(DataCompanyUpdateCommandExecutor dataCompanyUpdateCommandExecutor) {
		this.dataCompanyUpdateCommandExecutor = dataCompanyUpdateCommandExecutor;
	}
	@Autowired
	public void setDataCompanyCommandExecutor(DataCompanyCommandExecutor dataCompanyCommandExecutor) {
		this.dataCompanyCommandExecutor = dataCompanyCommandExecutor;
	}
	@Autowired
	public void setDataCompanyWarehouseCommandExecutor(DataCompanyWarehouseCommandExecutor dataCompanyWarehouseCommandExecutor) {
		this.dataCompanyWarehouseCommandExecutor = dataCompanyWarehouseCommandExecutor;
	}
}
