package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyJudgmentDocumentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentContentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyJudgmentDocumentPartyExWarehouseVO;
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
 * 企业裁判文书出库
 * </p>
 *
 * @author yangwei
 * @since 2025/5/17 17:36
 */
@Component
@Validated
public class DataCompanyJudgmentDocumentWrapExWarehouseCommandExecutor extends AbstractBaseWrapExWarehouseCommandExecutor{

    private DataCompanyJudgmentDocumentExWarehouseCommandExecutor dataCompanyJudgmentDocumentExWarehouseCommandExecutor;
    private DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;
    private DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;

    /**
     * 企业裁判文书出库
     * @param dataCompanyExWarehouseQueryCommand
     * @param dataCompanyJudgmentDocumentExWarehouseQueryCommand
     * @return
     */
    public PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                               DataCompanyJudgmentDocumentExWarehouseQueryCommand dataCompanyJudgmentDocumentExWarehouseQueryCommand) {
        if (dataCompanyJudgmentDocumentExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyJudgmentDocumentExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyJudgmentDocumentExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyJudgmentDocumentExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyJudgmentDocumentExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyJudgmentDocumentExWarehouseVO> response = dataCompanyJudgmentDocumentExWarehouseCommandExecutor.exWarehouse(dataCompanyJudgmentDocumentExWarehouseQueryCommand);
        if (CollectionUtil.isNotEmpty(response.getData())) {
            List<Long> ids = response.getData().stream().map(item -> item.getId()).collect(Collectors.toList());
            Map<Long, List<DataCompanyJudgmentDocumentPartyExWarehouseVO>> parties = parties(ids);
            Map<Long, DataCompanyJudgmentDocumentContentExWarehouseVO> content = content(ids);
            for (DataCompanyJudgmentDocumentExWarehouseVO datum : response.getData()) {
                datum.setParties(parties.get(datum.getId()));
                datum.setContent(content.get(datum.getId()));
            }
        }
        return response;
    }
    /**
     * 获取当事人信息
     * @param companyJudgmentDocumentIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyJudgmentDocumentPartyExWarehouseVO>> parties(List<Long> companyJudgmentDocumentIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyJudgmentDocumentPartyExWarehouseVO> multiResponse = dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor.exWarehouseByCompanyJudgmentDocumentIds(companyJudgmentDocumentIds);
        List<DataCompanyJudgmentDocumentPartyExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyJudgmentDocumentPartyExWarehouseVO::getCompanyJudgmentDocumentId));
    }
    /**
     * 获取内容信息
     * @param companyJudgmentDocumentIds 法院公告 ID 列表
     * @return 按法院公告 ID 分组的内容信息
     */
    private Map<Long, DataCompanyJudgmentDocumentContentExWarehouseVO> content(List<Long> companyJudgmentDocumentIds) {
        // 调用命令执行器查询内容信息
        MultiResponse<DataCompanyJudgmentDocumentContentExWarehouseVO> multiResponse = dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor.exWarehouseByCompanyJudgmentDocumentIds(companyJudgmentDocumentIds);
        List<DataCompanyJudgmentDocumentContentExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按法院公告 ID 分组返回结果
        return data.stream()
                .collect(Collectors.toMap(DataCompanyJudgmentDocumentContentExWarehouseVO::getCompanyJudgmentDocumentId, Function.identity()));
    }

    @Autowired
    public void setDataCompanyJudgmentDocumentExWarehouseCommandExecutor(DataCompanyJudgmentDocumentExWarehouseCommandExecutor dataCompanyJudgmentDocumentExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentExWarehouseCommandExecutor = dataCompanyJudgmentDocumentExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor(DataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor = dataCompanyJudgmentDocumentPartyExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyJudgmentDocumentContentExWarehouseCommandExecutor(DataCompanyJudgmentDocumentContentExWarehouseCommandExecutor dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor) {
        this.dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor = dataCompanyJudgmentDocumentContentExWarehouseCommandExecutor;
    }
}
