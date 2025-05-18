package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentNoticeQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentNoticeRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentNoticeQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentNoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentNoticeExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentNoticeExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentNoticeExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利通知书信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentNoticeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentNoticeRepresentationApplicationService {

    private DataCompanyIprPatentNoticeQueryCommandExecutor dataCompanyIprPatentNoticeQueryCommandExecutor;
    private DataCompanyIprPatentNoticeExWarehouseCommandExecutor dataCompanyIprPatentNoticeExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentNoticeVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentNoticeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentNoticeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentNoticeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentNoticeVO> pageQuery(DataCompanyIprPatentNoticePageQueryCommand dataCompanyIprPatentNoticePageQueryCommand) {
        return dataCompanyIprPatentNoticeQueryCommandExecutor.execute(dataCompanyIprPatentNoticePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentNoticeVO> queryList(DataCompanyIprPatentNoticeQueryListCommand dataCompanyIprPatentNoticeQueryListCommand) {
        return dataCompanyIprPatentNoticeQueryCommandExecutor.execute(dataCompanyIprPatentNoticeQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentNoticeExWarehouseVO> exWarehouse(DataCompanyIprPatentNoticeExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentNoticeExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentNoticeQueryCommandExecutor(DataCompanyIprPatentNoticeQueryCommandExecutor dataCompanyIprPatentNoticeQueryCommandExecutor) {
        this.dataCompanyIprPatentNoticeQueryCommandExecutor = dataCompanyIprPatentNoticeQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentNoticeExWarehouseCommandExecutor(DataCompanyIprPatentNoticeExWarehouseCommandExecutor dataCompanyIprPatentNoticeExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentNoticeExWarehouseCommandExecutor = dataCompanyIprPatentNoticeExWarehouseCommandExecutor;
    }
}
