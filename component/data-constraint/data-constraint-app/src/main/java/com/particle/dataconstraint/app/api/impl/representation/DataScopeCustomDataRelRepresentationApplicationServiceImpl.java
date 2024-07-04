package com.particle.dataconstraint.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataconstraint.app.executor.representation.DataScopeCustomDataRelQueryCommandExecutor;
import com.particle.dataconstraint.client.api.representation.IDataScopeCustomDataRelRepresentationApplicationService;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataScopeCustomDataRelQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 数据范围自定义数据关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Service
@CatchAndLog
public class DataScopeCustomDataRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataScopeCustomDataRelRepresentationApplicationService {

    private DataScopeCustomDataRelQueryCommandExecutor dataScopeCustomDataRelQueryCommandExecutor;

    @Override
    public SingleResponse<DataScopeCustomDataRelVO> queryDetail(IdCommand detailCommand) {
        return dataScopeCustomDataRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataScopeCustomDataRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataScopeCustomDataRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataScopeCustomDataRelVO> pageQuery(DataScopeCustomDataRelPageQueryCommand dataScopeCustomDataRelPageQueryCommand) {
        return dataScopeCustomDataRelQueryCommandExecutor.execute(dataScopeCustomDataRelPageQueryCommand);
    }

    @Override
    public MultiResponse<Long> queryCustomDataIdsByDataScopeId(IdCommand dataScopeIdCommand) {
        return dataScopeCustomDataRelQueryCommandExecutor.queryCustomDataIdsByDataScopeId(dataScopeIdCommand);
    }

    @Override
    public MultiResponse<DataScopeCustomDataRelVO> queryList(DataScopeCustomDataRelQueryListCommand dataScopeCustomDataRelQueryListCommand) {
        return dataScopeCustomDataRelQueryCommandExecutor.execute(dataScopeCustomDataRelQueryListCommand);
    }

    @Autowired
    public void setDataScopeCustomDataRelQueryCommandExecutor(DataScopeCustomDataRelQueryCommandExecutor dataScopeCustomDataRelQueryCommandExecutor) {
        this.dataScopeCustomDataRelQueryCommandExecutor = dataScopeCustomDataRelQueryCommandExecutor;
    }
}
