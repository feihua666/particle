package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyCourtAnnouncementContentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyCourtAnnouncementContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业法院公告内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:28
 */
@Service
@CatchAndLog
public class DataCompanyCourtAnnouncementContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCourtAnnouncementContentRepresentationApplicationService {

    private DataCompanyCourtAnnouncementContentQueryCommandExecutor dataCompanyCourtAnnouncementContentQueryCommandExecutor;
    private DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyCourtAnnouncementContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyCourtAnnouncementContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyCourtAnnouncementContentVO> pageQuery(DataCompanyCourtAnnouncementContentPageQueryCommand dataCompanyCourtAnnouncementContentPageQueryCommand) {
        return dataCompanyCourtAnnouncementContentQueryCommandExecutor.execute(dataCompanyCourtAnnouncementContentPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyCourtAnnouncementContentVO> queryList(DataCompanyCourtAnnouncementContentQueryListCommand dataCompanyCourtAnnouncementContentQueryListCommand) {
        return dataCompanyCourtAnnouncementContentQueryCommandExecutor.execute(dataCompanyCourtAnnouncementContentQueryListCommand);
    }


    @Override
    public SingleResponse<DataCompanyCourtAnnouncementContentExWarehouseVO> exWarehouse(DataCompanyCourtAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyCourtAnnouncementContentQueryCommandExecutor(DataCompanyCourtAnnouncementContentQueryCommandExecutor dataCompanyCourtAnnouncementContentQueryCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentQueryCommandExecutor = dataCompanyCourtAnnouncementContentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementContentExWarehouseCommandExecutor(DataCompanyCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor = dataCompanyCourtAnnouncementContentExWarehouseCommandExecutor;
    }
}