package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业裁判文书入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:36
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyJudgmentDocumentWarehouseCommandExecutor dataCompanyJudgmentDocumentWarehouseCommandExecutor;
    private DataCompanyJudgmentDocumentPartyWarehouseCommandExecutor dataCompanyJudgmentDocumentPartyWarehouseCommandExecutor;
    private DataCompanyJudgmentDocumentContentWarehouseCommandExecutor dataCompanyJudgmentDocumentContentWarehouseCommandExecutor;

    /**
     * 企业裁判文书入库
     * @param dataCompanyJudgmentDocumentExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> dataCompanyJudgmentDocumentExWarehouseVOPageResponse) {
        List<DataCompanyJudgmentDocumentExWarehouseVO> data = dataCompanyJudgmentDocumentExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(data)) {
            for (DataCompanyJudgmentDocumentExWarehouseVO dataCompanyJudgmentDocumentExWarehouseVO : data) {
                DataCompanyJudgmentDocumentWarehouseCommand byDataCompanyJudgmentDocumentExWarehouseVO = DataCompanyJudgmentDocumentWarehouseCommand.createByDataCompanyJudgmentDocumentExWarehouseVO(dataCompanyJudgmentDocumentExWarehouseVO);
                SingleResponse<DataCompanyJudgmentDocumentExWarehouseVO> warehouse = dataCompanyJudgmentDocumentWarehouseCommandExecutor.warehouse(byDataCompanyJudgmentDocumentExWarehouseVO);
                if (CollectionUtil.isNotEmpty(dataCompanyJudgmentDocumentExWarehouseVO.getParties())) {
                    for (DataCompanyJudgmentDocumentPartyExWarehouseVO party : dataCompanyJudgmentDocumentExWarehouseVO.getParties()) {
                        DataCompanyJudgmentDocumentPartyWarehouseCommand byDataCompanyJudgmentDocumentPartyExWarehouseVO = DataCompanyJudgmentDocumentPartyWarehouseCommand.createByDataCompanyJudgmentDocumentPartyExWarehouseVO(party);
                        byDataCompanyJudgmentDocumentPartyExWarehouseVO.setCompanyJudgmentDocumentId(warehouse.getData().getId());
                        dataCompanyJudgmentDocumentPartyWarehouseCommandExecutor.warehouse(byDataCompanyJudgmentDocumentPartyExWarehouseVO);
                    }
                }
                if (dataCompanyJudgmentDocumentExWarehouseVO.getContent() != null) {
                    DataCompanyJudgmentDocumentContentWarehouseCommand byDataCompanyJudgmentDocumentContentExWarehouseVO = DataCompanyJudgmentDocumentContentWarehouseCommand.createByDataCompanyJudgmentDocumentContentExWarehouseVO(dataCompanyJudgmentDocumentExWarehouseVO.getContent());
                    byDataCompanyJudgmentDocumentContentExWarehouseVO.setCompanyJudgmentDocumentId(warehouse.getData().getId());
                    dataCompanyJudgmentDocumentContentWarehouseCommandExecutor.warehouse(byDataCompanyJudgmentDocumentContentExWarehouseVO);
                }
            }

        }
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentWarehouseCommandExecutor(DataCompanyJudgmentDocumentWarehouseCommandExecutor dataCompanyJudgmentDocumentWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentWarehouseCommandExecutor = dataCompanyJudgmentDocumentWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyJudgmentDocumentPartyWarehouseCommandExecutor(DataCompanyJudgmentDocumentPartyWarehouseCommandExecutor dataCompanyJudgmentDocumentPartyWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentPartyWarehouseCommandExecutor = dataCompanyJudgmentDocumentPartyWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyJudgmentDocumentContentWarehouseCommandExecutor(DataCompanyJudgmentDocumentContentWarehouseCommandExecutor dataCompanyJudgmentDocumentContentWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentContentWarehouseCommandExecutor = dataCompanyJudgmentDocumentContentWarehouseCommandExecutor;
    }
}
