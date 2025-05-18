package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyPunishmentWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPunishmentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业行政处罚入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyPunishmentWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyPunishmentWarehouseCommandExecutor dataCompanyPunishmentWarehouseCommandExecutor;

    /**
     * 企业行政处罚入库
     * @param dataCompanyPunishmentExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyPunishmentExWarehouseVO> dataCompanyPunishmentExWarehouseVOPageResponse) {
        List<DataCompanyPunishmentExWarehouseVO> dataCompanyPunishmentExWarehouseVOs = dataCompanyPunishmentExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyPunishmentExWarehouseVOs)) {
            for (DataCompanyPunishmentExWarehouseVO dataCompanyPunishmentExWarehouseVO : dataCompanyPunishmentExWarehouseVOs) {
                DataCompanyPunishmentWarehouseCommand byDataCompanyPunishmentExWarehouseVO = DataCompanyPunishmentWarehouseCommand.createByDataCompanyPunishmentExWarehouseVO(dataCompanyPunishmentExWarehouseVO);
                dataCompanyPunishmentWarehouseCommandExecutor.warehouse(byDataCompanyPunishmentExWarehouseVO);
            }
        }

    }

    @Autowired
    public void setDataCompanyPunishmentWarehouseCommandExecutor(DataCompanyPunishmentWarehouseCommandExecutor dataCompanyPunishmentWarehouseCommandExecutor) {
        this.dataCompanyPunishmentWarehouseCommandExecutor = dataCompanyPunishmentWarehouseCommandExecutor;
    }
}
