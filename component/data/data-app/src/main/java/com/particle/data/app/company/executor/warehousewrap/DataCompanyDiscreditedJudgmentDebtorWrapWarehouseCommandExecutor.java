package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.dict.CurrencyType;
import com.particle.data.app.company.executor.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyDiscreditedJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDiscreditedJudgmentDebtorExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
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
                DataCompanyDiscreditedJudgmentDebtorWarehouseCommand dataCompanyDiscreditedJudgmentDebtorWarehouseCommand = DataCompanyDiscreditedJudgmentDebtorWarehouseCommand.createByDataCompanyDiscreditedJudgmentDebtorExWarehouseVO(dataCompanyDiscreditedJudgmentDebtorExWarehouseVO);
                fillIds(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand, dataCompanyDiscreditedJudgmentDebtorExWarehouseVO);
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor.warehouse(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand);
            }
        }

    }

    private void fillIds(DataCompanyDiscreditedJudgmentDebtorWarehouseCommand dataCompanyDiscreditedJudgmentDebtorWarehouseCommand, DataCompanyDiscreditedJudgmentDebtorExWarehouseVO dataCompanyDiscreditedJudgmentDebtorExWarehouseVO) {

        // 被执行人性质
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonName(),
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonCompanyId(),
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDishonestExecutedPersonCompanyPersonId(),
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getIsDishonestExecutedPersonNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.setDishonestExecutedPersonCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.setDishonestExecutedPersonCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.setIsDishonestExecutedPersonNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
        // 法人性质
        legalNaturePerson = checkNaturePerson(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getLegalPersonName(),
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getLegalPersonCompanyId(),
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getLegalPersonCompanyPersonId(),
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getIsLegalPersonNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.setLegalPersonCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.setLegalPersonCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.setIsLegalPersonNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
        // 执行法院公司id
        if(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getExecuteCourtCompanyId() == null){
            if (StrUtil.isNotEmpty(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getExecuteCourtName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanyDiscreditedJudgmentDebtorExWarehouseVO.getExecuteCourtName());
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.setExecuteCourtCompanyId(companyId);
            }
        }

        // 做出执行的依据单位公司id
        if(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDecisionBasisDeptCompanyId() == null){
            if (StrUtil.isNotEmpty(dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.getDecisionBasisDeptName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanyDiscreditedJudgmentDebtorExWarehouseVO.getDecisionBasisDeptName());
                dataCompanyDiscreditedJudgmentDebtorWarehouseCommand.setDecisionBasisDeptCompanyId(companyId);
            }
        }


    }

    @Autowired
    public void setDataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor(DataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor) {
        this.dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor = dataCompanyDiscreditedJudgmentDebtorWarehouseCommandExecutor;
    }
}
