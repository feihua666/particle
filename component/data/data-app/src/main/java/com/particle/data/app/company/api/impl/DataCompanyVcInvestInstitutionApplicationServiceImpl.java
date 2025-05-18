package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyVcInvestInstitutionCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcInvestInstitutionDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcInvestInstitutionUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcInvestInstitutionCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyVcInvestInstitutionApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcInvestInstitutionWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcInvestInstitutionWarehouseCommandExecutor;
/**
 * <p>
 * 企业投资机构 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyVcInvestInstitutionApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcInvestInstitutionApplicationService {

    private DataCompanyVcInvestInstitutionCreateCommandExecutor dataCompanyVcInvestInstitutionCreateCommandExecutor;

    private DataCompanyVcInvestInstitutionDeleteCommandExecutor dataCompanyVcInvestInstitutionDeleteCommandExecutor;

    private DataCompanyVcInvestInstitutionUpdateCommandExecutor dataCompanyVcInvestInstitutionUpdateCommandExecutor;

    private DataCompanyVcInvestInstitutionCommandExecutor dataCompanyVcInvestInstitutionCommandExecutor;

    private DataCompanyVcInvestInstitutionWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcInvestInstitutionVO> create(DataCompanyVcInvestInstitutionCreateCommand dataCompanyVcInvestInstitutionCreateCommand) {
        return dataCompanyVcInvestInstitutionCreateCommandExecutor.execute(dataCompanyVcInvestInstitutionCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcInvestInstitutionVO> delete(IdCommand deleteCommand) {
        return dataCompanyVcInvestInstitutionDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcInvestInstitutionVO> update(DataCompanyVcInvestInstitutionUpdateCommand dataCompanyVcInvestInstitutionUpdateCommand) {
        return dataCompanyVcInvestInstitutionUpdateCommandExecutor.execute(dataCompanyVcInvestInstitutionUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyVcInvestInstitutionExWarehouseVO> warehouse(DataCompanyVcInvestInstitutionWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyVcInvestInstitutionCreateCommandExecutor(DataCompanyVcInvestInstitutionCreateCommandExecutor dataCompanyVcInvestInstitutionCreateCommandExecutor) {
        this.dataCompanyVcInvestInstitutionCreateCommandExecutor = dataCompanyVcInvestInstitutionCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyVcInvestInstitutionDeleteCommandExecutor(DataCompanyVcInvestInstitutionDeleteCommandExecutor dataCompanyVcInvestInstitutionDeleteCommandExecutor) {
        this.dataCompanyVcInvestInstitutionDeleteCommandExecutor = dataCompanyVcInvestInstitutionDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcInvestInstitutionUpdateCommandExecutor(DataCompanyVcInvestInstitutionUpdateCommandExecutor dataCompanyVcInvestInstitutionUpdateCommandExecutor) {
        this.dataCompanyVcInvestInstitutionUpdateCommandExecutor = dataCompanyVcInvestInstitutionUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcInvestInstitutionCommandExecutor(DataCompanyVcInvestInstitutionCommandExecutor dataCompanyVcInvestInstitutionCommandExecutor) {
        this.dataCompanyVcInvestInstitutionCommandExecutor = dataCompanyVcInvestInstitutionCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcInvestInstitutionWarehouseCommandExecutor(DataCompanyVcInvestInstitutionWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}