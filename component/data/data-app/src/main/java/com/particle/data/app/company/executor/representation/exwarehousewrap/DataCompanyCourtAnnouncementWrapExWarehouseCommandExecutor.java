package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
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
 * 企业法院公告出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:36
 */
@Component
@Validated
public class DataCompanyCourtAnnouncementWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor{

    private DataCompanyCourtAnnouncementExWarehouseCommandExecutor dataCompanyCourtAnnouncementExWarehouseCommandExecutor;
    private DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
    private DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;

    /**
     * 企业法院公告出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyCourtAnnouncementExWarehouseQueryCommand
     * @return
     */
    public PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                               DataCompanyCourtAnnouncementExWarehouseQueryCommand dataCompanyCourtAnnouncementExWarehouseQueryCommand) {

        if (dataCompanyCourtAnnouncementExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyCourtAnnouncementExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyCourtAnnouncementExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> response = dataCompanyCourtAnnouncementExWarehouseCommandExecutor.exWarehouse(dataCompanyCourtAnnouncementExWarehouseQueryCommand);
        if (CollectionUtil.isNotEmpty(response.getData())) {
            List<Long> ids = response.getData().stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Long, List<DataCompanyCourtAnnouncementPartyExWarehouseVO>> parties = parties(ids);
            Map<Long, DataCompanyCourtAnnouncementContentExWarehouseVO> content = content(ids);
            for (DataCompanyCourtAnnouncementExWarehouseVO datum : response.getData()) {
                datum.setParties(parties.get(datum.getId()));
                datum.setContent(content.get(datum.getId()));
            }
        }
        return response;
    }
    /**
     * 获取当事人信息
     * @param companyCourtAnnouncementIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyCourtAnnouncementPartyExWarehouseVO>> parties(List<Long> companyCourtAnnouncementIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> multiResponse = dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouseByCompanyCourtAnnouncementIds(companyCourtAnnouncementIds);
        List<DataCompanyCourtAnnouncementPartyExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyCourtAnnouncementPartyExWarehouseVO::getCompanyCourtAnnouncementId));
    }
    /**
     * 获取内容信息
     * @param companyCourtAnnouncementIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的内容信息
     */
    private Map<Long, DataCompanyCourtAnnouncementContentExWarehouseVO> content(List<Long> companyCourtAnnouncementIds) {
        // 调用命令执行器查询内容信息
        MultiResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> multiResponse = dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor.exWarehouseByCompanyCourtAnnouncementIds(companyCourtAnnouncementIds);
        List<DataCompanyCourtAnnouncementContentExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.toMap(DataCompanyCourtAnnouncementContentExWarehouseVO::getCompanyCourtAnnouncementId, Function.identity()));
    }

    @Autowired
    public void setDataCompanyCourtAnnouncementExWarehouseCommandExecutor(DataCompanyCourtAnnouncementExWarehouseCommandExecutor dataCompanyCourtAnnouncementExWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementExWarehouseCommandExecutor = dataCompanyCourtAnnouncementExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor(DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor = dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementContentExWarehouseCommandExecutor(DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor = dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;
    }
}
