package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprIntegratedCircuitWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprIntegratedCircuitWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprIntegratedCircuitExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 企业集成电路入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:35:04
 */
@Component
@Validated
public class DataCompanyIprIntegratedCircuitWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprIntegratedCircuitWarehouseCommandExecutor dataCompanyIprIntegratedCircuitWarehouseCommandExecutor;

    /**
     * 企业集成电路入库
     * @param dataCompanyIprIntegratedCircuitExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyIprIntegratedCircuitExWarehouseVO> dataCompanyIprIntegratedCircuitExWarehouseVOPageResponse) {
        List<DataCompanyIprIntegratedCircuitExWarehouseVO> dataCompanyIprIntegratedCircuitExWarehouseVOs = dataCompanyIprIntegratedCircuitExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyIprIntegratedCircuitExWarehouseVOs)) {
            for (DataCompanyIprIntegratedCircuitExWarehouseVO dataCompanyIprIntegratedCircuitExWarehouseVO : dataCompanyIprIntegratedCircuitExWarehouseVOs) {
                DataCompanyIprIntegratedCircuitWarehouseCommand byDataCompanyIprIntegratedCircuitExWarehouseVO = DataCompanyIprIntegratedCircuitWarehouseCommand.createByDataCompanyIprIntegratedCircuitExWarehouseVO(dataCompanyIprIntegratedCircuitExWarehouseVO);
                fillId(byDataCompanyIprIntegratedCircuitExWarehouseVO);
                dataCompanyIprIntegratedCircuitWarehouseCommandExecutor.warehouse(byDataCompanyIprIntegratedCircuitExWarehouseVO);
            }
        }

    }

    private void fillId(DataCompanyIprIntegratedCircuitWarehouseCommand dataCompanyIprIntegratedCircuitWarehouseCommand) {
        // 布图设计权利人名称
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyIprIntegratedCircuitWarehouseCommand.getRightHolder(),
                dataCompanyIprIntegratedCircuitWarehouseCommand.getRightHolderCompanyId(),
                dataCompanyIprIntegratedCircuitWarehouseCommand.getRightHolderCompanyPersonId(),
                dataCompanyIprIntegratedCircuitWarehouseCommand.getIsRightHolderNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyIprIntegratedCircuitWarehouseCommand.setRightHolderCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyIprIntegratedCircuitWarehouseCommand.setRightHolderCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyIprIntegratedCircuitWarehouseCommand.setIsRightHolderNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }

    @Autowired
    public void setDataCompanyIprIntegratedCircuitWarehouseCommandExecutor(DataCompanyIprIntegratedCircuitWarehouseCommandExecutor dataCompanyIprIntegratedCircuitWarehouseCommandExecutor) {
        this.dataCompanyIprIntegratedCircuitWarehouseCommandExecutor = dataCompanyIprIntegratedCircuitWarehouseCommandExecutor;
    }
}
