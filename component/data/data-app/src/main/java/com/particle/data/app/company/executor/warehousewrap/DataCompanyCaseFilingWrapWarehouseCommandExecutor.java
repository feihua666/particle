package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyCaseFilingPartyWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyCaseFilingWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingPartyWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCaseFilingWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 立案信息入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:24
 */
@Component
@Validated
public class DataCompanyCaseFilingWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor{

    private DataCompanyCaseFilingWarehouseCommandExecutor dataCompanyCaseFilingWarehouseCommandExecutor;
    private DataCompanyCaseFilingPartyWarehouseCommandExecutor dataCompanyCaseFilingPartyWarehouseCommandExecutor;


    /**
     * 立案信息入库
     * @param dataCompanyCaseFilingExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyCaseFilingExWarehouseVO> dataCompanyCaseFilingExWarehouseVOPageResponse)
    {
        List<DataCompanyCaseFilingExWarehouseVO> data = dataCompanyCaseFilingExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(data)) {
            for (DataCompanyCaseFilingExWarehouseVO dataCompanyCaseFilingExWarehouseVO : data) {
                DataCompanyCaseFilingWarehouseCommand byDataCompanyCaseFilingExWarehouseVO = DataCompanyCaseFilingWarehouseCommand.createByDataCompanyCaseFilingExWarehouseVO(dataCompanyCaseFilingExWarehouseVO);
                SingleResponse<DataCompanyCaseFilingExWarehouseVO> warehouse = dataCompanyCaseFilingWarehouseCommandExecutor.warehouse(byDataCompanyCaseFilingExWarehouseVO);
                if (CollectionUtil.isNotEmpty(dataCompanyCaseFilingExWarehouseVO.getParties())) {
                    for (DataCompanyCaseFilingPartyExWarehouseVO party : dataCompanyCaseFilingExWarehouseVO.getParties()) {
                        DataCompanyCaseFilingPartyWarehouseCommand byDataCompanyCaseFilingPartyExWarehouseVO = DataCompanyCaseFilingPartyWarehouseCommand.createByDataCompanyCaseFilingPartyExWarehouseVO(party);
                        byDataCompanyCaseFilingPartyExWarehouseVO.setCompanyCaseFilingId(warehouse.getData().getId());
                        dataCompanyCaseFilingPartyWarehouseCommandExecutor.warehouse(byDataCompanyCaseFilingPartyExWarehouseVO);
                    }
                }
            }

        }
    }

    @Autowired
    public void setDataCompanyCaseFilingWarehouseCommandExecutor(DataCompanyCaseFilingWarehouseCommandExecutor dataCompanyCaseFilingWarehouseCommandExecutor) {
        this.dataCompanyCaseFilingWarehouseCommandExecutor = dataCompanyCaseFilingWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyCaseFilingPartyWarehouseCommandExecutor(DataCompanyCaseFilingPartyWarehouseCommandExecutor dataCompanyCaseFilingPartyWarehouseCommandExecutor) {
        this.dataCompanyCaseFilingPartyWarehouseCommandExecutor = dataCompanyCaseFilingPartyWarehouseCommandExecutor;
    }
}
