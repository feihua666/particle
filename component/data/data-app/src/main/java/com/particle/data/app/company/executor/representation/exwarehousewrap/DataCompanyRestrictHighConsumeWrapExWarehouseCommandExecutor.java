package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumeExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyRestrictHighConsumePartyExWarehouseVO;
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
 * 企业限制高消费出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:24
 */
@Component
@Validated
public class DataCompanyRestrictHighConsumeWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor {

    private DataCompanyRestrictHighConsumeExWarehouseCommandExecutor dataCompanyRestrictHighConsumeExWarehouseCommandExecutor;
    private DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;

    /**
     * 企业限制高消费出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyRestrictHighConsumeExWarehouseQueryCommand
     * @return
     */
    public PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                        DataCompanyRestrictHighConsumeExWarehouseQueryCommand dataCompanyRestrictHighConsumeExWarehouseQueryCommand) {
        if (dataCompanyRestrictHighConsumeExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyRestrictHighConsumeExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyRestrictHighConsumeExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyRestrictHighConsumeExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyRestrictHighConsumeExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyRestrictHighConsumeExWarehouseVO> response = dataCompanyRestrictHighConsumeExWarehouseCommandExecutor.exWarehouse(dataCompanyRestrictHighConsumeExWarehouseQueryCommand);
        if (CollectionUtil.isNotEmpty(response.getData())) {
            List<Long> ids = response.getData().stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Long, List<DataCompanyRestrictHighConsumePartyExWarehouseVO>> parties = parties(ids);
            for (DataCompanyRestrictHighConsumeExWarehouseVO datum : response.getData()) {
                datum.setParties(parties.get(datum.getId()));
            }
        }
        return response;
    }
    /**
     * 获取当事人信息
     * @param companyRestrictHighConsumeIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyRestrictHighConsumePartyExWarehouseVO>> parties(List<Long> companyRestrictHighConsumeIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyRestrictHighConsumePartyExWarehouseVO> multiResponse = dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor.exWarehouseByCompanyRestrictHighConsumeIds(companyRestrictHighConsumeIds);
        List<DataCompanyRestrictHighConsumePartyExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyRestrictHighConsumePartyExWarehouseVO::getCompanyRestrictHighConsumeId));
    }

    @Autowired
    public void setDataCompanyRestrictHighConsumeExWarehouseCommandExecutor(DataCompanyRestrictHighConsumeExWarehouseCommandExecutor dataCompanyRestrictHighConsumeExWarehouseCommandExecutor) {
        this.dataCompanyRestrictHighConsumeExWarehouseCommandExecutor = dataCompanyRestrictHighConsumeExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor(DataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor) {
        this.dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor = dataCompanyRestrictHighConsumePartyExWarehouseCommandExecutor;
    }
}
