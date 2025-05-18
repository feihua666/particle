package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyOpenCourtAnnouncementQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyOpenCourtAnnouncementRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;
/**
 * <p>
 * 企业开庭公告 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Service
@CatchAndLog
public class DataCompanyOpenCourtAnnouncementRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyOpenCourtAnnouncementRepresentationApplicationService {

    private DataCompanyOpenCourtAnnouncementQueryCommandExecutor dataCompanyOpenCourtAnnouncementQueryCommandExecutor;
    private DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyOpenCourtAnnouncementQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyOpenCourtAnnouncementQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyOpenCourtAnnouncementVO> pageQuery(DataCompanyOpenCourtAnnouncementPageQueryCommand dataCompanyOpenCourtAnnouncementPageQueryCommand) {
        return dataCompanyOpenCourtAnnouncementQueryCommandExecutor.execute(dataCompanyOpenCourtAnnouncementPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyOpenCourtAnnouncementVO> queryList(DataCompanyOpenCourtAnnouncementQueryListCommand dataCompanyOpenCourtAnnouncementQueryListCommand) {
        return dataCompanyOpenCourtAnnouncementQueryCommandExecutor.execute(dataCompanyOpenCourtAnnouncementQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyOpenCourtAnnouncementExWarehouseVO> exWarehouse(DataCompanyOpenCourtAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyOpenCourtAnnouncementQueryCommandExecutor(DataCompanyOpenCourtAnnouncementQueryCommandExecutor dataCompanyOpenCourtAnnouncementQueryCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementQueryCommandExecutor = dataCompanyOpenCourtAnnouncementQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementExWarehouseCommandExecutor;
    }
}
