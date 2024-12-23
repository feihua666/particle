package com.particle.dataconstraint.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataconstraint.app.executor.representation.DataObjectQueryCommandExecutor;
import com.particle.dataconstraint.client.api.representation.IDataObjectRepresentationApplicationService;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectPageQueryCommand;
import com.particle.dataconstraint.client.dto.command.representation.DataObjectQueryListCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 数据对象 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Service
@CatchAndLog
public class DataObjectRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataObjectRepresentationApplicationService {

    private DataObjectQueryCommandExecutor dataObjectQueryCommandExecutor;

    @Override
    public SingleResponse<DataObjectVO> queryDetail(IdCommand detailCommand) {
        return dataObjectQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataObjectVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataObjectQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataObjectVO> pageQuery(DataObjectPageQueryCommand dataObjectPageQueryCommand) {
        return dataObjectQueryCommandExecutor.execute(dataObjectPageQueryCommand);
    }

    @Override
    public MultiResponse<DataObjectVO> queryList(DataObjectQueryListCommand dataObjectQueryListCommand) {
        return dataObjectQueryCommandExecutor.execute(dataObjectQueryListCommand);
    }

    @Autowired
    public void setDataObjectQueryCommandExecutor(DataObjectQueryCommandExecutor dataObjectQueryCommandExecutor) {
        this.dataObjectQueryCommandExecutor = dataObjectQueryCommandExecutor;
    }
}
