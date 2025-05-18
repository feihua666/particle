package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyCourtAnnouncementQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyCourtAnnouncementRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementExWarehouseCommandExecutor;
/**
 * <p>
 * 企业法院公告 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Service
@CatchAndLog
public class DataCompanyCourtAnnouncementRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCourtAnnouncementRepresentationApplicationService {

    private DataCompanyCourtAnnouncementQueryCommandExecutor dataCompanyCourtAnnouncementQueryCommandExecutor;
    private DataCompanyCourtAnnouncementExWarehouseCommandExecutor dataCompanyCourtAnnouncementExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyCourtAnnouncementQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyCourtAnnouncementQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyCourtAnnouncementVO> pageQuery(DataCompanyCourtAnnouncementPageQueryCommand dataCompanyCourtAnnouncementPageQueryCommand) {
        return dataCompanyCourtAnnouncementQueryCommandExecutor.execute(dataCompanyCourtAnnouncementPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyCourtAnnouncementVO> queryList(DataCompanyCourtAnnouncementQueryListCommand dataCompanyCourtAnnouncementQueryListCommand) {
        return dataCompanyCourtAnnouncementQueryCommandExecutor.execute(dataCompanyCourtAnnouncementQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyCourtAnnouncementExWarehouseVO> exWarehouse(DataCompanyCourtAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyCourtAnnouncementExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyCourtAnnouncementQueryCommandExecutor(DataCompanyCourtAnnouncementQueryCommandExecutor dataCompanyCourtAnnouncementQueryCommandExecutor) {
        this.dataCompanyCourtAnnouncementQueryCommandExecutor = dataCompanyCourtAnnouncementQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementExWarehouseCommandExecutor(DataCompanyCourtAnnouncementExWarehouseCommandExecutor dataCompanyCourtAnnouncementExWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementExWarehouseCommandExecutor = dataCompanyCourtAnnouncementExWarehouseCommandExecutor;
    }
}
