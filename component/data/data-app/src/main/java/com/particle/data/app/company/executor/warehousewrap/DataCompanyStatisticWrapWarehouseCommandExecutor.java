package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyStatisticWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyStatisticWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyStatisticExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业统计信息入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-04 16:02:30
 */
@Component
@Validated
public class DataCompanyStatisticWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyStatisticWarehouseCommandExecutor dataCompanyStatisticWarehouseCommandExecutor;


    /**
     * 企业统计信息入库
     * @param dataCompanyStatisticExWarehouseVOSingleResponse
     */
    public void warehouse(SingleResponse<DataCompanyStatisticExWarehouseVO> dataCompanyStatisticExWarehouseVOSingleResponse) {
        DataCompanyStatisticExWarehouseVO dataCompanyStatisticExWarehouseVO = dataCompanyStatisticExWarehouseVOSingleResponse.getData();
        if (dataCompanyStatisticExWarehouseVO != null) {
            DataCompanyStatisticWarehouseCommand dataCompanyStatisticWarehouseCommand = DataCompanyStatisticWarehouseCommand.createByDataCompanyStatisticExWarehouseVO(dataCompanyStatisticExWarehouseVO);
            dataCompanyStatisticWarehouseCommandExecutor.warehouse(dataCompanyStatisticWarehouseCommand);
        }
    }

    @Autowired
    public void setDataCompanyStatisticWarehouseCommandExecutor(DataCompanyStatisticWarehouseCommandExecutor dataCompanyStatisticWarehouseCommandExecutor) {
        this.dataCompanyStatisticWarehouseCommandExecutor = dataCompanyStatisticWarehouseCommandExecutor;
    }
}
