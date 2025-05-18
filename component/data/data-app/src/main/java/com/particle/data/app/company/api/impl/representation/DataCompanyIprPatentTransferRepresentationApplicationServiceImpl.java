package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentTransferQueryCommandExecutor;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentTransferExWarehouseCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentTransferRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentTransferQueryListCommand;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentTransferExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentTransferVO;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentTransferExWarehouseVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 企业知识产权专利转让信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:51
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentTransferRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentTransferRepresentationApplicationService {

    private DataCompanyIprPatentTransferQueryCommandExecutor dataCompanyIprPatentTransferQueryCommandExecutor;
    private DataCompanyIprPatentTransferExWarehouseCommandExecutor dataCompanyIprPatentTransferExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentTransferVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentTransferQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentTransferVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentTransferQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentTransferVO> pageQuery(DataCompanyIprPatentTransferPageQueryCommand dataCompanyIprPatentTransferPageQueryCommand) {
        return dataCompanyIprPatentTransferQueryCommandExecutor.execute(dataCompanyIprPatentTransferPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentTransferVO> queryList(DataCompanyIprPatentTransferQueryListCommand dataCompanyIprPatentTransferQueryListCommand) {
        return dataCompanyIprPatentTransferQueryCommandExecutor.execute(dataCompanyIprPatentTransferQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentTransferExWarehouseVO> exWarehouse(DataCompanyIprPatentTransferExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentTransferExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }


    @Autowired
    public void setDataCompanyIprPatentTransferQueryCommandExecutor(DataCompanyIprPatentTransferQueryCommandExecutor dataCompanyIprPatentTransferQueryCommandExecutor) {
        this.dataCompanyIprPatentTransferQueryCommandExecutor = dataCompanyIprPatentTransferQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentTransferExWarehouseCommandExecutor(DataCompanyIprPatentTransferExWarehouseCommandExecutor dataCompanyIprPatentTransferExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentTransferExWarehouseCommandExecutor = dataCompanyIprPatentTransferExWarehouseCommandExecutor;
    }
}
