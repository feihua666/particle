package com.particle.data.app.company.api.impl;

import com.particle.data.app.company.executor.DataCompanyIprPatentFamilyCreateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentFamilyDeleteCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentFamilyUpdateCommandExecutor;
import com.particle.data.app.company.executor.DataCompanyIprPatentFamilyCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyUpdateCommand;
import com.particle.data.client.company.api.IDataCompanyIprPatentFamilyApplicationService;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPatentFamilyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentFamilyExWarehouseVO;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPatentFamilyWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利同族信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Transactional
@Service
@CatchAndLog
public class DataCompanyIprPatentFamilyApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentFamilyApplicationService {

    private DataCompanyIprPatentFamilyCreateCommandExecutor dataCompanyIprPatentFamilyCreateCommandExecutor;

    private DataCompanyIprPatentFamilyDeleteCommandExecutor dataCompanyIprPatentFamilyDeleteCommandExecutor;

    private DataCompanyIprPatentFamilyUpdateCommandExecutor dataCompanyIprPatentFamilyUpdateCommandExecutor;

    private DataCompanyIprPatentFamilyCommandExecutor dataCompanyIprPatentFamilyCommandExecutor;

    private DataCompanyIprPatentFamilyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentFamilyVO> create(DataCompanyIprPatentFamilyCreateCommand dataCompanyIprPatentFamilyCreateCommand) {
        return dataCompanyIprPatentFamilyCreateCommandExecutor.execute(dataCompanyIprPatentFamilyCreateCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentFamilyVO> delete(IdCommand deleteCommand) {
        return dataCompanyIprPatentFamilyDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentFamilyVO> update(DataCompanyIprPatentFamilyUpdateCommand dataCompanyIprPatentFamilyUpdateCommand) {
        return dataCompanyIprPatentFamilyUpdateCommandExecutor.execute(dataCompanyIprPatentFamilyUpdateCommand);
    }


    @Override
    public SingleResponse<DataCompanyIprPatentFamilyExWarehouseVO> warehouse(DataCompanyIprPatentFamilyWarehouseCommand dataCompanyBasicWarehouseCommand) {
        return dataCompanyBasicWarehouseCommandExecutor.warehouse(dataCompanyBasicWarehouseCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentFamilyCreateCommandExecutor(DataCompanyIprPatentFamilyCreateCommandExecutor dataCompanyIprPatentFamilyCreateCommandExecutor) {
        this.dataCompanyIprPatentFamilyCreateCommandExecutor = dataCompanyIprPatentFamilyCreateCommandExecutor;
    }

    @Autowired
    public void setDataCompanyIprPatentFamilyDeleteCommandExecutor(DataCompanyIprPatentFamilyDeleteCommandExecutor dataCompanyIprPatentFamilyDeleteCommandExecutor) {
        this.dataCompanyIprPatentFamilyDeleteCommandExecutor = dataCompanyIprPatentFamilyDeleteCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentFamilyUpdateCommandExecutor(DataCompanyIprPatentFamilyUpdateCommandExecutor dataCompanyIprPatentFamilyUpdateCommandExecutor) {
        this.dataCompanyIprPatentFamilyUpdateCommandExecutor = dataCompanyIprPatentFamilyUpdateCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentFamilyCommandExecutor(DataCompanyIprPatentFamilyCommandExecutor dataCompanyIprPatentFamilyCommandExecutor) {
        this.dataCompanyIprPatentFamilyCommandExecutor = dataCompanyIprPatentFamilyCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentFamilyWarehouseCommandExecutor(DataCompanyIprPatentFamilyWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }

}