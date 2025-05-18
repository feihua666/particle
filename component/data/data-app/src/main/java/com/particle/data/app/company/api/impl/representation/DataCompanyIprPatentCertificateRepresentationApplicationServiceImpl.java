package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyIprPatentCertificateQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyIprPatentCertificateRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificatePageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyIprPatentCertificateQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCertificateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyIprPatentCertificateExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyIprPatentCertificateExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyIprPatentCertificateExWarehouseCommandExecutor;
/**
 * <p>
 * 企业知识产权专利证书信息 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Service
@CatchAndLog
public class DataCompanyIprPatentCertificateRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyIprPatentCertificateRepresentationApplicationService {

    private DataCompanyIprPatentCertificateQueryCommandExecutor dataCompanyIprPatentCertificateQueryCommandExecutor;
    private DataCompanyIprPatentCertificateExWarehouseCommandExecutor dataCompanyIprPatentCertificateExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyIprPatentCertificateVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyIprPatentCertificateQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyIprPatentCertificateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyIprPatentCertificateQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyIprPatentCertificateVO> pageQuery(DataCompanyIprPatentCertificatePageQueryCommand dataCompanyIprPatentCertificatePageQueryCommand) {
        return dataCompanyIprPatentCertificateQueryCommandExecutor.execute(dataCompanyIprPatentCertificatePageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyIprPatentCertificateVO> queryList(DataCompanyIprPatentCertificateQueryListCommand dataCompanyIprPatentCertificateQueryListCommand) {
        return dataCompanyIprPatentCertificateQueryCommandExecutor.execute(dataCompanyIprPatentCertificateQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyIprPatentCertificateExWarehouseVO> exWarehouse(DataCompanyIprPatentCertificateExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyIprPatentCertificateExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyIprPatentCertificateQueryCommandExecutor(DataCompanyIprPatentCertificateQueryCommandExecutor dataCompanyIprPatentCertificateQueryCommandExecutor) {
        this.dataCompanyIprPatentCertificateQueryCommandExecutor = dataCompanyIprPatentCertificateQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyIprPatentCertificateExWarehouseCommandExecutor(DataCompanyIprPatentCertificateExWarehouseCommandExecutor dataCompanyIprPatentCertificateExWarehouseCommandExecutor) {
        this.dataCompanyIprPatentCertificateExWarehouseCommandExecutor = dataCompanyIprPatentCertificateExWarehouseCommandExecutor;
    }
}
