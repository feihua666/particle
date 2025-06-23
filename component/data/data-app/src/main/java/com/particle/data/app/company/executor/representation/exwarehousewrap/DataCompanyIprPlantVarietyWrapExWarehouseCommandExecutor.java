package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPlantVarietyExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPlantVarietyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyChangeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPlantVarietyExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业植物新品种出库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:34:00
 */
@Component
@Validated
public class DataCompanyIprPlantVarietyWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprPlantVarietyExWarehouseCommandExecutor dataCompanyIprPlantVarietyExWarehouseCommandExecutor;
    private DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;

    /**
     * 企业植物新品种出库
     * @param dataCompanyIprPlantVarietyExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyIprPlantVarietyExWarehouseQueryCommand dataCompanyIprPlantVarietyExWarehouseQueryCommand) {
        if (dataCompanyIprPlantVarietyExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyIprPlantVarietyExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyIprPlantVarietyExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyIprPlantVarietyExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyIprPlantVarietyExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyIprPlantVarietyExWarehouseVO> dataCompanyIprPlantVarietyExWarehouseVOPageResponse = dataCompanyIprPlantVarietyExWarehouseCommandExecutor.exWarehouse(dataCompanyIprPlantVarietyExWarehouseQueryCommand);
        List<DataCompanyIprPlantVarietyExWarehouseVO> dataCompanyIprPlantVarietyExWarehouseVOs = dataCompanyIprPlantVarietyExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyIprPlantVarietyExWarehouseVOs)) {
            List<Long> companyIprPlantVarietyIds = dataCompanyIprPlantVarietyExWarehouseVOs.stream().map(DataCompanyIprPlantVarietyExWarehouseVO::getId).collect(Collectors.toList());
            Map<Long, List<DataCompanyIprPlantVarietyChangeExWarehouseVO>> approveAnnouncements = approveAnnouncements(companyIprPlantVarietyIds);
            for (DataCompanyIprPlantVarietyExWarehouseVO dataCompanyIprPlantVarietyExWarehouseVO : dataCompanyIprPlantVarietyExWarehouseVOs) {
                List<DataCompanyIprPlantVarietyChangeExWarehouseVO> dataCompanyIprPlantVarietyChangeExWarehouseVOS = approveAnnouncements.get(dataCompanyIprPlantVarietyExWarehouseVO.getId());
                dataCompanyIprPlantVarietyExWarehouseVO.setChanges(dataCompanyIprPlantVarietyChangeExWarehouseVOS);
            }
        }

        return dataCompanyIprPlantVarietyExWarehouseVOPageResponse;
    }
    /**
     * 获取核准公告信息
     * @param companyIprPlantVarietyIds 地理标识 ID 列表
     * @return 按地理标识 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyIprPlantVarietyChangeExWarehouseVO>> approveAnnouncements(List<Long> companyIprPlantVarietyIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyIprPlantVarietyChangeExWarehouseVO> multiResponse = dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor.exWarehouseByCompanyIprPlantVarietyIds(companyIprPlantVarietyIds);
        List<DataCompanyIprPlantVarietyChangeExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按地理标识 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyIprPlantVarietyChangeExWarehouseVO::getCompanyIprPlantVarietyId));
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyExWarehouseCommandExecutor(DataCompanyIprPlantVarietyExWarehouseCommandExecutor dataCompanyIprPlantVarietyExWarehouseCommandExecutor) {
        this.dataCompanyIprPlantVarietyExWarehouseCommandExecutor = dataCompanyIprPlantVarietyExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor(DataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor) {
        this.dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor = dataCompanyIprPlantVarietyChangeExWarehouseCommandExecutor;
    }
}
