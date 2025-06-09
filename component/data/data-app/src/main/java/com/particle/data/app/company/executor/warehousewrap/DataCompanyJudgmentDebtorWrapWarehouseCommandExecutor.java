package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyJudgmentDebtorWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyJudgmentDebtorWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDebtorExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业被执行人入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyJudgmentDebtorWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyJudgmentDebtorWarehouseCommandExecutor dataCompanyJudgmentDebtorWarehouseCommandExecutor;

    /**
     * 企业被执行人入库
     * @param dataCompanyJudgmentDebtorExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyJudgmentDebtorExWarehouseVO> dataCompanyJudgmentDebtorExWarehouseVOPageResponse) {
        List<DataCompanyJudgmentDebtorExWarehouseVO> dataCompanyJudgmentDebtorExWarehouseVOs = dataCompanyJudgmentDebtorExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyJudgmentDebtorExWarehouseVOs)) {
            for (DataCompanyJudgmentDebtorExWarehouseVO dataCompanyJudgmentDebtorExWarehouseVO : dataCompanyJudgmentDebtorExWarehouseVOs) {
                DataCompanyJudgmentDebtorWarehouseCommand dataCompanyJudgmentDebtorWarehouseCommand = DataCompanyJudgmentDebtorWarehouseCommand.createByDataCompanyJudgmentDebtorExWarehouseVO(dataCompanyJudgmentDebtorExWarehouseVO);
                fillIds(dataCompanyJudgmentDebtorWarehouseCommand, dataCompanyJudgmentDebtorExWarehouseVO);
                dataCompanyJudgmentDebtorWarehouseCommandExecutor.warehouse(dataCompanyJudgmentDebtorWarehouseCommand);
            }
        }

    }

    private void fillIds(DataCompanyJudgmentDebtorWarehouseCommand dataCompanyJudgmentDebtorWarehouseCommand, DataCompanyJudgmentDebtorExWarehouseVO dataCompanyJudgmentDebtorExWarehouseVO) {

        // 被执行人性质
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyJudgmentDebtorWarehouseCommand.getExecutedPersonName(),
                dataCompanyJudgmentDebtorWarehouseCommand.getExecutedPersonCompanyId(),
                dataCompanyJudgmentDebtorWarehouseCommand.getExecutedPersonCompanyPersonId(),
                dataCompanyJudgmentDebtorWarehouseCommand.getIsExecutedPersonNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyJudgmentDebtorWarehouseCommand.setExecutedPersonCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyJudgmentDebtorWarehouseCommand.setExecutedPersonCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyJudgmentDebtorWarehouseCommand.setIsExecutedPersonNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }

        // 执行法院公司id
        if(dataCompanyJudgmentDebtorWarehouseCommand.getExecuteCourtCompanyId() == null){
            if (StrUtil.isNotEmpty(dataCompanyJudgmentDebtorWarehouseCommand.getExecuteCourtName())) {
                Long companyId = warehouseCompanyGetCompanyId(dataCompanyJudgmentDebtorExWarehouseVO.getExecuteCourtName());
                dataCompanyJudgmentDebtorWarehouseCommand.setExecuteCourtCompanyId(companyId);
            }
        }

    }
    @Autowired
    public void setDataCompanyJudgmentDebtorWarehouseCommandExecutor(DataCompanyJudgmentDebtorWarehouseCommandExecutor dataCompanyJudgmentDebtorWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDebtorWarehouseCommandExecutor = dataCompanyJudgmentDebtorWarehouseCommandExecutor;
    }
}
