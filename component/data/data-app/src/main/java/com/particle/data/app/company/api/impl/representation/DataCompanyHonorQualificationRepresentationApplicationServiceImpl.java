package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyHonorQualificationQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyHonorQualificationRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyHonorQualificationQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyHonorQualificationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyHonorQualificationExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyHonorQualificationExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyHonorQualificationExWarehouseCommandExecutor;
/**
 * <p>
 * 企业荣誉资质 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
@Service
@CatchAndLog
public class DataCompanyHonorQualificationRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyHonorQualificationRepresentationApplicationService {

    private DataCompanyHonorQualificationQueryCommandExecutor dataCompanyHonorQualificationQueryCommandExecutor;
    private DataCompanyHonorQualificationExWarehouseCommandExecutor dataCompanyHonorQualificationExWarehouseCommandExecutor;

    @Override
    public SingleResponse<DataCompanyHonorQualificationVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyHonorQualificationQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyHonorQualificationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyHonorQualificationQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyHonorQualificationVO> pageQuery(DataCompanyHonorQualificationPageQueryCommand dataCompanyHonorQualificationPageQueryCommand) {
        return dataCompanyHonorQualificationQueryCommandExecutor.execute(dataCompanyHonorQualificationPageQueryCommand);
    }

    @Override
    public MultiResponse<DataCompanyHonorQualificationVO> queryList(DataCompanyHonorQualificationQueryListCommand dataCompanyHonorQualificationQueryListCommand) {
        return dataCompanyHonorQualificationQueryCommandExecutor.execute(dataCompanyHonorQualificationQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyHonorQualificationExWarehouseVO> exWarehouse(DataCompanyHonorQualificationExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyHonorQualificationExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }



    @Autowired
    public void setDataCompanyHonorQualificationQueryCommandExecutor(DataCompanyHonorQualificationQueryCommandExecutor dataCompanyHonorQualificationQueryCommandExecutor) {
        this.dataCompanyHonorQualificationQueryCommandExecutor = dataCompanyHonorQualificationQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyHonorQualificationExWarehouseCommandExecutor(DataCompanyHonorQualificationExWarehouseCommandExecutor dataCompanyHonorQualificationExWarehouseCommandExecutor) {
        this.dataCompanyHonorQualificationExWarehouseCommandExecutor = dataCompanyHonorQualificationExWarehouseCommandExecutor;
    }
}
