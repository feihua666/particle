package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprGeograApproveAnnouncementQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprGeograApproveAnnouncementQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprGeograApproveAnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权地理标识核准公告 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:21
 */
@Service
@CatchAndLog
public class DataCompanyIprGeograApproveAnnouncementRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprGeograApproveAnnouncementRepresentationApplicationService {

    private DataCompanyIprGeograApproveAnnouncementQueryCommandExecutor dataCompanyIprGeograApproveAnnouncementQueryCommandExecutor;
    private DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprGeograApproveAnnouncementQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprGeograApproveAnnouncementVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprGeograApproveAnnouncementQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprGeograApproveAnnouncementVO> pageQuery(DataCompanyIprGeograApproveAnnouncementPageQueryCommand dataCompanyIprGeograApproveAnnouncementPageQueryCommand) {
        return dataCompanyIprGeograApproveAnnouncementQueryCommandExecutor.execute(dataCompanyIprGeograApproveAnnouncementPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprGeograApproveAnnouncementVO> queryList(DataCompanyIprGeograApproveAnnouncementQueryListCommand dataCompanyIprGeograApproveAnnouncementQueryListCommand) {
        return dataCompanyIprGeograApproveAnnouncementQueryCommandExecutor.execute(dataCompanyIprGeograApproveAnnouncementQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprGeograApproveAnnouncementExWarehouseVO> exWarehouse(DataCompanyIprGeograApproveAnnouncementExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementQueryCommandExecutor(DataCompanyIprGeograApproveAnnouncementQueryCommandExecutor dataCompanyIprGeograApproveAnnouncementQueryCommandExecutor) {
        this.dataCompanyIprGeograApproveAnnouncementQueryCommandExecutor = dataCompanyIprGeograApproveAnnouncementQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor(DataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor) {
        this.dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor = dataCompanyIprGeograApproveAnnouncementExWarehouseCommandExecutor;
    }
}
