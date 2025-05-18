package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentQuoteQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentQuoteRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuotePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentQuoteQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentQuoteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentQuoteExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentQuoteExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentQuoteExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利引证信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:24
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentQuoteRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentQuoteRepresentationApplicationService {

    private DataCompanyIprPatentQuoteQueryCommandExecutor dataCompanyIprPatentQuoteQueryCommandExecutor;
    private DataCompanyIprPatentQuoteExWarehouseCommandExecutor dataCompanyIprPatentQuoteExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentQuoteVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentQuoteQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentQuoteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentQuoteQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentQuoteVO> pageQuery(DataCompanyIprPatentQuotePageQueryCommand dataCompanyIprPatentQuotePageQueryCommand) {
        return dataCompanyIprPatentQuoteQueryCommandExecutor.execute(dataCompanyIprPatentQuotePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentQuoteVO> queryList(DataCompanyIprPatentQuoteQueryListCommand dataCompanyIprPatentQuoteQueryListCommand) {
        return dataCompanyIprPatentQuoteQueryCommandExecutor.execute(dataCompanyIprPatentQuoteQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentQuoteExWarehouseVO> exWarehouse(DataCompanyIprPatentQuoteExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentQuoteExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentQuoteQueryCommandExecutor(DataCompanyIprPatentQuoteQueryCommandExecutor dataCompanyIprPatentQuoteQueryCommandExecutor) {
        this.dataCompanyIprPatentQuoteQueryCommandExecutor = dataCompanyIprPatentQuoteQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentQuoteExWarehouseCommandExecutor(DataCompanyIprPatentQuoteExWarehouseCommandExecutor dataCompanyIprPatentQuoteExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentQuoteExWarehouseCommandExecutor = dataCompanyIprPatentQuoteExWarehouseCommandExecutor;
    }
}
