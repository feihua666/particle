package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyRestrictHighConsumePartyWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyRestrictHighConsumeWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumePartyWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyRestrictHighConsumeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业限制高消费入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:24
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor{

    private DataCompanyRestrictHighConsumeWarehouseCommandExecutor dataCompanyRestrictHighConsumeWarehouseCommandExecutor;
    private DataCompanyRestrictHighConsumePartyWarehouseCommandExecutor dataCompanyRestrictHighConsumePartyWarehouseCommandExecutor;


    /**
     * 企业限制高消费入库
     * @param dataCompanyRestrictHighConsumeExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> dataCompanyRestrictHighConsumeExWarehouseVOPageResponse)
    {
        List<DataCompanyRestrictHighConsumeExWarehouseVO> data = dataCompanyRestrictHighConsumeExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(data)) {
            for (DataCompanyRestrictHighConsumeExWarehouseVO dataCompanyRestrictHighConsumeExWarehouseVO : data) {
                DataCompanyRestrictHighConsumeWarehouseCommand byDataCompanyRestrictHighConsumeExWarehouseVO = DataCompanyRestrictHighConsumeWarehouseCommand.createByDataCompanyRestrictHighConsumeExWarehouseVO(dataCompanyRestrictHighConsumeExWarehouseVO);
                SingleResponse<DataCompanyRestrictHighConsumeExWarehouseVO> warehouse = dataCompanyRestrictHighConsumeWarehouseCommandExecutor.warehouse(byDataCompanyRestrictHighConsumeExWarehouseVO);
                if (CollectionUtil.isNotEmpty(dataCompanyRestrictHighConsumeExWarehouseVO.getParties())) {
                    for (DataCompanyRestrictHighConsumePartyExWarehouseVO party : dataCompanyRestrictHighConsumeExWarehouseVO.getParties()) {
                        DataCompanyRestrictHighConsumePartyWarehouseCommand byDataCompanyRestrictHighConsumePartyExWarehouseVO = DataCompanyRestrictHighConsumePartyWarehouseCommand.createByDataCompanyRestrictHighConsumePartyExWarehouseVO(party);
                        byDataCompanyRestrictHighConsumePartyExWarehouseVO.setCompanyRestrictHighConsumeId(warehouse.getData().getId());
                        dataCompanyRestrictHighConsumePartyWarehouseCommandExecutor.warehouse(byDataCompanyRestrictHighConsumePartyExWarehouseVO);
                    }
                }
            }

        }
    }

    @Autowired
    public void setDataCompanyRestrictHighConsumeWarehouseCommandExecutor(DataCompanyRestrictHighConsumeWarehouseCommandExecutor dataCompanyRestrictHighConsumeWarehouseCommandExecutor) {
        this.dataCompanyRestrictHighConsumeWarehouseCommandExecutor = dataCompanyRestrictHighConsumeWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyRestrictHighConsumePartyWarehouseCommandExecutor(DataCompanyRestrictHighConsumePartyWarehouseCommandExecutor dataCompanyRestrictHighConsumePartyWarehouseCommandExecutor) {
        this.dataCompanyRestrictHighConsumePartyWarehouseCommandExecutor = dataCompanyRestrictHighConsumePartyWarehouseCommandExecutor;
    }
}
