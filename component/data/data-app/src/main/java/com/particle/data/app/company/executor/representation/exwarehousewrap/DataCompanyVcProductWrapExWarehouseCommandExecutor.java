package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyVcProductExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyVcProductExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyVcProductExWarehouseVO;
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
 * 企业融资产品出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyVcProductWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyVcProductExWarehouseCommandExecutor dataCompanyVcProductExWarehouseCommandExecutor;

    /**
     * 企业融资产品出库
     * @param dataCompanyVcProductExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyVcProductExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyVcProductExWarehouseQueryCommand dataCompanyVcProductExWarehouseQueryCommand) {
        if (dataCompanyVcProductExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyVcProductExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyVcProductExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyVcProductExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyVcProductExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyVcProductExWarehouseVO> response = dataCompanyVcProductExWarehouseCommandExecutor.exWarehouse(dataCompanyVcProductExWarehouseQueryCommand);
        if (CollectionUtil.isNotEmpty(response.getData())) {
            List<Long> ids = response.getData().stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Long, List<DataCompanyVcProductExWarehouseVO>> competitiveProducts = competitiveProducts(ids);
            for (DataCompanyVcProductExWarehouseVO datum : response.getData()) {
                datum.setCompetitiveProducts(competitiveProducts.get(datum.getId()));
            }
        }
        return response;

    }
    /**
     * 获取融资产品竞品信息
     * @param companyVcProductIds 融资产品 ID 列表
     * @return 按融资产品 ID 分组的融资产品竞品信息
     */
    private Map<Long, List<DataCompanyVcProductExWarehouseVO>> competitiveProducts(List<Long> companyVcProductIds) {
        // 调用命令执行器查询融资产品竞品信息
        SingleResponse<Map<Long, List<DataCompanyVcProductExWarehouseVO>>> singleResponse = dataCompanyVcProductExWarehouseCommandExecutor.exWarehouseCompetitiveGroupByCompanyVcProductIdByCompanyVcProductIds(companyVcProductIds);
        Map<Long, List<DataCompanyVcProductExWarehouseVO>> data = singleResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按融资产品 ID 分组返回结果
        return data;
    }
    @Autowired
    public void setDataCompanyVcProductExWarehouseCommandExecutor(DataCompanyVcProductExWarehouseCommandExecutor dataCompanyVcProductExWarehouseCommandExecutor) {
        this.dataCompanyVcProductExWarehouseCommandExecutor = dataCompanyVcProductExWarehouseCommandExecutor;
    }
}
