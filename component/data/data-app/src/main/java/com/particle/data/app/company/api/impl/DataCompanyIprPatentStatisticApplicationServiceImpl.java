package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentStatisticCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentStatisticDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentStatisticUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentStatisticCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentStatisticApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentStatisticWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentStatisticExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentStatisticWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利统计 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentStatisticApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentStatisticApplicationService {

    private DataCompanyIprPatentStatisticCreateCommandExecutor dataCompanyIprPatentStatisticCreateCommandExecutor;

    private DataCompanyIprPatentStatisticDeleteCommandExecutor dataCompanyIprPatentStatisticDeleteCommandExecutor;

    private DataCompanyIprPatentStatisticUpdateCommandExecutor dataCompanyIprPatentStatisticUpdateCommandExecutor;

    private DataCompanyIprPatentStatisticCommandExecutor dataCompanyIprPatentStatisticCommandExecutor;

    private DataCompanyIprPatentStatisticWarehouseCommandExecutor dataCompanyIprPatentStatisticWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentStatisticVO> create(DataCompanyIprPatentStatisticCreateCommand dataCompanyIprPatentStatisticCreateCommand) {
        return dataCompanyIprPatentStatisticCreateCommandExecutor.execute(dataCompanyIprPatentStatisticCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentStatisticVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentStatisticDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentStatisticVO> update(DataCompanyIprPatentStatisticUpdateCommand dataCompanyIprPatentStatisticUpdateCommand) {
        return dataCompanyIprPatentStatisticUpdateCommandExecutor.execute(dataCompanyIprPatentStatisticUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentStatisticExWarehouseVO> warehouse(DataCompanyIprPatentStatisticWarehouseCommand dataCompanyIprPatentStatisticWarehouseCommand) {
        return dataCompanyIprPatentStatisticWarehouseCommandExecutor.warehouse(dataCompanyIprPatentStatisticWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentStatisticCreateCommandExecutor(DataCompanyIprPatentStatisticCreateCommandExecutor dataCompanyIprPatentStatisticCreateCommandExecutor) {
        this.dataCompanyIprPatentStatisticCreateCommandExecutor = dataCompanyIprPatentStatisticCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentStatisticDeleteCommandExecutor(DataCompanyIprPatentStatisticDeleteCommandExecutor dataCompanyIprPatentStatisticDeleteCommandExecutor) {
        this.dataCompanyIprPatentStatisticDeleteCommandExecutor = dataCompanyIprPatentStatisticDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentStatisticUpdateCommandExecutor(DataCompanyIprPatentStatisticUpdateCommandExecutor dataCompanyIprPatentStatisticUpdateCommandExecutor) {
        this.dataCompanyIprPatentStatisticUpdateCommandExecutor = dataCompanyIprPatentStatisticUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentStatisticCommandExecutor(DataCompanyIprPatentStatisticCommandExecutor dataCompanyIprPatentStatisticCommandExecutor) {
        this.dataCompanyIprPatentStatisticCommandExecutor = dataCompanyIprPatentStatisticCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentStatisticWarehouseCommandExecutor(DataCompanyIprPatentStatisticWarehouseCommandExecutor dataCompanyIprPatentStatisticWarehouseCommandExecutor) {
        this.dataCompanyIprPatentStatisticWarehouseCommandExecutor = dataCompanyIprPatentStatisticWarehouseCommandExecutor;
    }

}