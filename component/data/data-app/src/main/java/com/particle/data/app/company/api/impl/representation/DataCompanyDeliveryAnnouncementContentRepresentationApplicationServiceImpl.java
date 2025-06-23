package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyDeliveryAnnouncementContentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyDeliveryAnnouncementContentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyDeliveryAnnouncementContentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyDeliveryAnnouncementContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业送达公告内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@Service
@CatchAndLog
public class DataCompanyDeliveryAnnouncementContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyDeliveryAnnouncementContentRepresentationApplicationService {

    private DataCompanyDeliveryAnnouncementContentQueryCommandExecutor dataCompanyDeliveryAnnouncementContentQueryCommandExecutor;
    private DataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyDeliveryAnnouncementContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyDeliveryAnnouncementContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyDeliveryAnnouncementContentVO> pageQuery(DataCompanyDeliveryAnnouncementContentPageQueryCommand dataCompanyDeliveryAnnouncementContentPageQueryCommand) {
        return dataCompanyDeliveryAnnouncementContentQueryCommandExecutor.execute(dataCompanyDeliveryAnnouncementContentPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyDeliveryAnnouncementContentVO> queryList(DataCompanyDeliveryAnnouncementContentQueryListCommand dataCompanyDeliveryAnnouncementContentQueryListCommand) {
        return dataCompanyDeliveryAnnouncementContentQueryCommandExecutor.execute(dataCompanyDeliveryAnnouncementContentQueryListCommand);
    }


    @Override
    public SingleResponse<DataCompanyDeliveryAnnouncementContentExWarehouseVO> exWarehouse(DataCompanyDeliveryAnnouncementContentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentQueryCommandExecutor(DataCompanyDeliveryAnnouncementContentQueryCommandExecutor dataCompanyDeliveryAnnouncementContentQueryCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementContentQueryCommandExecutor = dataCompanyDeliveryAnnouncementContentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor(DataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor) {
        this.dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor = dataCompanyDeliveryAnnouncementContentExWarehouseCommandExecutor;
    }
}