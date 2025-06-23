package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPrimeStaffExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPrimeStaffExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPrimeStaffPositionExWarehouseVO;
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
 * 企业主要人员出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyPrimeStaffWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyPrimeStaffExWarehouseCommandExecutor dataCompanyPrimeStaffExWarehouseCommandExecutor;

    private DataCompanyPrimeStaffPositionExWarehouseCommandExecutor dataCompanyPrimeStaffPositionExWarehouseCommandExecutor;
    /**
     * 企业主要人员出库
     * @param dataCompanyPrimeStaffExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyPrimeStaffExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyPrimeStaffExWarehouseQueryCommand dataCompanyPrimeStaffExWarehouseQueryCommand) {
        if (dataCompanyPrimeStaffExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyPrimeStaffExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyPrimeStaffExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyPrimeStaffExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyPrimeStaffExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyPrimeStaffExWarehouseVO> dataCompanyPrimeStaffExWarehouseVOPageResponse = dataCompanyPrimeStaffExWarehouseCommandExecutor.exWarehouse(dataCompanyPrimeStaffExWarehouseQueryCommand);
        List<DataCompanyPrimeStaffExWarehouseVO> dataCompanyPrimeStaffExWarehouseVOs = dataCompanyPrimeStaffExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyPrimeStaffExWarehouseVOs)) {
            List<Long> companyPrimeStaffIds = dataCompanyPrimeStaffExWarehouseVOs.stream().map(DataCompanyPrimeStaffExWarehouseVO::getId).collect(Collectors.toList());
            Map<Long, List<DataCompanyPrimeStaffPositionExWarehouseVO>> positions = positions(companyPrimeStaffIds);
            for (DataCompanyPrimeStaffExWarehouseVO dataCompanyPrimeStaffExWarehouseVO : dataCompanyPrimeStaffExWarehouseVOs) {
                List<DataCompanyPrimeStaffPositionExWarehouseVO> dataCompanyPrimeStaffPositionExWarehouseVOS = positions.get(dataCompanyPrimeStaffExWarehouseVO.getId());
                dataCompanyPrimeStaffExWarehouseVO.setPositions(dataCompanyPrimeStaffPositionExWarehouseVOS);
            }
        }

        return dataCompanyPrimeStaffExWarehouseVOPageResponse;
    }
    /**
     * 获取职位信息
     * @param companyPrimeStaffIds 主要人员 ID 列表
     * @return 按主要人员 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyPrimeStaffPositionExWarehouseVO>> positions(List<Long> companyPrimeStaffIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyPrimeStaffPositionExWarehouseVO> multiResponse = dataCompanyPrimeStaffPositionExWarehouseCommandExecutor.exWarehouseByCompanyPrimeStaffIds(companyPrimeStaffIds);
        List<DataCompanyPrimeStaffPositionExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按主要人员 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyPrimeStaffPositionExWarehouseVO::getCompanyPrimeStaffId));
    }
    @Autowired
    public void setDataCompanyPrimeStaffExWarehouseCommandExecutor(DataCompanyPrimeStaffExWarehouseCommandExecutor dataCompanyPrimeStaffExWarehouseCommandExecutor) {
        this.dataCompanyPrimeStaffExWarehouseCommandExecutor = dataCompanyPrimeStaffExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPrimeStaffPositionExWarehouseCommandExecutor(DataCompanyPrimeStaffPositionExWarehouseCommandExecutor dataCompanyPrimeStaffPositionExWarehouseCommandExecutor) {
        this.dataCompanyPrimeStaffPositionExWarehouseCommandExecutor = dataCompanyPrimeStaffPositionExWarehouseCommandExecutor;
    }
}
