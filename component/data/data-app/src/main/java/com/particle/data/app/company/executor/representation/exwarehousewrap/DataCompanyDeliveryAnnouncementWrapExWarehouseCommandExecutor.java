package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 企业送达公告出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:57
 */
@Component
@Validated
public class DataCompanyDeliveryAnnouncementWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;
    private DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;
    private DataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor;

    /**
     * 企业开庭公告出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyDeliveryAnnouncementExWarehouseQueryCommand
     * @return
     */
    public PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                   DataCompanyDeliveryAnnouncementExWarehouseQueryCommand dataCompanyDeliveryAnnouncementExWarehouseQueryCommand) {
        if (dataCompanyDeliveryAnnouncementExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyDeliveryAnnouncementExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyDeliveryAnnouncementExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyDeliveryAnnouncementExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyDeliveryAnnouncementExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> response = dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor.exWarehouse(dataCompanyDeliveryAnnouncementExWarehouseQueryCommand);
        if (CollectionUtil.isNotEmpty(response.getData())) {
            List<Long> ids = response.getData().stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Long, List<DataCompanyDeliveryAnnouncementPartyExWarehouseVO>> parties = parties(ids);
            Map<Long, DataCompanyDeliveryAnnouncementContentExWarehouseVO> content = content(ids);
            for (DataCompanyDeliveryAnnouncementExWarehouseVO datum : response.getData()) {
                datum.setParties(parties.get(datum.getId()));
                datum.setContent(content.get(datum.getId()));
            }
        }
        return response;
    }
    /**
     * 获取当事人信息
     * @param companyDeliveryAnnouncementIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyDeliveryAnnouncementPartyExWarehouseVO>> parties(List<Long> companyDeliveryAnnouncementIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> multiResponse = dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor.exWarehouseByCompanyDeliveryAnnouncementIds(companyDeliveryAnnouncementIds);
        List<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyDeliveryAnnouncementPartyExWarehouseVO::getCompanyDeliveryAnnouncementId));
    }
    /**
     * 获取内容信息
     * @param companyDeliveryAnnouncementIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的内容信息
     */
    private Map<Long, DataCompanyDeliveryAnnouncementContentExWarehouseVO> content(List<Long> companyDeliveryAnnouncementIds) {
        // 调用命令执行器查询内容信息
        MultiResponse<DataCompanyDeliveryAnnouncementContentExWarehouseVO> multiResponse = dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor.exWarehouseByCompanyDeliveryAnnouncementIds(companyDeliveryAnnouncementIds);
        List<DataCompanyDeliveryAnnouncementContentExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.toMap(DataCompanyDeliveryAnnouncementContentExWarehouseVO::getCompanyDeliveryAnnouncementId, Function.identity()));
    }

    @Autowired
    public void setDataCompanyDeliveryAnnouncementExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor;
    }
}
