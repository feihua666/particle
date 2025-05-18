package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyOpenCourtAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyOpenCourtAnnouncementPartyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业开庭公告当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:03
 */
@Service
@CatchAndLog
public class DataCompanyOpenCourtAnnouncementPartyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyOpenCourtAnnouncementPartyRepresentationApplicationService {

    private DataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor dataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor;
    private DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyOpenCourtAnnouncementPartyVO> pageQuery(DataCompanyOpenCourtAnnouncementPartyPageQueryCommand dataCompanyOpenCourtAnnouncementPartyPageQueryCommand) {
        return dataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor.execute(dataCompanyOpenCourtAnnouncementPartyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyOpenCourtAnnouncementPartyVO> queryList(DataCompanyOpenCourtAnnouncementPartyQueryListCommand dataCompanyOpenCourtAnnouncementPartyQueryListCommand) {
        return dataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor.execute(dataCompanyOpenCourtAnnouncementPartyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyOpenCourtAnnouncementPartyExWarehouseVO> exWarehouse(DataCompanyOpenCourtAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor(DataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor dataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor = dataCompanyOpenCourtAnnouncementPartyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor(DataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor) {
        this.dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor = dataCompanyOpenCourtAnnouncementPartyExWarehouseCommandExecutor;
    }
}
