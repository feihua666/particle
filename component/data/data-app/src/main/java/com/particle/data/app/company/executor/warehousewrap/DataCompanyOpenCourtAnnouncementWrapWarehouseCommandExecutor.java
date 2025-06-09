package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyOpenCourtAnnouncementWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyOpenCourtAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业开庭公告入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:36
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyOpenCourtAnnouncementWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWarehouseCommandExecutor;
    private DataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor;
    private DataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor;

    /**
     * 企业开庭公告入库
     * @param dataCompanyOpenCourtAnnouncementExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> dataCompanyOpenCourtAnnouncementExWarehouseVOPageResponse) {
        List<DataCompanyOpenCourtAnnouncementExWarehouseVO> data = dataCompanyOpenCourtAnnouncementExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(data)) {
            for (DataCompanyOpenCourtAnnouncementExWarehouseVO dataCompanyOpenCourtAnnouncementExWarehouseVO : data) {
                DataCompanyOpenCourtAnnouncementWarehouseCommand dataCompanyOpenCourtAnnouncementWarehouseCommand = DataCompanyOpenCourtAnnouncementWarehouseCommand.createByDataCompanyOpenCourtAnnouncementExWarehouseVO(dataCompanyOpenCourtAnnouncementExWarehouseVO);
                SingleResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> warehouse = dataCompanyOpenCourtAnnouncementWarehouseCommandExecutor.warehouse(dataCompanyOpenCourtAnnouncementWarehouseCommand);
                if (CollectionUtil.isNotEmpty(dataCompanyOpenCourtAnnouncementExWarehouseVO.getParties())) {
                    for (DataCompanyOpenCourtAnnouncementPartyExWarehouseVO party : dataCompanyOpenCourtAnnouncementExWarehouseVO.getParties()) {
                        DataCompanyOpenCourtAnnouncementPartyWarehouseCommand dataCompanyOpenCourtAnnouncementPartyWarehouseCommand = DataCompanyOpenCourtAnnouncementPartyWarehouseCommand.createByDataCompanyOpenCourtAnnouncementPartyExWarehouseVO(party);
                        dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.setCompanyOpenCourtAnnouncementId(warehouse.getData().getId());
                        fillPartyIds(dataCompanyOpenCourtAnnouncementPartyWarehouseCommand, party);
                        dataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor.warehouse(dataCompanyOpenCourtAnnouncementPartyWarehouseCommand);
                    }
                }
                if (dataCompanyOpenCourtAnnouncementExWarehouseVO.getContent() != null) {
                    DataCompanyOpenCourtAnnouncementContentWarehouseCommand byDataCompanyOpenCourtAnnouncementContentExWarehouseVO = DataCompanyOpenCourtAnnouncementContentWarehouseCommand.createByDataCompanyOpenCourtAnnouncementContentExWarehouseVO(dataCompanyOpenCourtAnnouncementExWarehouseVO.getContent());
                    byDataCompanyOpenCourtAnnouncementContentExWarehouseVO.setCompanyOpenCourtAnnouncementId(warehouse.getData().getId());
                    dataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor.warehouse(byDataCompanyOpenCourtAnnouncementContentExWarehouseVO);
                }
            }

        }
    }

    /**
     * 当事人相关id设置
     * @param dataCompanyOpenCourtAnnouncementPartyWarehouseCommand
     * @param party
     */
    private void fillPartyIds(DataCompanyOpenCourtAnnouncementPartyWarehouseCommand dataCompanyOpenCourtAnnouncementPartyWarehouseCommand,
                              DataCompanyOpenCourtAnnouncementPartyExWarehouseVO party) {
        // 当事人性质
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.getPartyName(),
                dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.getPartyCompanyId(),
                dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.getPartyCompanyPersonId(),
                dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.getIsPartyNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.setPartyCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.setPartyCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyOpenCourtAnnouncementPartyWarehouseCommand.setIsPartyNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementPartyWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementContentWarehouseCommandExecutor;
    }
}
