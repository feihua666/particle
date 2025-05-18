package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyShareholderWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyShareholderWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyShareholderExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业股东入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyShareholderWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyShareholderWarehouseCommandExecutor dataCompanyShareholderWarehouseCommandExecutor;

    /**
     * 企业股东入库
     * @param dataCompanyShareholderExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyShareholderExWarehouseVO> dataCompanyShareholderExWarehouseVOPageResponse) {
        List<DataCompanyShareholderExWarehouseVO> dataCompanyShareholderExWarehouseVOs = dataCompanyShareholderExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyShareholderExWarehouseVOs)) {
            for (DataCompanyShareholderExWarehouseVO dataCompanyShareholderExWarehouseVO : dataCompanyShareholderExWarehouseVOs) {
                DataCompanyShareholderWarehouseCommand byDataCompanyShareholderExWarehouseVO = DataCompanyShareholderWarehouseCommand.createByDataCompanyShareholderExWarehouseVO(dataCompanyShareholderExWarehouseVO);
                dataCompanyShareholderWarehouseCommandExecutor.warehouse(byDataCompanyShareholderExWarehouseVO);
            }
        }

    }

    @Autowired
    public void setDataCompanyShareholderWarehouseCommandExecutor(DataCompanyShareholderWarehouseCommandExecutor dataCompanyShareholderWarehouseCommandExecutor) {
        this.dataCompanyShareholderWarehouseCommandExecutor = dataCompanyShareholderWarehouseCommandExecutor;
    }
}
