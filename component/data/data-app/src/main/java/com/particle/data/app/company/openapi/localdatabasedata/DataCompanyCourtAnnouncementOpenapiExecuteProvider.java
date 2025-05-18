package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyCourtAnnouncementWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业法院公告信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyCourtAnnouncementOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor;
    private DataCompanyCourtAnnouncementWrapWarehouseCommandExecutor dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_court_announcement".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyCourtAnnouncementExWarehouseQueryCommand dataCompanyCourtAnnouncementExWarehouseQueryCommand = (DataCompanyCourtAnnouncementExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyCourtAnnouncementExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> response = (PageResponse<DataCompanyCourtAnnouncementExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor(DataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor = dataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementWrapWarehouseCommandExecutor(DataCompanyCourtAnnouncementWrapWarehouseCommandExecutor dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor = dataCompanyCourtAnnouncementWrapWarehouseCommandExecutor;
    }
}
