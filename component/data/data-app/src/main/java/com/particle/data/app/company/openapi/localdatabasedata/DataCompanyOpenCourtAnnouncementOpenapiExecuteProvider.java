package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业开庭公告信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyOpenCourtAnnouncementOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {


    private DataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor;
    private DataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor;

    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_open_court_announcement".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业id
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand = (DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> response = (PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor;
    }
}
