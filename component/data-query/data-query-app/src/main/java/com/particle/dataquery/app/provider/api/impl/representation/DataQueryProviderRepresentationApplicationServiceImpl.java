package com.particle.dataquery.app.provider.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.provider.executor.representation.DataQueryProviderQueryCommandExecutor;
import com.particle.dataquery.client.provider.api.representation.IDataQueryProviderRepresentationApplicationService;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderPageQueryCommand;
import com.particle.dataquery.client.provider.dto.command.representation.DataQueryProviderQueryListCommand;
import com.particle.dataquery.client.provider.dto.data.DataQueryProviderVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 数据查询供应商 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-03 19:19:55
 */
@Service
@CatchAndLog
public class DataQueryProviderRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataQueryProviderRepresentationApplicationService {

    private DataQueryProviderQueryCommandExecutor dataQueryProviderQueryCommandExecutor;

    @Override
    public SingleResponse<DataQueryProviderVO> queryDetail(IdCommand detailCommand) {
        return dataQueryProviderQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataQueryProviderVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataQueryProviderQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataQueryProviderVO> pageQuery(DataQueryProviderPageQueryCommand dataQueryProviderPageQueryCommand) {
        return dataQueryProviderQueryCommandExecutor.execute(dataQueryProviderPageQueryCommand);
    }

    @Override
    public MultiResponse<DataQueryProviderVO> queryList(DataQueryProviderQueryListCommand dataQueryProviderQueryListCommand) {
        return dataQueryProviderQueryCommandExecutor.execute(dataQueryProviderQueryListCommand);
    }

    @Autowired
    public void setDataQueryProviderQueryCommandExecutor(DataQueryProviderQueryCommandExecutor dataQueryProviderQueryCommandExecutor) {
        this.dataQueryProviderQueryCommandExecutor = dataQueryProviderQueryCommandExecutor;
    }
}
