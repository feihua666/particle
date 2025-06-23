package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.dict.CurrencyType;
import com.particle.data.app.company.executor.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementContentWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementPartyWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDeliveryAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业送达公告入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyDeliveryAnnouncementWarehouseCommandExecutor dataCompanyDeliveryAnnouncementWarehouseCommandExecutor;
    private DataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor dataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor;
    private DataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor dataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor;

    /**
     * 企业开庭公告入库
     * @param dataCompanyDeliveryAnnouncementExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> dataCompanyDeliveryAnnouncementExWarehouseVOPageResponse) {
        List<DataCompanyDeliveryAnnouncementExWarehouseVO> data = dataCompanyDeliveryAnnouncementExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(data)) {
            for (DataCompanyDeliveryAnnouncementExWarehouseVO dataCompanyDeliveryAnnouncementExWarehouseVO : data) {
                DataCompanyDeliveryAnnouncementWarehouseCommand dataCompanyDeliveryAnnouncementWarehouseCommand = DataCompanyDeliveryAnnouncementWarehouseCommand.createByDataCompanyDeliveryAnnouncementExWarehouseVO(dataCompanyDeliveryAnnouncementExWarehouseVO);
                SingleResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> warehouse = dataCompanyDeliveryAnnouncementWarehouseCommandExecutor.warehouse(dataCompanyDeliveryAnnouncementWarehouseCommand);
                if (CollectionUtil.isNotEmpty(dataCompanyDeliveryAnnouncementExWarehouseVO.getParties())) {
                    for (DataCompanyDeliveryAnnouncementPartyExWarehouseVO party : dataCompanyDeliveryAnnouncementExWarehouseVO.getParties()) {
                        DataCompanyDeliveryAnnouncementPartyWarehouseCommand dataCompanyDeliveryAnnouncementPartyWarehouseCommand = DataCompanyDeliveryAnnouncementPartyWarehouseCommand.createByDataCompanyDeliveryAnnouncementPartyExWarehouseVO(party);
                        dataCompanyDeliveryAnnouncementPartyWarehouseCommand.setCompanyDeliveryAnnouncementId(warehouse.getData().getId());
                        fillPartyIds(dataCompanyDeliveryAnnouncementPartyWarehouseCommand, party);
                        dataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor.warehouse(dataCompanyDeliveryAnnouncementPartyWarehouseCommand);
                    }
                }
                if (dataCompanyDeliveryAnnouncementExWarehouseVO.getContent() != null) {
                    DataCompanyDeliveryAnnouncementContentWarehouseCommand byDataCompanyDeliveryAnnouncementContentExWarehouseVO = DataCompanyDeliveryAnnouncementContentWarehouseCommand.createByDataCompanyDeliveryAnnouncementContentExWarehouseVO(dataCompanyDeliveryAnnouncementExWarehouseVO.getContent());
                    byDataCompanyDeliveryAnnouncementContentExWarehouseVO.setCompanyDeliveryAnnouncementId(warehouse.getData().getId());
                    dataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor.warehouse(byDataCompanyDeliveryAnnouncementContentExWarehouseVO);
                }
            }

        }
    }

    /**
     * 当事人相关id设置
     * @param dataCompanyDeliveryAnnouncementPartyWarehouseCommand
     * @param party
     */
    private void fillPartyIds(DataCompanyDeliveryAnnouncementPartyWarehouseCommand dataCompanyDeliveryAnnouncementPartyWarehouseCommand,
                              DataCompanyDeliveryAnnouncementPartyExWarehouseVO party) {
        // 当事人性质
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyDeliveryAnnouncementPartyWarehouseCommand.getPartyName(),
                dataCompanyDeliveryAnnouncementPartyWarehouseCommand.getPartyCompanyId(),
                dataCompanyDeliveryAnnouncementPartyWarehouseCommand.getPartyCompanyPersonId(),
                dataCompanyDeliveryAnnouncementPartyWarehouseCommand.getIsPartyNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyDeliveryAnnouncementPartyWarehouseCommand.setPartyCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyDeliveryAnnouncementPartyWarehouseCommand.setPartyCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyDeliveryAnnouncementPartyWarehouseCommand.setIsPartyNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementWarehouseCommandExecutor dataCompanyDeliveryAnnouncementWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor dataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementPartyWarehouseCommandExecutor;
    }

    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor dataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementContentWarehouseCommandExecutor;
    }
}
