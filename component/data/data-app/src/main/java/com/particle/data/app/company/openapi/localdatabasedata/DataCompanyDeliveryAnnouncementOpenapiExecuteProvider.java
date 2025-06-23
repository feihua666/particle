package com.particle.data.app.company.openapi.localdatabasedata;

import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.representation.exwarehousewrap.DataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.DataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.openapi.collect.OpenapiContext;
import com.particle.global.openapi.endpoint.command.OpenapiCommand;
import com.particle.global.openapi.endpoint.command.OpenapiWarehouseCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 企业送达公告信息开放接口实现
 * </p>
 *
 * @author yangwei
 * @since 2025-04-25 13:03:44
 */
@Component
public class DataCompanyDeliveryAnnouncementOpenapiExecuteProvider extends AbstractDataCompanyOpenapiExecuteProvider {

    private DataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor;
    private DataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor;


    @Override
    public boolean supportApi(String apiCode,String apiVersion) {
        return "company_delivery_announcement".equals(apiCode) && StrUtil.isEmpty(apiVersion);
    }

    @Override
    public PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> execute(OpenapiCommand openapiCommand, OpenapiContext openapiContext) {
        // 企业主体
        DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand = (DataCompanyExWarehouseQueryCommand) openapiCommand.getParam();
        DataCompanyDeliveryAnnouncementExWarehouseQueryCommand dataCompanyDeliveryAnnouncementExWarehouseQueryCommand = (DataCompanyDeliveryAnnouncementExWarehouseQueryCommand)openapiCommand.getEx1Param();

        return dataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand, dataCompanyDeliveryAnnouncementExWarehouseQueryCommand);
    }

    @Override
    public boolean supportWareHouse() {
        return true;
    }

    @Override
    public void warehouse(OpenapiWarehouseCommand warehouseCommand,OpenapiCommand openapiCommand,  OpenapiContext openapiContext) {
        PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> response = (PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO>) warehouseCommand.getParam();
        dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor.warehouse(response);
    }

    @Autowired
    public void setDataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor;
    }
}
