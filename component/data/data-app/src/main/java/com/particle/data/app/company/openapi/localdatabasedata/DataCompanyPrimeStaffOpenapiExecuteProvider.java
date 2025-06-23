package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyPrimeStaffWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyPrimeStaffWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPrimeStaffExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业主要人员信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyPrimeStaffOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyPrimeStaffWrapExWarehouseCommandExecutor dataCompanyPrimeStaffWrapExWarehouseCommandExecutor;
    private DataCompanyPrimeStaffWrapWarehouseCommandExecutor dataCompanyPrimeStaffWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_prime_staff".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyPrimeStaffExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyPrimeStaffExWarehouseQueryCommand dataCompanyPrimeStaffExWarehouseQueryCommand = (DataCompanyPrimeStaffExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyPrimeStaffWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyPrimeStaffExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyPrimeStaffExWarehouseVO> response = (PageResponse<DataCompanyPrimeStaffExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyPrimeStaffExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyPrimeStaffExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyPrimeStaffWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyPrimeStaffWrapExWarehouseCommandExecutor(DataCompanyPrimeStaffWrapExWarehouseCommandExecutor dataCompanyPrimeStaffWrapExWarehouseCommandExecutor) {
        this.dataCompanyPrimeStaffWrapExWarehouseCommandExecutor = dataCompanyPrimeStaffWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffWrapWarehouseCommandExecutor(DataCompanyPrimeStaffWrapWarehouseCommandExecutor dataCompanyPrimeStaffWrapWarehouseCommandExecutor) {
        this.dataCompanyPrimeStaffWrapWarehouseCommandExecutor = dataCompanyPrimeStaffWrapWarehouseCommandExecutor;
    }
}
