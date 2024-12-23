package com.particle.data.app.company.api.impl;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.DataCompanyMd5CommandExecutor;
import com.particle.data.app.company.executor.DataCompanyMd5CreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyMd5DeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyMd5UpdateCommandExecutor;
import com.particle.data.client.company.api.IDataCompanyMd5ApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyMd5CreateCommand;
import com.particle.data.client.company.dto.command.DataCompanyMd5UpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 企业md5 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyMd5ApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyMd5ApplicationService {

	private DataCompanyMd5CreateCommandExecutor dataCompanyMd5CreateCommandExecutor;

	private DataCompanyMd5DeleteCommandExecutor dataCompanyMd5DeleteCommandExecutor;

	private DataCompanyMd5UpdateCommandExecutor dataCompanyMd5UpdateCommandExecutor;

	private DataCompanyMd5CommandExecutor dataCompanyMd5CommandExecutor;


	@Override
	public SingleResponse<DataCompanyMd5VO> create(DataCompanyMd5CreateCommand dataCompanyMd5CreateCommand) {
		return dataCompanyMd5CreateCommandExecutor.execute(dataCompanyMd5CreateCommand);
	}

	@Override
	public SingleResponse<DataCompanyMd5VO> delete(IdCommand deleteCommand) {
		return dataCompanyMd5DeleteCommandExecutor.execute(deleteCommand);
	}

	@Override
	public SingleResponse<DataCompanyMd5VO> update(DataCompanyMd5UpdateCommand dataCompanyMd5UpdateCommand) {
		return dataCompanyMd5UpdateCommandExecutor.execute(dataCompanyMd5UpdateCommand);
	}


	@Autowired
	public void setDataCompanyMd5CreateCommandExecutor(DataCompanyMd5CreateCommandExecutor dataCompanyMd5CreateCommandExecutor) {
		this.dataCompanyMd5CreateCommandExecutor = dataCompanyMd5CreateCommandExecutor;
	}

	@Autowired
	public void setDataCompanyMd5DeleteCommandExecutor(DataCompanyMd5DeleteCommandExecutor dataCompanyMd5DeleteCommandExecutor) {
		this.dataCompanyMd5DeleteCommandExecutor = dataCompanyMd5DeleteCommandExecutor;
	}
	@Autowired
	public void setDataCompanyMd5UpdateCommandExecutor(DataCompanyMd5UpdateCommandExecutor dataCompanyMd5UpdateCommandExecutor) {
		this.dataCompanyMd5UpdateCommandExecutor = dataCompanyMd5UpdateCommandExecutor;
	}
	@Autowired
	public void setDataCompanyMd5CommandExecutor(DataCompanyMd5CommandExecutor dataCompanyMd5CommandExecutor) {
		this.dataCompanyMd5CommandExecutor = dataCompanyMd5CommandExecutor;
	}
}
