package com.particle.data.app.company.executor.representation.exwarehousewrap;

import cn.hutool.core.collection.CollectionUtil;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprGeograExWarehouseCommandExecutor;
import com.particle.data.app.company.executor.warehousewrap.AbstractBaseWrapWarehouseCommandExecutor;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprGeograExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograExWarehouseVO;
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
 * 企业地理标识出库
 * </p>
 *
 * @author yangwei
 * @since 2025-06-17 10:34:00
 */
@Component
@Validated
public class DataCompanyIprGeograWrapExWarehouseCommandExecutor extends AbstractBaseWrapWarehouseCommandExecutor {

    private DataCompanyIprGeograExWarehouseCommandExecutor dataCompanyIprGeograExWarehouseCommandExecutor;
    private DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;

    /**
     * 企业地理标识出库
     * @param dataCompanyIprGeograExWarehouseQueryCommand
     */
    public PageResponse<DataCompanyIprGeograExWarehouseVO> exWarehouse(DataCompanyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand,
                                                                                       DataCompanyIprGeograExWarehouseQueryCommand dataCompanyIprGeograExWarehouseQueryCommand) {
        if (dataCompanyIprGeograExWarehouseQueryCommand.getCompanyId() == null) {
            dataCompanyIprGeograExWarehouseQueryCommand.setCompanyId(dataCompanyExWarehouseQueryCommand.getId());
        }
        if (dataCompanyIprGeograExWarehouseQueryCommand.getCompanyId() == null) {
            Long companyId = getCompanyId(dataCompanyExWarehouseQueryCommand);
            dataCompanyIprGeograExWarehouseQueryCommand.setCompanyId(companyId);
        }
        if (dataCompanyIprGeograExWarehouseQueryCommand.getCompanyId() == null) {
            return PageResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        PageResponse<DataCompanyIprGeograExWarehouseVO> dataCompanyIprGeograExWarehouseVOPageResponse = dataCompanyIprGeograExWarehouseCommandExecutor.exWarehouse(dataCompanyIprGeograExWarehouseQueryCommand);
        List<DataCompanyIprGeograExWarehouseVO> dataCompanyIprGeograExWarehouseVOs = dataCompanyIprGeograExWarehouseVOPageResponse.getData();
        if (CollectionUtil.isNotEmpty(dataCompanyIprGeograExWarehouseVOs)) {
            List<Long> companyIprGeograIds = dataCompanyIprGeograExWarehouseVOs.stream().map(DataCompanyIprGeograExWarehouseVO::getId).collect(Collectors.toList());
            Map<Long, List<DataCompanyIprGeograApproveAnnouncementExWarehouseVO>> approveAnnouncements = approveAnnouncements(companyIprGeograIds);
            for (DataCompanyIprGeograExWarehouseVO dataCompanyIprGeograExWarehouseVO : dataCompanyIprGeograExWarehouseVOs) {
                List<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> dataCompanyIprGeograApproveAnnouncementExWarehouseVOS = approveAnnouncements.get(dataCompanyIprGeograExWarehouseVO.getId());
                dataCompanyIprGeograExWarehouseVO.setApproveAnnouncements(dataCompanyIprGeograApproveAnnouncementExWarehouseVOS);
            }
        }

        return dataCompanyIprGeograExWarehouseVOPageResponse;

    }

    /**
     * 获取核准公告信息
     * @param companyIprGeograIds 地理标识 ID 列表
     * @return 按地理标识 ID 分组的当事人信息
     */
    private Map<Long, List<DataCompanyIprGeograApproveAnnouncementExWarehouseVO>> approveAnnouncements(List<Long> companyIprGeograIds) {
        // 调用命令执行器查询当事人信息
        MultiResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> multiResponse = dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor.exWarehouseByCompanyIprGeograIds(companyIprGeograIds);
        List<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> data = multiResponse.getData();
        // 如果数据为空，返回空 Map
        if (CollectionUtil.isEmpty(data)) {
            return Collections.emptyMap();
        }
        // 按地理标识 ID 分组返回结果
        return data.stream()
                .collect(Collectors.groupingBy(DataCompanyIprGeograApproveAnnouncementExWarehouseVO::getCompanyIprGeograId));
    }
    @Autowired
    public void setDataCompanyIprGeograExWarehouseCommandExecutor(DataCompanyIprGeograExWarehouseCommandExecutor dataCompanyIprGeograExWarehouseCommandExecutor) {
        this.dataCompanyIprGeograExWarehouseCommandExecutor = dataCompanyIprGeograExWarehouseCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor(DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor) {
        this.dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor = dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;
    }
}
