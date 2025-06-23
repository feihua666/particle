package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyShareholderWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyShareholderWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyShareholderExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业股东信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-27 11:23:36
 */
@Component
public class DataCompanyShareholderOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyShareholderWrapExWarehouseCommandExecutor dataCompanyShareholderWrapExWarehouseCommandExecutor;
    private DataCompanyShareholderWrapWarehouseCommandExecutor dataCompanyShareholderWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_shareholder".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyShareholderExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyShareholderExWarehouseQueryCommand dataCompanyShareholderExWarehouseQueryCommand = (DataCompanyShareholderExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyShareholderWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyShareholderExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyShareholderExWarehouseVO> response = (PageResponse<DataCompanyShareholderExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyShareholderExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyShareholderExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyShareholderWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyShareholderWrapExWarehouseCommandExecutor(DataCompanyShareholderWrapExWarehouseCommandExecutor dataCompanyShareholderWrapExWarehouseCommandExecutor) {
        this.dataCompanyShareholderWrapExWarehouseCommandExecutor = dataCompanyShareholderWrapExWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyShareholderWrapWarehouseCommandExecutor(DataCompanyShareholderWrapWarehouseCommandExecutor dataCompanyShareholderWrapWarehouseCommandExecutor) {
        this.dataCompanyShareholderWrapWarehouseCommandExecutor = dataCompanyShareholderWrapWarehouseCommandExecutor;
    }
}
