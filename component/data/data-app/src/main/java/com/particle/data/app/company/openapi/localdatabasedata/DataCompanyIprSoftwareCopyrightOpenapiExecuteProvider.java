package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprSoftwareCopyrightExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业软件著作信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:42:08
 */
@Component
public class DataCompanyIprSoftwareCopyrightOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor;
    private DataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_ipr_software_copyright".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand = (DataCompanyIprSoftwareCopyrightExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyIprSoftwareCopyrightExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO> dataCompanyIprSoftwareCopyrightExWarehouseVOPageResponse = (PageResponse<DataCompanyIprSoftwareCopyrightExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor.warehouse(dataCompanyIprSoftwareCopyrightExWarehouseVOPageResponse);
    }

    @Autowired
    public void setDataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor(DataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor = dataCompanyIprSoftwareCopyrightWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor(DataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor) {
        this.dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor = dataCompanyIprSoftwareCopyrightWrapWarehouseCommandExecutor;
    }
}
