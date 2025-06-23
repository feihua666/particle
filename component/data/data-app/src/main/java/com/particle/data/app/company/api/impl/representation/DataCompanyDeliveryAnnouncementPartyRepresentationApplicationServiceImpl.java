package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyDeliveryAnnouncementPartyQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyDeliveryAnnouncementPartyRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPartyPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPartyQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementPartyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;
/**
 * <p>
 * 企业送达公告当事人 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:33
 */
@Service
@CatchAndLog
public class DataCompanyDeliveryAnnouncementPartyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyDeliveryAnnouncementPartyRepresentationApplicationService {

    private DataCompanyDeliveryAnnouncementPartyQueryCommandExecutor dataCompanyDeliveryAnnouncementPartyQueryCommandExecutor;
    private DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyDeliveryAnnouncementPartyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementPartyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyDeliveryAnnouncementPartyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyDeliveryAnnouncementPartyVO> pageQuery(DataCompanyDeliveryAnnouncementPartyPageQueryCommand dataCompanyDeliveryAnnouncementPartyPageQueryCommand) {
        return dataCompanyDeliveryAnnouncementPartyQueryCommandExecutor.execute(dataCompanyDeliveryAnnouncementPartyPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyDeliveryAnnouncementPartyVO> queryList(DataCompanyDeliveryAnnouncementPartyQueryListCommand dataCompanyDeliveryAnnouncementPartyQueryListCommand) {
        return dataCompanyDeliveryAnnouncementPartyQueryCommandExecutor.execute(dataCompanyDeliveryAnnouncementPartyQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyDeliveryAnnouncementPartyExWarehouseVO> exWarehouse(DataCompanyDeliveryAnnouncementPartyExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyQueryCommandExecutor(DataCompanyDeliveryAnnouncementPartyQueryCommandExecutor dataCompanyDeliveryAnnouncementPartyQueryCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementPartyQueryCommandExecutor = dataCompanyDeliveryAnnouncementPartyQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementPartyExWarehouseCommandExecutor;
    }
}
