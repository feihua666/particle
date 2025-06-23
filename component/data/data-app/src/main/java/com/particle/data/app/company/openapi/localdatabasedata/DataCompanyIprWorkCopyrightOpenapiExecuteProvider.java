package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprWorkCopyrightExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业作品著作信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:42:40
 */
@Component
public class DataCompanyIprWorkCopyrightOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor dataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor;
    private DataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_ipr_work_copyright".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyIprWorkCopyrightExWarehouseQueryCommand dataCompanyIprWorkCopyrightExWarehouseQueryCommand = (DataCompanyIprWorkCopyrightExWarehouseQueryCommand)openapiCommand.getEx1Param();
        return dataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand,dataCompanyIprWorkCopyrightExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO> dataCompanyIprWorkCopyrightExWarehouseVOPageResponse = (PageResponse<DataCompanyIprWorkCopyrightExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor.warehouse(dataCompanyIprWorkCopyrightExWarehouseVOPageResponse);
    }

    @Autowired
    public void setDataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor(DataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor dataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor) {
        this.dataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor = dataCompanyIprWorkCopyrightWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor(DataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor) {
        this.dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor = dataCompanyIprWorkCopyrightWrapWarehouseCommandExecutor;
    }
}
