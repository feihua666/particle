package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyOpenCourtAnnouncementContentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业开庭公告内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:18
 */
@Service
@CatchAndLog
public class DataCompanyOpenCourtAnnouncementContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyOpenCourtAnnouncementContentRepresentationApplicationService {

    private DataCompanyOpenCourtAnnouncementContentQueryCommandExecutor dataCompanyOpenCourtAnnouncementContentQueryCommandExecutor;
    private DataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyOpenCourtAnnouncementContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyOpenCourtAnnouncementContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyOpenCourtAnnouncementContentVO> pageQuery(DataCompanyOpenCourtAnnouncementContentPageQueryCommand dataCompanyOpenCourtAnnouncementContentPageQueryCommand) {
        return dataCompanyOpenCourtAnnouncementContentQueryCommandExecutor.execute(dataCompanyOpenCourtAnnouncementContentPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyOpenCourtAnnouncementContentVO> queryList(DataCompanyOpenCourtAnnouncementContentQueryListCommand dataCompanyOpenCourtAnnouncementContentQueryListCommand) {
        return dataCompanyOpenCourtAnnouncementContentQueryCommandExecutor.execute(dataCompanyOpenCourtAnnouncementContentQueryListCommand);
    }


    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementContentExWarehouseVO> exWarehouse(DataCompanyOpenCourtAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentQueryCommandExecutor(DataCompanyOpenCourtAnnouncementContentQueryCommandExecutor dataCompanyOpenCourtAnnouncementContentQueryCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementContentQueryCommandExecutor = dataCompanyOpenCourtAnnouncementContentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementContentExWarehouseCommandExecutor;
    }
}