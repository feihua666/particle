package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyCourtAnnouncementPartyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyCourtAnnouncementPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyCourtAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyCourtAnnouncementPartyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业法院公告当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:44
 */
@Service
@CatchAndLog
public class DataCompanyCourtAnnouncementPartyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyCourtAnnouncementPartyRepresentationApplicationService {

    private DataCompanyCourtAnnouncementPartyQueryCommandExecutor dataCompanyCourtAnnouncementPartyQueryCommandExecutor;
    private DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementPartyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyCourtAnnouncementPartyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyCourtAnnouncementPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyCourtAnnouncementPartyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyCourtAnnouncementPartyVO> pageQuery(DataCompanyCourtAnnouncementPartyPageQueryCommand dataCompanyCourtAnnouncementPartyPageQueryCommand) {
        return dataCompanyCourtAnnouncementPartyQueryCommandExecutor.execute(dataCompanyCourtAnnouncementPartyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyCourtAnnouncementPartyVO> queryList(DataCompanyCourtAnnouncementPartyQueryListCommand dataCompanyCourtAnnouncementPartyQueryListCommand) {
        return dataCompanyCourtAnnouncementPartyQueryCommandExecutor.execute(dataCompanyCourtAnnouncementPartyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyCourtAnnouncementPartyExWarehouseVO> exWarehouse(DataCompanyCourtAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyCourtAnnouncementPartyQueryCommandExecutor(DataCompanyCourtAnnouncementPartyQueryCommandExecutor dataCompanyCourtAnnouncementPartyQueryCommandExecutor) {
        this.dataCompanyCourtAnnouncementPartyQueryCommandExecutor = dataCompanyCourtAnnouncementPartyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor(DataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor) {
        this.dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor = dataCompanyCourtAnnouncementPartyExWarehouseCommandExecutor;
    }
}
