package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcFinancingExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcFinancingExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcFinancingExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcInvestInstitutionExWarehouseVO;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
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
 * 企业融资出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyVcFinancingWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyVcFinancingExWarehouseCommandExecutor dataCompanyVcFinancingExWarehouseCommandExecutor;
    private DataCompanyVcInvestInstitutionExWarehouseCommandExecutor dataCompanyVcInvestInstitutionExWarehouseCommandExecutor;

    /**
     * 企业融资出库
     * @param dataCompanyVcFinancingExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyVcFinancingExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyVcFinancingExWarehouseQueryCommand dataCompanyVcFinancingExWarehouseQueryCommand) {
        if (dataCompanyVcFinancingExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyVcFinancingExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyVcFinancingExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyVcFinancingExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyVcFinancingExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyVcFinancingExWarehouseVO> response = dataCompanyVcFinancingExWarehouseCommandExecutor.exWarehouse(dataCompanyVcFinancingExWarehouseQueryCommand);
        if (CollectionUtil.isNotEmpty(response.getData())) {
            List<Long> ids = response.getData().stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Long, List<DataCompanyVcInvestInstitutionExWarehouseVO>> vcInvestInstitutions = vcInvestInstitutions(ids);
            for (DataCompanyVcFinancingExWarehouseVO datum : response.getData()) {
                datum.setVcInvestInstitutions(vcInvestInstitutions.get(datum.getId()));
            }
        }
        return response;

    }
    /**
     * 获取融资机构信息
     * @param companyVcFinancingIds 融资 ID 列表
     * @return 按融资 ID 分组的融资机构信息
     */
    private Map<Long, List<DataCompanyVcInvestInstitutionExWarehouseVO>> vcInvestInstitutions(List<Long> companyVcFinancingIds) {
        // 调用命令执行器查询融资机构信息
        SingleResponse<Map<Long, List<DataCompanyVcInvestInstitutionExWarehouseVO>>> singleResponse = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor.exWarehouseGroupByCompanyVcFinancingIdByCompanyVcFinancingIds(companyVcFinancingIds);
        Map<Long, List<DataCompanyVcInvestInstitutionExWarehouseVO>> data = singleResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按融资 ID 分组返回结果
        return data;
    }
    @Autowired
    public void setDataCompanyVcFinancingExWarehouseCommandExecutor(DataCompanyVcFinancingExWarehouseCommandExecutor dataCompanyVcFinancingExWarehouseCommandExecutor) {
        this.dataCompanyVcFinancingExWarehouseCommandExecutor = dataCompanyVcFinancingExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyVcInvestInstitutionExWarehouseCommandExecutor(DataCompanyVcInvestInstitutionExWarehouseCommandExecutor dataCompanyVcInvestInstitutionExWarehouseCommandExecutor) {
        this.dataCompanyVcInvestInstitutionExWarehouseCommandExecutor = dataCompanyVcInvestInstitutionExWarehouseCommandExecutor;
    }
}
