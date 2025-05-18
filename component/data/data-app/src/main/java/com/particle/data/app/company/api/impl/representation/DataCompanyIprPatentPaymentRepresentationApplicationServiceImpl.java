package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentPaymentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentPaymentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentPaymentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPaymentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentPaymentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentPaymentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentPaymentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利缴费信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:27
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentPaymentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentPaymentRepresentationApplicationService {

    private DataCompanyIprPatentPaymentQueryCommandExecutor dataCompanyIprPatentPaymentQueryCommandExecutor;
    private DataCompanyIprPatentPaymentExWarehouseCommandExecutor dataCompanyIprPatentPaymentExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentPaymentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentPaymentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentPaymentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentPaymentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentPaymentVO> pageQuery(DataCompanyIprPatentPaymentPageQueryCommand dataCompanyIprPatentPaymentPageQueryCommand) {
        return dataCompanyIprPatentPaymentQueryCommandExecutor.execute(dataCompanyIprPatentPaymentPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentPaymentVO> queryList(DataCompanyIprPatentPaymentQueryListCommand dataCompanyIprPatentPaymentQueryListCommand) {
        return dataCompanyIprPatentPaymentQueryCommandExecutor.execute(dataCompanyIprPatentPaymentQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentPaymentExWarehouseVO> exWarehouse(DataCompanyIprPatentPaymentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentPaymentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentPaymentQueryCommandExecutor(DataCompanyIprPatentPaymentQueryCommandExecutor dataCompanyIprPatentPaymentQueryCommandExecutor) {
        this.dataCompanyIprPatentPaymentQueryCommandExecutor = dataCompanyIprPatentPaymentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentPaymentExWarehouseCommandExecutor(DataCompanyIprPatentPaymentExWarehouseCommandExecutor dataCompanyIprPatentPaymentExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentPaymentExWarehouseCommandExecutor = dataCompanyIprPatentPaymentExWarehouseCommandExecutor;
    }
}
