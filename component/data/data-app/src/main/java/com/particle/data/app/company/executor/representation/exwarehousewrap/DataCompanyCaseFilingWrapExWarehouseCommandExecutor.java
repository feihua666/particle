package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCaseFilingExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCaseFilingPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCaseFilingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCaseFilingPartyExWarehouseVO;
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
 * 企业立案信息出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:24
 */
@Component
@Validated
public class DataCompanyCaseFilingWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor {

    private DataCompanyCaseFilingExWarehouseCommandExecutor dataCompanyCaseFilingExWarehouseCommandExecutor;
    private DataCompanyCaseFilingPartyExWarehouseCommandExecutor dataCompanyCaseFilingPartyExWarehouseCommandExecutor;

    /**
     * 立案信息出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyCaseFilingExWarehouseQueryCommand
     * @return
     */
    public PageResponse<DataCompanyCaseFilingExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                        DataCompanyCaseFilingExWarehouseQueryCommand dataCompanyCaseFilingExWarehouseQueryCommand) {
        if (dataCompanyCaseFilingExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyCaseFilingExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyCaseFilingExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyCaseFilingExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyCaseFilingExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyCaseFilingExWarehouseVO> response = dataCompanyCaseFilingExWarehouseCommandExecutor.exWarehouse(dataCompanyCaseFilingExWarehouseQueryCommand);
        if (CollectionUtil.isNotEmpty(response.getData())) {
            List<Long> ids = response.getData().stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Long, List<DataCompanyCaseFilingPartyExWarehouseVO>> parties = parties(ids);
            for (DataCompanyCaseFilingExWarehouseVO datum : response.getData()) {
                datum.setParties(parties.get(datum.getId()));
            }
        }
        return response;
    }

    /**
     * 获取当事人信息
     * @param companyCaseFilingIds 立案 ID 列表
     * @return 按立案 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyCaseFilingPartyExWarehouseVO>> parties(List<Long> companyCaseFilingIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyCaseFilingPartyExWarehouseVO> multiResponse = dataCompanyCaseFilingPartyExWarehouseCommandExecutor.exWarehouseByCompanyCaseFilingIds(companyCaseFilingIds);
        List<DataCompanyCaseFilingPartyExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按立案 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyCaseFilingPartyExWarehouseVO::getCompanyCaseFilingId));
    }
    @Autowired
    public void setDataCompanyCaseFilingExWarehouseCommandExecutor(DataCompanyCaseFilingExWarehouseCommandExecutor dataCompanyCaseFilingExWarehouseCommandExecutor) {
        this.dataCompanyCaseFilingExWarehouseCommandExecutor = dataCompanyCaseFilingExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCaseFilingPartyExWarehouseCommandExecutor(DataCompanyCaseFilingPartyExWarehouseCommandExecutor dataCompanyCaseFilingPartyExWarehouseCommandExecutor) {
        this.dataCompanyCaseFilingPartyExWarehouseCommandExecutor = dataCompanyCaseFilingPartyExWarehouseCommandExecutor;
    }
}
