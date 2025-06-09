package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.dict.CurrencyType;
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
                DataCompanyPunishmentWarehouseCommand dataCompanyPunishmentWarehouseCommand = DataCompanyPunishmentWarehouseCommand.createByDataCompanyPunishmentExWarehouseVO(dataCompanyPunishmentExWarehouseVO);
                fillIds(dataCompanyPunishmentWarehouseCommand, dataCompanyPunishmentExWarehouseVO);
                dataCompanyPunishmentWarehouseCommandExecutor.warehouse(dataCompanyPunishmentWarehouseCommand);
            }
        }
    }

    private void fillIds(DataCompanyPunishmentWarehouseCommand dataCompanyPunishmentWarehouseCommand, DataCompanyPunishmentExWarehouseVO dataCompanyPunishmentExWarehouseVO) {
        // 企业id
        if (dataCompanyPunishmentWarehouseCommand.getCompanyId() == null) {
            if (StrUtil.isNotEmpty(dataCompanyPunishmentWarehouseCommand.getCompanyName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanyPunishmentWarehouseCommand.getCompanyName());
                dataCompanyPunishmentWarehouseCommand.setCompanyId(companyId);
            }
        }
        // 法人性质
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyPunishmentWarehouseCommand.getLegalPersonName(),
                dataCompanyPunishmentWarehouseCommand.getLegalPersonCompanyId(),
                dataCompanyPunishmentWarehouseCommand.getLegalPersonCompanyPersonId(),
                dataCompanyPunishmentWarehouseCommand.getIsLegalPersonNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyPunishmentWarehouseCommand.setLegalPersonCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyPunishmentWarehouseCommand.setLegalPersonCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyPunishmentWarehouseCommand.setIsLegalPersonNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
        if (dataCompanyPunishmentWarehouseCommand.getFineAmountCurrencyDictId() == null) {
            if (StrUtil.isBlank(dataCompanyPunishmentExWarehouseVO.getFineAmountCurrencyDictName())) {
                Long dictId = mappingDictItemGetDictId(dataCompanyPunishmentExWarehouseVO.getFineAmountCurrencyDictName(), CurrencyType.Group.currency_type.groupCode());
                dataCompanyPunishmentWarehouseCommand.setFineAmountCurrencyDictId(dictId);
            }
        }
        if (dataCompanyPunishmentWarehouseCommand.getConfiscateAmountCurrencyDictId() == null) {
            if (StrUtil.isBlank(dataCompanyPunishmentExWarehouseVO.getConfiscateAmountCurrencyDictName())) {
                Long dictId = mappingDictItemGetDictId(dataCompanyPunishmentExWarehouseVO.getConfiscateAmountCurrencyDictName(), CurrencyType.Group.currency_type.groupCode());
                dataCompanyPunishmentWarehouseCommand.setConfiscateAmountCurrencyDictId(dictId);
            }
        }

    }

    @Autowired
    public void setDataCompanyPunishmentWarehouseCommandExecutor(DataCompanyPunishmentWarehouseCommandExecutor dataCompanyPunishmentWarehouseCommandExecutor) {
        this.dataCompanyPunishmentWarehouseCommandExecutor = dataCompanyPunishmentWarehouseCommandExecutor;
    }
}
