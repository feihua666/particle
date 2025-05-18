package com.particle.data.app.company.executor.warehousewrap;

import com.particle.data.app.company.executor.warehouse.DataCompanyBasicWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyBasicWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyBasicExWarehouseVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 企业基本信息入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyBasicWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyBasicWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor;

    /**
     * 企业基本信息入库
     * @param dataCompanyBasicExWarehouseVOSingleResponse
     */
    public void warehouse(SingleResponse<DataCompanyBasicExWarehouseVO> dataCompanyBasicExWarehouseVOSingleResponse) {
        DataCompanyBasicExWarehouseVO dataCompanyBasicExWarehouseVO = dataCompanyBasicExWarehouseVOSingleResponse.getData();
        if (dataCompanyBasicExWarehouseVO != null) {
            DataCompanyBasicWarehouseCommand byDataCompanyBasicExWarehouseVO = DataCompanyBasicWarehouseCommand.createByDataCompanyBasicExWarehouseVO(dataCompanyBasicExWarehouseVO);
            dataCompanyBasicWarehouseCommandExecutor.warehouse(byDataCompanyBasicExWarehouseVO);
        }
    }


    @Autowired
    public void setDataCompanyBasicWarehouseCommandExecutor(DataCompanyBasicWarehouseCommandExecutor dataCompanyBasicWarehouseCommandExecutor) {
        this.dataCompanyBasicWarehouseCommandExecutor = dataCompanyBasicWarehouseCommandExecutor;
    }
}
