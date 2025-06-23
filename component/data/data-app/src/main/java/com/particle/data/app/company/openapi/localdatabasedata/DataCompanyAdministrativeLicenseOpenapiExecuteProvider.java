package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAbnormalExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyAdministrativeLicenseExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业行政许可信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyAdministrativeLicenseOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor dataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor;
    private DataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_administrative_license".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyAdministrativeLicenseExWarehouseQueryCommand dataCompanyAdministrativeLicenseExWarehouseQueryCommand = (DataCompanyAdministrativeLicenseExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyAdministrativeLicenseExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO> response = (PageResponse<DataCompanyAdministrativeLicenseExWarehouseVO>) warehouseCommand.getParam();
        if (response != null && CollectionUtil.isNotEmpty(response.getData())) {
            DataCompanyAdministrativeLicenseExWarehouseVO next = response.getData().iterator().next();
            // 有一条有 companyId 则认为所有数据都有
            Long companyId = next.getCompanyId();
            if (companyId == null) {
                DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
                companyId = tryWarehouseCompanyAndGetCompanyId(dataCompanyExWarehouseQueryCommand);
                for (DataCompanyAdministrativeLicenseExWarehouseVO item : response.getData()) {
                    item.setCompanyId(companyId);
                }
            }
        }
        dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor(DataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor dataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor) {
        this.dataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor = dataCompanyAdministrativeLicenseWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor(DataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor) {
        this.dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor = dataCompanyAdministrativeLicenseWrapWarehouseCommandExecutor;
    }
}
