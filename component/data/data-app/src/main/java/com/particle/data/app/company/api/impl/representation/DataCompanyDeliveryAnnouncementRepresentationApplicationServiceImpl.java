package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyDeliveryAnnouncementQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyDeliveryAnnouncementRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;
/**
 * <p>
 * 企业送达公告 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:06
 */
@Service
@CatchAndLog
public class DataCompanyDeliveryAnnouncementRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyDeliveryAnnouncementRepresentationApplicationService {

    private DataCompanyDeliveryAnnouncementQueryCommandExecutor dataCompanyDeliveryAnnouncementQueryCommandExecutor;
    private DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyDeliveryAnnouncementQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyDeliveryAnnouncementQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyDeliveryAnnouncementVO> pageQuery(DataCompanyDeliveryAnnouncementPageQueryCommand dataCompanyDeliveryAnnouncementPageQueryCommand) {
        return dataCompanyDeliveryAnnouncementQueryCommandExecutor.execute(dataCompanyDeliveryAnnouncementPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyDeliveryAnnouncementVO> queryList(DataCompanyDeliveryAnnouncementQueryListCommand dataCompanyDeliveryAnnouncementQueryListCommand) {
        return dataCompanyDeliveryAnnouncementQueryCommandExecutor.execute(dataCompanyDeliveryAnnouncementQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyDeliveryAnnouncementExWarehouseVO> exWarehouse(DataCompanyDeliveryAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyDeliveryAnnouncementQueryCommandExecutor(DataCompanyDeliveryAnnouncementQueryCommandExecutor dataCompanyDeliveryAnnouncementQueryCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementQueryCommandExecutor = dataCompanyDeliveryAnnouncementQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementExWarehouseCommandExecutor;
    }
}
