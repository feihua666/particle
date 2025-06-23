package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyAbnormalWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyAbnormalWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAbnormalExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业经营异常信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyAbnormalOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyAbnormalWrapExWarehouseCommandExecutor dataCompanyAbnormalWrapExWarehouseCommandExecutor;
    private DataCompanyAbnormalWrapWarehouseCommandExecutor dataCompanyAbnormalWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_abnormal".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyAbnormalExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyAbnormalExWarehouseQueryCommand dataCompanyAbnormalExWarehouseQueryCommand = (DataCompanyAbnormalExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyAbnormalWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAbnormalExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyAbnormalExWarehouseVO> response = (PageResponse<DataCompanyAbnormalExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyAbnormalExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyAbnormalExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyAbnormalWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyAbnormalWrapExWarehouseCommandExecutor(DataCompanyAbnormalWrapExWarehouseCommandExecutor dataCompanyAbnormalWrapExWarehouseCommandExecutor) {
        this.dataCompanyAbnormalWrapExWarehouseCommandExecutor = dataCompanyAbnormalWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAbnormalWrapWarehouseCommandExecutor(DataCompanyAbnormalWrapWarehouseCommandExecutor dataCompanyAbnormalWrapWarehouseCommandExecutor) {
        this.dataCompanyAbnormalWrapWarehouseCommandExecutor = dataCompanyAbnormalWrapWarehouseCommandExecutor;
    }
}
