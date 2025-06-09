package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;
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
 * 企业开庭公告出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:36
 */
@Component
@Validated
public class DataCompanyOpenCourtAnnouncementWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor{

    private DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;
    private DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;
    private DataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor;

    /**
     * 企业开庭公告出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand
     * @return
     */
    public PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                               DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand) {
        if (dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> response = dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor.exWarehouse(dataCompanyOpenCourtAnnouncementExWarehouseQueryCommand);
        if (CollectionUtil.isNotEmpty(response.getData())) {
            List<Long> ids = response.getData().stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Long, List<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO>> parties = parties(ids);
            Map<Long, DataCompanyOpenCourtAnnouncementContentExWarehouseVO> content = content(ids);
            for (DataCompanyOpenCourtAnnouncementExWarehouseVO datum : response.getData()) {
                datum.setParties(parties.get(datum.getId()));
                datum.setContent(content.get(datum.getId()));
            }
        }
        return response;
    }
    /**
     * 获取当事人信息
     * @param companyOpenCourtAnnouncementIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO>> parties(List<Long> companyOpenCourtAnnouncementIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> multiResponse = dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseByCompanyOpenCourtAnnouncementIds(companyOpenCourtAnnouncementIds);
        List<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyOpenCourtAnnouncementPartyExWarehouseVO::getCompanyOpenCourtAnnouncementId));
    }
    /**
     * 获取内容信息
     * @param companyOpenCourtAnnouncementIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的内容信息
     */
    private Map<Long, DataCompanyOpenCourtAnnouncementContentExWarehouseVO> content(List<Long> companyOpenCourtAnnouncementIds) {
        // 调用命令执行器查询内容信息
        MultiResponse<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> multiResponse = dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor.exWarehouseByCompanyOpenCourtAnnouncementIds(companyOpenCourtAnnouncementIds);
        List<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.toMap(DataCompanyOpenCourtAnnouncementContentExWarehouseVO::getCompanyOpenCourtAnnouncementId, Function.identity()));
    }

    @Autowired
    public void setDataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor;
    }
}
