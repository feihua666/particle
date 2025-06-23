package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyPunishmentWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyPunishmentWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPunishmentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanySpotCheckExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业行政处罚信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-27 11:23:36
 */
@Component
public class DataCompanyPunishmentOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyPunishmentWrapExWarehouseCommandExecutor dataCompanyPunishmentWrapExWarehouseCommandExecutor;
    private DataCompanyPunishmentWrapWarehouseCommandExecutor dataCompanyPunishmentWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_punishment".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyPunishmentExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyPunishmentExWarehouseQueryCommand dataCompanyPunishmentExWarehouseQueryCommand = (DataCompanyPunishmentExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyPunishmentWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyPunishmentExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyPunishmentExWarehouseVO> response = (PageResponse<DataCompanyPunishmentExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyPunishmentExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyPunishmentExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyPunishmentWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyPunishmentWrapExWarehouseCommandExecutor(DataCompanyPunishmentWrapExWarehouseCommandExecutor dataCompanyPunishmentWrapExWarehouseCommandExecutor) {
        this.dataCompanyPunishmentWrapExWarehouseCommandExecutor = dataCompanyPunishmentWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPunishmentWrapWarehouseCommandExecutor(DataCompanyPunishmentWrapWarehouseCommandExecutor dataCompanyPunishmentWrapWarehouseCommandExecutor) {
        this.dataCompanyPunishmentWrapWarehouseCommandExecutor = dataCompanyPunishmentWrapWarehouseCommandExecutor;
    }
}
