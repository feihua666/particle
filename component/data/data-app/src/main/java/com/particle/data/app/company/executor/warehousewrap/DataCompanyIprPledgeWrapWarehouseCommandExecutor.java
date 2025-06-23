package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.dict.CurrencyType;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPledgeWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPledgeWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPledgeExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业知识产权出质入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyIprPledgeWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprPledgeWarehouseCommandExecutor dataCompanyIprPledgeWarehouseCommandExecutor;

    /**
     * 企业知识产权出质入库
     * @param dataCompanyIprPledgeExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyIprPledgeExWarehouseVO> dataCompanyIprPledgeExWarehouseVOPageResponse) {
        List<DataCompanyIprPledgeExWarehouseVO> dataCompanyIprPledgeExWarehouseVOs = dataCompanyIprPledgeExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyIprPledgeExWarehouseVOs)) {
            for (DataCompanyIprPledgeExWarehouseVO dataCompanyIprPledgeExWarehouseVO : dataCompanyIprPledgeExWarehouseVOs) {
                DataCompanyIprPledgeWarehouseCommand dataCompanyIprPledgeWarehouseCommand = DataCompanyIprPledgeWarehouseCommand.createByDataCompanyIprPledgeExWarehouseVO(dataCompanyIprPledgeExWarehouseVO);
                fillIds(dataCompanyIprPledgeWarehouseCommand, dataCompanyIprPledgeExWarehouseVO);
                dataCompanyIprPledgeWarehouseCommandExecutor.warehouse(dataCompanyIprPledgeWarehouseCommand);
            }
        }
    }

    private void fillIds(DataCompanyIprPledgeWarehouseCommand dataCompanyIprPledgeWarehouseCommand, DataCompanyIprPledgeExWarehouseVO dataCompanyIprPledgeExWarehouseVO) {
        // 出质人
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyIprPledgeWarehouseCommand.getPledgor(),
                dataCompanyIprPledgeWarehouseCommand.getPledgorCompanyId(),
                dataCompanyIprPledgeWarehouseCommand.getPledgorCompanyPersonId(),
                dataCompanyIprPledgeWarehouseCommand.getIsPledgorNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyIprPledgeWarehouseCommand.setPledgorCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyIprPledgeWarehouseCommand.setPledgorCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyIprPledgeWarehouseCommand.setIsPledgorNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
        // 质权人
        legalNaturePerson = checkNaturePerson(dataCompanyIprPledgeWarehouseCommand.getPledgee(),
                dataCompanyIprPledgeWarehouseCommand.getPledgeeCompanyId(),
                dataCompanyIprPledgeWarehouseCommand.getPledgeeCompanyPersonId(),
                dataCompanyIprPledgeWarehouseCommand.getIsPledgeeNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyIprPledgeWarehouseCommand.setPledgeeCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyIprPledgeWarehouseCommand.setPledgeeCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyIprPledgeWarehouseCommand.setIsPledgeeNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }

    }

    @Autowired
    public void setDataCompanyIprPledgeWarehouseCommandExecutor(DataCompanyIprPledgeWarehouseCommandExecutor dataCompanyIprPledgeWarehouseCommandExecutor) {
        this.dataCompanyIprPledgeWarehouseCommandExecutor = dataCompanyIprPledgeWarehouseCommandExecutor;
    }
}
