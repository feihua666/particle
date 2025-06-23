package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.dict.CurrencyType;
import com.particle.data.app.company.executor.warehouse.DataCompanyEquityPledgeWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEquityPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEquityPledgeExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业股权出质入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyEquityPledgeWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyEquityPledgeWarehouseCommandExecutor dataCompanyEquityPledgeWarehouseCommandExecutor;

    /**
     * 企业股权出质入库
     * @param dataCompanyEquityPledgeExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyEquityPledgeExWarehouseVO> dataCompanyEquityPledgeExWarehouseVOPageResponse) {
        List<DataCompanyEquityPledgeExWarehouseVO> dataCompanyEquityPledgeExWarehouseVOs = dataCompanyEquityPledgeExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyEquityPledgeExWarehouseVOs)) {
            for (DataCompanyEquityPledgeExWarehouseVO dataCompanyEquityPledgeExWarehouseVO : dataCompanyEquityPledgeExWarehouseVOs) {
                DataCompanyEquityPledgeWarehouseCommand dataCompanyEquityPledgeWarehouseCommand = DataCompanyEquityPledgeWarehouseCommand.createByDataCompanyEquityPledgeExWarehouseVO(dataCompanyEquityPledgeExWarehouseVO);
                fillIds(dataCompanyEquityPledgeWarehouseCommand, dataCompanyEquityPledgeExWarehouseVO);
                dataCompanyEquityPledgeWarehouseCommandExecutor.warehouse(dataCompanyEquityPledgeWarehouseCommand);
            }
        }
    }

    private void fillIds(DataCompanyEquityPledgeWarehouseCommand dataCompanyEquityPledgeWarehouseCommand, DataCompanyEquityPledgeExWarehouseVO dataCompanyEquityPledgeExWarehouseVO) {

        // 出质人
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyEquityPledgeWarehouseCommand.getPledgor(),
                dataCompanyEquityPledgeWarehouseCommand.getPledgorCompanyId(),
                dataCompanyEquityPledgeWarehouseCommand.getPledgorCompanyPersonId(),
                dataCompanyEquityPledgeWarehouseCommand.getIsPledgorNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyEquityPledgeWarehouseCommand.setPledgorCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyEquityPledgeWarehouseCommand.setPledgorCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyEquityPledgeWarehouseCommand.setIsPledgorNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
        // 质权人
        legalNaturePerson = checkNaturePerson(dataCompanyEquityPledgeWarehouseCommand.getPledgee(),
                dataCompanyEquityPledgeWarehouseCommand.getPledgeeCompanyId(),
                dataCompanyEquityPledgeWarehouseCommand.getPledgeeCompanyPersonId(),
                dataCompanyEquityPledgeWarehouseCommand.getIsPledgeeNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyEquityPledgeWarehouseCommand.setPledgeeCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyEquityPledgeWarehouseCommand.setPledgeeCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyEquityPledgeWarehouseCommand.setIsPledgeeNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }

    @Autowired
    public void setDataCompanyEquityPledgeWarehouseCommandExecutor(DataCompanyEquityPledgeWarehouseCommandExecutor dataCompanyEquityPledgeWarehouseCommandExecutor) {
        this.dataCompanyEquityPledgeWarehouseCommandExecutor = dataCompanyEquityPledgeWarehouseCommandExecutor;
    }
}
