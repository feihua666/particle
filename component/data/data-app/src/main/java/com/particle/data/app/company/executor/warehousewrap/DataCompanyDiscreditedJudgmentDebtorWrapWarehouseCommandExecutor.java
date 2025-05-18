package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业失信被执行人入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyDiscreditedJudgmentDebtorWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor;

    /**
     * 企业失信被执行人入库
     * @param dataCompanyDiscreditedJudgmentDebtorExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> dataCompanyDiscreditedJudgmentDebtorExWarehouseVOPageResponse) {
        List<DataCompanyDiscreditedJudgmentDebtorExWarehouseVO> dataCompanyDiscreditedJudgmentDebtorExWarehouseVOs = dataCompanyDiscreditedJudgmentDebtorExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyDiscreditedJudgmentDebtorExWarehouseVOs)) {
            for (DataCompanyDiscreditedJudgmentDebtorExWarehouseVO dataCompanyDiscreditedJudgmentDebtorExWarehouseVO : dataCompanyDiscreditedJudgmentDebtorExWarehouseVOs) {
                DataCompanyDiscreditedJudgmentDebtorWarehouseCommand byDataCompanyDiscreditedJudgmentDebtorExWarehouseVO = DataCompanyDiscreditedJudgmentDebtorWarehouseCommand.createByDataCompanyDiscreditedJudgmentDebtorExWarehouseVO(dataCompanyDiscreditedJudgmentDebtorExWarehouseVO);
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor.warehouse(byDataCompanyDiscreditedJudgmentDebtorExWarehouseVO);
            }
        }

    }

    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor;
    }
}
