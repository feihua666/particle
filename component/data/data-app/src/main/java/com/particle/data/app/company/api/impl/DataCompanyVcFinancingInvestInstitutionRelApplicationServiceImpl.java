package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcFinancingInvestInstitutionRelCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingInvestInstitutionRelUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyVcFinancingInvestInstitutionRelApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingInvestInstitutionRelCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;

import com.particle.data.app.company.executor.DataCompanyVcFinancingInvestInstitutionRelCommandExecutor;
import com.particle.data.client.company.dto.command.CompanyVcFinancingAssignCompanyVcInvestInstitutionCommand;
import com.particle.data.client.company.dto.command.CompanyVcInvestInstitutionAssignCompanyVcFinancingCommand;

import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor;
/**
 * <p>
 * 企业融资历史投资机构关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyVcFinancingInvestInstitutionRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcFinancingInvestInstitutionRelApplicationService {

    private DataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor dataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor;

    private DataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor dataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor;

    private DataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor dataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor;

    private DataCompanyVcFinancingInvestInstitutionRelCommandExecutor dataCompanyVcFinancingInvestInstitutionRelCommandExecutor;

    private DataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> create(DataCompanyVcFinancingInvestInstitutionRelCreateCommand dataCompanyVcFinancingInvestInstitutionRelCreateCommand) {
        return dataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor.execute(dataCompanyVcFinancingInvestInstitutionRelCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> delete(IdCommand deleteCommand) {
        return dataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> update(DataCompanyVcFinancingInvestInstitutionRelUpdateCommand dataCompanyVcFinancingInvestInstitutionRelUpdateCommand) {
        return dataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor.execute(dataCompanyVcFinancingInvestInstitutionRelUpdateCommand);
    }


    @Override
    public Response companyVcFinancingAssignCompanyVcInvestInstitution(CompanyVcFinancingAssignCompanyVcInvestInstitutionCommand companyVcFinancingAssignCompanyVcInvestInstitutionCommand){
        return dataCompanyVcFinancingInvestInstitutionRelCommandExecutor.companyVcFinancingAssignCompanyVcInvestInstitution(companyVcFinancingAssignCompanyVcInvestInstitutionCommand);
    }

    @Override
    public Response companyVcInvestInstitutionAssignCompanyVcFinancing(CompanyVcInvestInstitutionAssignCompanyVcFinancingCommand companyVcInvestInstitutionAssignCompanyVcFinancingCommand){
        return dataCompanyVcFinancingInvestInstitutionRelCommandExecutor.companyVcInvestInstitutionAssignCompanyVcFinancing(companyVcInvestInstitutionAssignCompanyVcFinancingCommand);
    }

    @Override
    public Response deleteByCompanyVcFinancingId(IdCommand idCommand){
        return dataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor.deleteByCompanyVcFinancingId(idCommand);
    }

    @Override
    public Response deleteByCompanyVcInvestInstitutionId(IdCommand idCommand){
        return dataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor.deleteByCompanyVcInvestInstitutionId(idCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelExWarehouseVO> warehouse(DataCompanyVcFinancingInvestInstitutionRelWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor(DataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor dataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor) {
        this.dataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor = dataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor(DataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor dataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor) {
        this.dataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor = dataCompanyVcFinancingInvestInstitutionRelDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor(DataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor dataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor) {
        this.dataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor = dataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingInvestInstitutionRelCommandExecutor(DataCompanyVcFinancingInvestInstitutionRelCommandExecutor dataCompanyVcFinancingInvestInstitutionRelCommandExecutor) {
        this.dataCompanyVcFinancingInvestInstitutionRelCommandExecutor = dataCompanyVcFinancingInvestInstitutionRelCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor(DataCompanyVcFinancingInvestInstitutionRelWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}