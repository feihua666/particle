package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehousewrap.AbstractBaseWrapExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyCourtAnnouncementContentWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyCourtAnnouncementPartyWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyCourtAnnouncementWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyCourtAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业法院公告入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:36
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyCourtAnnouncementWarehouseCommandExecutor dataCompanyCourtAnnouncementWarehouseCommandExecutor;
    private DataCompanyCourtAnnouncementPartyWarehouseCommandExecutor dataCompanyCourtAnnouncementPartyWarehouseCommandExecutor;
    private DataCompanyCourtAnnouncementContentWarehouseCommandExecutor dataCompanyCourtAnnouncementContentWarehouseCommandExecutor;

    /**
     * 企业法院公告入库
     * @param dataCompanyCourtAnnouncementExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> dataCompanyCourtAnnouncementExWarehouseVOPageResponse) {
        List<DataCompanyCourtAnnouncementExWarehouseVO> data = dataCompanyCourtAnnouncementExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(data)) {
            for (DataCompanyCourtAnnouncementExWarehouseVO dataCompanyCourtAnnouncementExWarehouseVO : data) {
                DataCompanyCourtAnnouncementWarehouseCommand byDataCompanyCourtAnnouncementExWarehouseVO = DataCompanyCourtAnnouncementWarehouseCommand.createByDataCompanyCourtAnnouncementExWarehouseVO(dataCompanyCourtAnnouncementExWarehouseVO);
                SingleResponse<DataCompanyCourtAnnouncementExWarehouseVO> warehouse = dataCompanyCourtAnnouncementWarehouseCommandExecutor.warehouse(byDataCompanyCourtAnnouncementExWarehouseVO);
                if (CollectionUtil.isNotEmpty(dataCompanyCourtAnnouncementExWarehouseVO.getParties())) {
                    for (DataCompanyCourtAnnouncementPartyExWarehouseVO party : dataCompanyCourtAnnouncementExWarehouseVO.getParties()) {
                        DataCompanyCourtAnnouncementPartyWarehouseCommand dataCompanyCourtAnnouncementPartyWarehouseCommand = DataCompanyCourtAnnouncementPartyWarehouseCommand.createByDataCompanyCourtAnnouncementPartyExWarehouseVO(party);
                        dataCompanyCourtAnnouncementPartyWarehouseCommand.setCompanyCourtAnnouncementId(warehouse.getData().getId());
                        fillPartyIds(dataCompanyCourtAnnouncementPartyWarehouseCommand, party);
                        dataCompanyCourtAnnouncementPartyWarehouseCommandExecutor.warehouse(dataCompanyCourtAnnouncementPartyWarehouseCommand);
                    }
                }
                if (dataCompanyCourtAnnouncementExWarehouseVO.getContent() != null) {
                    DataCompanyCourtAnnouncementContentWarehouseCommand byDataCompanyCourtAnnouncementContentExWarehouseVO = DataCompanyCourtAnnouncementContentWarehouseCommand.createByDataCompanyCourtAnnouncementContentExWarehouseVO(dataCompanyCourtAnnouncementExWarehouseVO.getContent());
                    byDataCompanyCourtAnnouncementContentExWarehouseVO.setCompanyCourtAnnouncementId(warehouse.getData().getId());
                    dataCompanyCourtAnnouncementContentWarehouseCommandExecutor.warehouse(byDataCompanyCourtAnnouncementContentExWarehouseVO);
                }
            }

        }
    }
    /**
     * 当事人相关id设置
     * @param dataCompanyCourtAnnouncementPartyWarehouseCommand
     * @param party
     */
    private void fillPartyIds(DataCompanyCourtAnnouncementPartyWarehouseCommand dataCompanyCourtAnnouncementPartyWarehouseCommand,
                              DataCompanyCourtAnnouncementPartyExWarehouseVO party) {
        // 当事人性质
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyCourtAnnouncementPartyWarehouseCommand.getPartyName(),
                dataCompanyCourtAnnouncementPartyWarehouseCommand.getPartyCompanyId(),
                dataCompanyCourtAnnouncementPartyWarehouseCommand.getPartyCompanyPersonId(),
                dataCompanyCourtAnnouncementPartyWarehouseCommand.getIsPartyNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyCourtAnnouncementPartyWarehouseCommand.setPartyCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyCourtAnnouncementPartyWarehouseCommand.setPartyCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyCourtAnnouncementPartyWarehouseCommand.setIsPartyNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementWarehouseCommandExecutor(DataCompanyCourtAnnouncementWarehouseCommandExecutor dataCompanyCourtAnnouncementWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementWarehouseCommandExecutor = dataCompanyCourtAnnouncementWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyCourtAnnouncementPartyWarehouseCommandExecutor(DataCompanyCourtAnnouncementPartyWarehouseCommandExecutor dataCompanyCourtAnnouncementPartyWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementPartyWarehouseCommandExecutor = dataCompanyCourtAnnouncementPartyWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyCourtAnnouncementContentWarehouseCommandExecutor(DataCompanyCourtAnnouncementContentWarehouseCommandExecutor dataCompanyCourtAnnouncementContentWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentWarehouseCommandExecutor = dataCompanyCourtAnnouncementContentWarehouseCommandExecutor;
    }
}
