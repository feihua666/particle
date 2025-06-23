package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPlantVarietyChangeWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprPlantVarietyWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyChangeWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprPlantVarietyWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 企业植物新品种入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:35:04
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprPlantVarietyWarehouseCommandExecutor dataCompanyIprPlantVarietyWarehouseCommandExecutor;
    private DataCompanyIprPlantVarietyChangeWarehouseCommandExecutor dataCompanyIprPlantVarietyChangeWarehouseCommandExecutor;

    /**
     * 企业植物新品种入库
     * @param dataCompanyIprPlantVarietyExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> dataCompanyIprPlantVarietyExWarehouseVOPageResponse) {
        List<DataCompanyIprPlantVarietyExWarehouseVO> dataCompanyIprPlantVarietyExWarehouseVOs = dataCompanyIprPlantVarietyExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyIprPlantVarietyExWarehouseVOs)) {
            for (DataCompanyIprPlantVarietyExWarehouseVO dataCompanyIprPlantVarietyExWarehouseVO : dataCompanyIprPlantVarietyExWarehouseVOs) {
                DataCompanyIprPlantVarietyWarehouseCommand byDataCompanyIprPlantVarietyExWarehouseVO = DataCompanyIprPlantVarietyWarehouseCommand.createByDataCompanyIprPlantVarietyExWarehouseVO(dataCompanyIprPlantVarietyExWarehouseVO);
                fillId(byDataCompanyIprPlantVarietyExWarehouseVO);
                SingleResponse<DataCompanyIprPlantVarietyExWarehouseVO> warehouse = dataCompanyIprPlantVarietyWarehouseCommandExecutor.warehouse(byDataCompanyIprPlantVarietyExWarehouseVO);
                Long companyIprPlantVarietyId = Optional.ofNullable(warehouse)
                        .map(rp -> rp.getData())
                        .map(d -> d.getId())
                        .orElse(null);
                warehouseChanges(dataCompanyIprPlantVarietyExWarehouseVO.getChanges(), companyIprPlantVarietyId);
            }
        }

    }
    /**
     * 变更入库
     * @param approveAnnouncements
     * @param companyIprPlantVarietyId
     */
    private void warehouseChanges(List<DataCompanyIprPlantVarietyChangeExWarehouseVO> approveAnnouncements, Long companyIprPlantVarietyId) {
        if (CollectionUtil.isEmpty(approveAnnouncements)) {
            return;
        }
        for (DataCompanyIprPlantVarietyChangeExWarehouseVO approveAnnouncement : approveAnnouncements) {
            DataCompanyIprPlantVarietyChangeWarehouseCommand dataCompanyIprPlantVarietyChangeWarehouseCommand = DataCompanyIprPlantVarietyChangeWarehouseCommand.createByDataCompanyIprPlantVarietyChangeExWarehouseVO(approveAnnouncement);
            if (dataCompanyIprPlantVarietyChangeWarehouseCommand.getCompanyIprPlantVarietyId() == null) {
                dataCompanyIprPlantVarietyChangeWarehouseCommand.setCompanyIprPlantVarietyId(companyIprPlantVarietyId);
            }
            dataCompanyIprPlantVarietyChangeWarehouseCommandExecutor.warehouse(dataCompanyIprPlantVarietyChangeWarehouseCommand);
        }

    }
    private void fillId(DataCompanyIprPlantVarietyWarehouseCommand dataCompanyIprPlantVarietyWarehouseCommand) {
        // 申请人名称
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyIprPlantVarietyWarehouseCommand.getApplicantName(),
                dataCompanyIprPlantVarietyWarehouseCommand.getApplicantNameCompanyId(),
                dataCompanyIprPlantVarietyWarehouseCommand.getApplicantNameCompanyPersonId(),
                dataCompanyIprPlantVarietyWarehouseCommand.getIsApplicantNameNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyIprPlantVarietyWarehouseCommand.setApplicantNameCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyIprPlantVarietyWarehouseCommand.setApplicantNameCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyIprPlantVarietyWarehouseCommand.setIsApplicantNameNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }

    @Autowired
    public void setDataCompanyIprPlantVarietyWarehouseCommandExecutor(DataCompanyIprPlantVarietyWarehouseCommandExecutor dataCompanyIprPlantVarietyWarehouseCommandExecutor) {
        this.dataCompanyIprPlantVarietyWarehouseCommandExecutor = dataCompanyIprPlantVarietyWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyChangeWarehouseCommandExecutor(DataCompanyIprPlantVarietyChangeWarehouseCommandExecutor dataCompanyIprPlantVarietyChangeWarehouseCommandExecutor) {
        this.dataCompanyIprPlantVarietyChangeWarehouseCommandExecutor = dataCompanyIprPlantVarietyChangeWarehouseCommandExecutor;
    }
}
