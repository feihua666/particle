package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyIprGeograWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograApproveAnnouncementWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyIprGeograWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 企业地理标识入库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:35:04
 */
@Component
@Validated
public class DataCompanyIprGeograWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprGeograWarehouseCommandExecutor dataCompanyIprGeograWarehouseCommandExecutor;
    private DataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor dataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor;

    /**
     * 企业地理标识入库
     * @param dataCompanyIprGeograExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyIprGeograExWarehouseVO> dataCompanyIprGeograExWarehouseVOPageResponse) {
        List<DataCompanyIprGeograExWarehouseVO> dataCompanyIprGeograExWarehouseVOs = dataCompanyIprGeograExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyIprGeograExWarehouseVOs)) {
            for (DataCompanyIprGeograExWarehouseVO dataCompanyIprGeograExWarehouseVO : dataCompanyIprGeograExWarehouseVOs) {
                DataCompanyIprGeograWarehouseCommand byDataCompanyIprGeograExWarehouseVO = DataCompanyIprGeograWarehouseCommand.createByDataCompanyIprGeograExWarehouseVO(dataCompanyIprGeograExWarehouseVO);
                fillId(byDataCompanyIprGeograExWarehouseVO);
                SingleResponse<DataCompanyIprGeograExWarehouseVO> warehouse = dataCompanyIprGeograWarehouseCommandExecutor.warehouse(byDataCompanyIprGeograExWarehouseVO);
                Long companyIprGeograId = Optional.ofNullable(warehouse)
                        .map(rp -> rp.getData())
                        .map(d -> d.getId())
                        .orElse(null);
                warehouseApproveAnnouncements(dataCompanyIprGeograExWarehouseVO.getApproveAnnouncements(), companyIprGeograId);
            }
        }

    }

    /**
     * 核准公告入库
     * @param approveAnnouncements
     * @param companyIprGeograId
     */
    private void warehouseApproveAnnouncements(List<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> approveAnnouncements,Long companyIprGeograId) {
        if (CollectionUtil.isEmpty(approveAnnouncements)) {
            return;
        }
        for (DataCompanyIprGeograApproveAnnouncementExWarehouseVO approveAnnouncement : approveAnnouncements) {
            DataCompanyIprGeograApproveAnnouncementWarehouseCommand dataCompanyIprGeograApproveAnnouncementWarehouseCommand = DataCompanyIprGeograApproveAnnouncementWarehouseCommand.createByDataCompanyIprGeograApproveAnnouncementExWarehouseVO(approveAnnouncement);
            if (dataCompanyIprGeograApproveAnnouncementWarehouseCommand.getCompanyIprGeograId() == null) {
                dataCompanyIprGeograApproveAnnouncementWarehouseCommand.setCompanyIprGeograId(companyIprGeograId);
            }
            dataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor.warehouse(dataCompanyIprGeograApproveAnnouncementWarehouseCommand);
        }

    }
    private void fillId(DataCompanyIprGeograWarehouseCommand dataCompanyIprGeograWarehouseCommand) {
        // 申请人名称
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyIprGeograWarehouseCommand.getApplicantName(),
                dataCompanyIprGeograWarehouseCommand.getApplicantNameCompanyId(),
                dataCompanyIprGeograWarehouseCommand.getApplicantNameCompanyPersonId(),
                dataCompanyIprGeograWarehouseCommand.getIsApplicantNameNaturalPerson());
        if (legalNaturePerson != null) {
            dataCompanyIprGeograWarehouseCommand.setApplicantNameCompanyId(legalNaturePerson.getCompanyId());
            dataCompanyIprGeograWarehouseCommand.setApplicantNameCompanyPersonId(legalNaturePerson.getPersonId());
            dataCompanyIprGeograWarehouseCommand.setIsApplicantNameNaturalPerson(legalNaturePerson.getIsNaturePerson());
        }
    }

    @Autowired
    public void setDataCompanyIprGeograWarehouseCommandExecutor(DataCompanyIprGeograWarehouseCommandExecutor dataCompanyIprGeograWarehouseCommandExecutor) {
        this.dataCompanyIprGeograWarehouseCommandExecutor = dataCompanyIprGeograWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor(DataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor dataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor) {
        this.dataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor = dataCompanyIprGeograApproveAnnouncementWarehouseCommandExecutor;
    }
}
