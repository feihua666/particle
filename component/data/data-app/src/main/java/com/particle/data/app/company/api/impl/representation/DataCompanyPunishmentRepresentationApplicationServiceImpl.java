package com.particle.data.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.data.app.company.executor.representation.DataCompanyPunishmentQueryCommandExecutor;
import com.particle.data.client.company.api.representation.IDataCompanyPunishmentRepresentationApplicationService;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentPageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyPunishmentQueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.data.client.company.dto.command.representation.exwarehouse.DataCompanyPunishmentExWarehouseQueryCommand;
import com.particle.data.client.company.dto.data.exwarehouse.DataCompanyPunishmentExWarehouseVO;
import com.particle.data.app.company.executor.representation.exwarehouse.DataCompanyPunishmentExWarehouseCommandExecutor;
/**
 * <p>
 * 企业行政处罚 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Service
@CatchAndLog
public class DataCompanyPunishmentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataCompanyPunishmentRepresentationApplicationService {

    private DataCompanyPunishmentQueryCommandExecutor dataCompanyPunishmentQueryCommandExecutor;

    @Override
    public SingleResponse<DataCompanyPunishmentVO> queryDetail(IdCommand detailCommand) {
        return dataCompanyPunishmentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataCompanyPunishmentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataCompanyPunishmentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataCompanyPunishmentVO> pageQuery(DataCompanyPunishmentPageQueryCommand dataCompanyPunishmentPageQueryCommand) {
        return dataCompanyPunishmentQueryCommandExecutor.execute(dataCompanyPunishmentPageQueryCommand);
    }
    private DataCompanyPunishmentExWarehouseCommandExecutor dataCompanyPunishmentExWarehouseCommandExecutor;

    @Override
    public MultiResponse<DataCompanyPunishmentVO> queryList(DataCompanyPunishmentQueryListCommand dataCompanyPunishmentQueryListCommand) {
        return dataCompanyPunishmentQueryCommandExecutor.execute(dataCompanyPunishmentQueryListCommand);
    }


    @Override
    public PageResponse<DataCompanyPunishmentExWarehouseVO> exWarehouse(DataCompanyPunishmentExWarehouseQueryCommand dataCompanyExWarehouseQueryCommand) {
        return dataCompanyPunishmentExWarehouseCommandExecutor.exWarehouse(dataCompanyExWarehouseQueryCommand);
    }


    @Autowired
    public void setDataCompanyPunishmentQueryCommandExecutor(DataCompanyPunishmentQueryCommandExecutor dataCompanyPunishmentQueryCommandExecutor) {
        this.dataCompanyPunishmentQueryCommandExecutor = dataCompanyPunishmentQueryCommandExecutor;
    }
    @Autowired
    public void setDataCompanyPunishmentExWarehouseCommandExecutor(DataCompanyPunishmentExWarehouseCommandExecutor dataCompanyPunishmentExWarehouseCommandExecutor) {
        this.dataCompanyPunishmentExWarehouseCommandExecutor = dataCompanyPunishmentExWarehouseCommandExecutor;
    }
}
