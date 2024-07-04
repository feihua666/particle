package com.particle.dataconstraint.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataconstraint.app.executor.representation.DataScopeQueryCommandExecutor;
import com.particle.dataconstraint.client.api.representation.IDataScopeRepresentationApplicationService;
import com.particle.dataconstraint.client.dto.command.representation.DataScopePageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 数据范围 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Service
@CatchAndLog
public class DataScopeRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataScopeRepresentationApplicationService {

    private DataScopeQueryCommandExecutor dataScopeQueryCommandExecutor;

    @Override
    public SingleResponse<DataScopeVO> queryDetail(IdCommand detailCommand) {
        return dataScopeQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataScopeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataScopeQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataScopeVO> pageQuery(DataScopePageQueryCommand dataScopePageQueryCommand) {
        return dataScopeQueryCommandExecutor.execute(dataScopePageQueryCommand);
    }

    @Override
    public MultiResponse<DataScopeVO> queryList(DataScopeQueryListCommand dataScopeQueryListCommand) {
        return dataScopeQueryCommandExecutor.execute(dataScopeQueryListCommand);
    }

    @Autowired
    public void setDataScopeQueryCommandExecutor(DataScopeQueryCommandExecutor dataScopeQueryCommandExecutor) {
        this.dataScopeQueryCommandExecutor = dataScopeQueryCommandExecutor;
    }
}
