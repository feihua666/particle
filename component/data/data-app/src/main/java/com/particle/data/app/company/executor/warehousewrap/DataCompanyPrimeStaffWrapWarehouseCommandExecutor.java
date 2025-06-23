package com.particle.data.app.company.executor.warehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.warehouse.DataCompanyPrimeStaffPositionWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehouse.DataCompanyPrimeStaffWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffPositionWarehouseCommand;
import com.particle.data.client.company.dto.command.warehouse.DataCompanyPrimeStaffWarehouseCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffPositionService;
import com.particle.data.infrastructure.company.service.IDataCompanyPrimeStaffService;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业主要人员入库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyPrimeStaffWrapWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyPrimeStaffWarehouseCommandExecutor dataCompanyPrimeStaffWarehouseCommandExecutor;
    private DataCompanyPrimeStaffPositionWarehouseCommandExecutor dataCompanyPrimeStaffPositionWarehouseCommandExecutor;
    private IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService;
    private IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService;
    /**
     * 企业主要人员入库
     * @param dataCompanyPrimeStaffExWarehouseVOPageResponse
     */
    public void warehouse(PageResponse<DataCompanyPrimeStaffExWarehouseVO> dataCompanyPrimeStaffExWarehouseVOPageResponse) {
        List<DataCompanyPrimeStaffExWarehouseVO> dataCompanyPrimeStaffExWarehouseVOs = dataCompanyPrimeStaffExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyPrimeStaffExWarehouseVOs)) {
            // 是否为全部数据
            boolean isAll = dataCompanyPrimeStaffExWarehouseVOPageResponse.getTotalCount() == dataCompanyPrimeStaffExWarehouseVOPageResponse.getPageSize();
            List<Long> dbPrimeStaffIds = null;
            if (isAll) {
                List<DataCompanyPrimeStaffDO> dataCompanyPrimeStaffDOS = iDataCompanyPrimeStaffService.listByCompanyId(dataCompanyPrimeStaffExWarehouseVOPageResponse.getData().get(0).getCompanyId());
                dbPrimeStaffIds = dataCompanyPrimeStaffDOS.stream().map(DataCompanyPrimeStaffDO::getId).collect(Collectors.toList());
            }

            for (DataCompanyPrimeStaffExWarehouseVO dataCompanyPrimeStaffExWarehouseVO : dataCompanyPrimeStaffExWarehouseVOs) {
                DataCompanyPrimeStaffWarehouseCommand dataCompanyPrimeStaffWarehouseCommand = DataCompanyPrimeStaffWarehouseCommand.createByDataCompanyPrimeStaffExWarehouseVO(dataCompanyPrimeStaffExWarehouseVO);
                fillIds(dataCompanyPrimeStaffWarehouseCommand, dataCompanyPrimeStaffExWarehouseVO);
                SingleResponse<DataCompanyPrimeStaffExWarehouseVO> warehouse = dataCompanyPrimeStaffWarehouseCommandExecutor.warehouse(dataCompanyPrimeStaffWarehouseCommand);
                Long companyPrimeStaffId = Optional.ofNullable(warehouse)
                        .map(rp -> rp.getData())
                        .map(d -> d.getId())
                        .orElse(null);
                if (CollectionUtil.isNotEmpty(dbPrimeStaffIds)) {
                    if (dbPrimeStaffIds.contains(companyPrimeStaffId)) {
                        dbPrimeStaffIds.remove(companyPrimeStaffId);
                    }
                }
                warehousePositions(dataCompanyPrimeStaffExWarehouseVO.getPositions(), companyPrimeStaffId);
            }
            if (isAll) {
                if (CollectionUtil.isNotEmpty(dbPrimeStaffIds)) {
                    // 将入库之外的都删除
                    iDataCompanyPrimeStaffService.removeByIds(dbPrimeStaffIds);
                }
            }
        }
    }
    /**
     * 职位入库
     * @param positions
     * @param companyPrimeStaffId
     */
    private void warehousePositions(List<DataCompanyPrimeStaffPositionExWarehouseVO> positions, Long companyPrimeStaffId) {
        List<DataCompanyPrimeStaffPositionDO> dataCompanyPrimeStaffPositionDOS = iDataCompanyPrimeStaffPositionService.listByCompanyPrimeStaffId(companyPrimeStaffId);
        List<Long> dbPositionIds = dataCompanyPrimeStaffPositionDOS.stream().map(DataCompanyPrimeStaffPositionDO::getId).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(positions)) {
            // 删除所有职位
            iDataCompanyPrimeStaffPositionService.deleteByColumn(companyPrimeStaffId, DataCompanyPrimeStaffPositionDO::getCompanyPrimeStaffId);
            return;
        }
        for (DataCompanyPrimeStaffPositionExWarehouseVO position : positions) {
            DataCompanyPrimeStaffPositionWarehouseCommand dataCompanyPrimeStaffPositionWarehouseCommand = DataCompanyPrimeStaffPositionWarehouseCommand.createByDataCompanyPrimeStaffPositionExWarehouseVO(position);
            if (dataCompanyPrimeStaffPositionWarehouseCommand.getCompanyPrimeStaffId() == null) {
                dataCompanyPrimeStaffPositionWarehouseCommand.setCompanyPrimeStaffId(companyPrimeStaffId);
            }
            SingleResponse<DataCompanyPrimeStaffPositionExWarehouseVO> warehouse = dataCompanyPrimeStaffPositionWarehouseCommandExecutor.warehouse(dataCompanyPrimeStaffPositionWarehouseCommand);
            if (warehouse.isSuccess()) {
                if (dbPositionIds.contains(warehouse.getData().getId())) {
                    dbPositionIds.remove(warehouse.getData().getId());
                }
            }
        }
        // 将入库之外的都删除
        if (CollectionUtil.isNotEmpty(dbPositionIds)) {
            iDataCompanyPrimeStaffPositionService.removeByIds(dbPositionIds);
        }

    }
    private void fillIds(DataCompanyPrimeStaffWarehouseCommand dataCompanyPrimeStaffWarehouseCommand, DataCompanyPrimeStaffExWarehouseVO dataCompanyPrimeStaffExWarehouseVO) {

        // 姓名
        NaturePerson legalNaturePerson = checkNaturePerson(dataCompanyPrimeStaffWarehouseCommand.getStaffName(),
                null,
                dataCompanyPrimeStaffWarehouseCommand.getStaffCompanyPersonId(),
                true);
        if (legalNaturePerson != null) {
            dataCompanyPrimeStaffWarehouseCommand.setStaffCompanyPersonId(legalNaturePerson.getPersonId());
        }

    }

    @Autowired
    public void setDataCompanyPrimeStaffWarehouseCommandExecutor(DataCompanyPrimeStaffWarehouseCommandExecutor dataCompanyPrimeStaffWarehouseCommandExecutor) {
        this.dataCompanyPrimeStaffWarehouseCommandExecutor = dataCompanyPrimeStaffWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffPositionWarehouseCommandExecutor(DataCompanyPrimeStaffPositionWarehouseCommandExecutor dataCompanyPrimeStaffPositionWarehouseCommandExecutor) {
        this.dataCompanyPrimeStaffPositionWarehouseCommandExecutor = dataCompanyPrimeStaffPositionWarehouseCommandExecutor;
    }
    @Autowired
    public void setiDataCompanyPrimeStaffService(IDataCompanyPrimeStaffService iDataCompanyPrimeStaffService) {
        this.iDataCompanyPrimeStaffService = iDataCompanyPrimeStaffService;
    }

    @Autowired
    public void setiDataCompanyPrimeStaffPositionService(IDataCompanyPrimeStaffPositionService iDataCompanyPrimeStaffPositionService) {
        this.iDataCompanyPrimeStaffPositionService = iDataCompanyPrimeStaffPositionService;
    }
}
