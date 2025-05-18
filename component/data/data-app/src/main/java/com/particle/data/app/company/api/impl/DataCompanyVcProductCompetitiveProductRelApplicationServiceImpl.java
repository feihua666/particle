package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyVcProductCompetitiveProductRelCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyVcProductCompetitiveProductRelCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCompetitiveProductRelUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyVcProductCompetitiveProductRelApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyVcProductCompetitiveProductRelCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcProductCompetitiveProductRelVO;

import com.particle.data.app.company.executor.DataCompanyVcProductCompetitiveProductRelCommandExecutor;
import com.particle.data.client.company.dto.command.CompanyVcProductAssignCompanyVcCompetitiveProductCommand;
import com.particle.data.client.company.dto.command.CompanyVcCompetitiveProductAssignCompanyVcProductCommand;

import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyVcProductCompetitiveProductRelWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductCompetitiveProductRelExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor;
/**
 * <p>
 * 企业融资产品竞品关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:47:00
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyVcProductCompetitiveProductRelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyVcProductCompetitiveProductRelApplicationService {

    private DataCompanyVcProductCompetitiveProductRelCreateCommandExecutor dataCompanyVcProductCompetitiveProductRelCreateCommandExecutor;

    private DataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor dataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor;

    private DataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor dataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor;

    private DataCompanyVcProductCompetitiveProductRelCommandExecutor dataCompanyVcProductCompetitiveProductRelCommandExecutor;

    private DataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> create(DataCompanyVcProductCompetitiveProductRelCreateCommand dataCompanyVcProductCompetitiveProductRelCreateCommand) {
        return dataCompanyVcProductCompetitiveProductRelCreateCommandExecutor.execute(dataCompanyVcProductCompetitiveProductRelCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> delete(IdCommand deleteCommand) {
        return dataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelVO> update(DataCompanyVcProductCompetitiveProductRelUpdateCommand dataCompanyVcProductCompetitiveProductRelUpdateCommand) {
        return dataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor.execute(dataCompanyVcProductCompetitiveProductRelUpdateCommand);
    }


    @Override
    public Response companyVcProductAssignCompanyVcCompetitiveProduct(CompanyVcProductAssignCompanyVcCompetitiveProductCommand companyVcProductAssignCompanyVcCompetitiveProductCommand){
        return dataCompanyVcProductCompetitiveProductRelCommandExecutor.companyVcProductAssignCompanyVcCompetitiveProduct(companyVcProductAssignCompanyVcCompetitiveProductCommand);
    }

    @Override
    public Response companyVcCompetitiveProductAssignCompanyVcProduct(CompanyVcCompetitiveProductAssignCompanyVcProductCommand companyVcCompetitiveProductAssignCompanyVcProductCommand){
        return dataCompanyVcProductCompetitiveProductRelCommandExecutor.companyVcCompetitiveProductAssignCompanyVcProduct(companyVcCompetitiveProductAssignCompanyVcProductCommand);
    }

    @Override
    public Response deleteByCompanyVcProductId(IdCommand idCommand){
        return dataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor.deleteByCompanyVcProductId(idCommand);
    }

    @Override
    public Response deleteByCompanyVcCompetitiveProductId(IdCommand idCommand){
        return dataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor.deleteByCompanyVcCompetitiveProductId(idCommand);
    }

    @Override
    public SingleResponse<DataCompanyVcProductCompetitiveProductRelExWarehouseVO> warehouse(DataCompanyVcProductCompetitiveProductRelWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyVcProductCompetitiveProductRelCreateCommandExecutor(DataCompanyVcProductCompetitiveProductRelCreateCommandExecutor dataCompanyVcProductCompetitiveProductRelCreateCommandExecutor) {
        this.dataCompanyVcProductCompetitiveProductRelCreateCommandExecutor = dataCompanyVcProductCompetitiveProductRelCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor(DataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor dataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor) {
        this.dataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor = dataCompanyVcProductCompetitiveProductRelDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor(DataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor dataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor) {
        this.dataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor = dataCompanyVcProductCompetitiveProductRelUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductCompetitiveProductRelCommandExecutor(DataCompanyVcProductCompetitiveProductRelCommandExecutor dataCompanyVcProductCompetitiveProductRelCommandExecutor) {
        this.dataCompanyVcProductCompetitiveProductRelCommandExecutor = dataCompanyVcProductCompetitiveProductRelCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor(DataCompanyVcProductCompetitiveProductRelWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}