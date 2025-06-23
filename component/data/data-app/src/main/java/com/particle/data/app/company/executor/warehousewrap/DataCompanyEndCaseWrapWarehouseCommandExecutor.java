package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.dict.CurrencyType;
import com.particle.data.app.company.executor.warehouse.DataCompanyEndCaseWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyEndCaseWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyEndCaseExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业终本案件入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyEndCaseWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyEndCaseWarehouseCommandExecutor dataCompanyEndCaseWarehouseCommandExecutor;

    /**
     * 企业终本案件入库
     * @param dataCompanyEndCaseExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyEndCaseExWarehouseVO> dataCompanyEndCaseExWarehouseVOPageResponse) {
        List<DataCompanyEndCaseExWarehouseVO> dataCompanyEndCaseExWarehouseVOs = dataCompanyEndCaseExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyEndCaseExWarehouseVOs)) {
            for (DataCompanyEndCaseExWarehouseVO dataCompanyEndCaseExWarehouseVO : dataCompanyEndCaseExWarehouseVOs) {
                DataCompanyEndCaseWarehouseCommand dataCompanyEndCaseWarehouseCommand = DataCompanyEndCaseWarehouseCommand.createByDataCompanyEndCaseExWarehouseVO(dataCompanyEndCaseExWarehouseVO);
                fillIds(dataCompanyEndCaseWarehouseCommand, dataCompanyEndCaseExWarehouseVO);
                dataCompanyEndCaseWarehouseCommandExecutor.warehouse(dataCompanyEndCaseWarehouseCommand);
            }
        }
    }

    private void fillIds(DataCompanyEndCaseWarehouseCommand dataCompanyEndCaseWarehouseCommand, DataCompanyEndCaseExWarehouseVO dataCompanyEndCaseExWarehouseVO) {

        // 被执行人名称
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyEndCaseWarehouseCommand.getExecutedPersonName(),
                dataCompanyEndCaseWarehouseCommand.getExecutedPersonCompanyId(),
                dataCompanyEndCaseWarehouseCommand.getExecutedPersonCompanyPersonId(),
                dataCompanyEndCaseWarehouseCommand.getIsExecutedPersonNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyEndCaseWarehouseCommand.setExecutedPersonCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyEndCaseWarehouseCommand.setExecutedPersonCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyEndCaseWarehouseCommand.setIsExecutedPersonNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
        // 法院名称
        if (StrUtil.isNotEmpty(dataCompanyEndCaseWarehouseCommand.getCourtName())) {
            Long courtNameCompanyId = warehouseCompanyGetCompanyId(dataCompanyEndCaseWarehouseCommand.getCourtName());
            dataCompanyEndCaseWarehouseCommand.setCourtCompanyId(courtNameCompanyId);
        }

    }

    @Autowired
    public void setDataCompanyEndCaseWarehouseCommandExecutor(DataCompanyEndCaseWarehouseCommandExecutor dataCompanyEndCaseWarehouseCommandExecutor) {
        this.dataCompanyEndCaseWarehouseCommandExecutor = dataCompanyEndCaseWarehouseCommandExecutor;
    }
}
