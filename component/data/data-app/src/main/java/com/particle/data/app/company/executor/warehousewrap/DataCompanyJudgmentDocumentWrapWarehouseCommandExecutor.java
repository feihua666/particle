package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDocumentWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentContentWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentPartyWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDocumentWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
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
                DataCompanyJudgmentDocumentWarehouseCommand dataCompanyJudgmentDocumentWarehouseCommand = DataCompanyJudgmentDocumentWarehouseCommand.createByDataCompanyJudgmentDocumentExWarehouseVO(dataCompanyJudgmentDocumentExWarehouseVO);
                fillIds(dataCompanyJudgmentDocumentWarehouseCommand, dataCompanyJudgmentDocumentExWarehouseVO);
                SingleResponse<DataCompanyJudgmentDocumentExWarehouseVO> warehouse = dataCompanyJudgmentDocumentWarehouseCommandExecutor.warehouse(dataCompanyJudgmentDocumentWarehouseCommand);
                if (CollectionUtil.isNotEmpty(dataCompanyJudgmentDocumentExWarehouseVO.getParties())) {
                    for (DataCompanyJudgmentDocumentPartyExWarehouseVO party : dataCompanyJudgmentDocumentExWarehouseVO.getParties()) {
                        DataCompanyJudgmentDocumentPartyWarehouseCommand dataCompanyJudgmentDocumentPartyWarehouseCommand = DataCompanyJudgmentDocumentPartyWarehouseCommand.createByDataCompanyJudgmentDocumentPartyExWarehouseVO(party);
                        dataCompanyJudgmentDocumentPartyWarehouseCommand.setCompanyJudgmentDocumentId(warehouse.getData().getId());
                        fillPartyIds(dataCompanyJudgmentDocumentPartyWarehouseCommand, party);
                        dataCompanyJudgmentDocumentPartyWarehouseCommandExecutor.warehouse(dataCompanyJudgmentDocumentPartyWarehouseCommand);
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
    private void fillIds(DataCompanyJudgmentDocumentWarehouseCommand dataCompanyJudgmentDocumentWarehouseCommand, DataCompanyJudgmentDocumentExWarehouseVO dataCompanyJudgmentDocumentExWarehouseVO) {
        // 法院id
        if (dataCompanyJudgmentDocumentWarehouseCommand.getCaseCourtCompanyId() == null) {
            Long companyId = warehouseCompanyGetCompanyId(dataCompanyJudgmentDocumentExWarehouseVO.getCaseCourtName());
            dataCompanyJudgmentDocumentWarehouseCommand.setCaseCourtCompanyId(companyId);
        }
    }
    /**
     * 当事人相关id设置
     * @param dataCompanyJudgmentDocumentPartyWarehouseCommand
     * @param party
     */
    private void fillPartyIds(DataCompanyJudgmentDocumentPartyWarehouseCommand dataCompanyJudgmentDocumentPartyWarehouseCommand,
                              DataCompanyJudgmentDocumentPartyExWarehouseVO party) {
        // 当事人性质
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyJudgmentDocumentPartyWarehouseCommand.getPartyName(),
                dataCompanyJudgmentDocumentPartyWarehouseCommand.getPartyCompanyId(),
                dataCompanyJudgmentDocumentPartyWarehouseCommand.getPartyCompanyPersonId(),
                dataCompanyJudgmentDocumentPartyWarehouseCommand.getIsPartyNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyJudgmentDocumentPartyWarehouseCommand.setPartyCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyJudgmentDocumentPartyWarehouseCommand.setPartyCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyJudgmentDocumentPartyWarehouseCommand.setIsPartyNaturalPerson(legalNaturePerson.getIsNaturePerson());
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
