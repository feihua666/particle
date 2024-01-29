package com.particle.dataquery.app.datasource.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiDataApiQueryCommandExecutor;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataquery.app.datasource.executor.representation.DataQueryDatasourceQueryCommandExecutor;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceRepresentationApplicationService;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourcePageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 数据查询数据源 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Service
@CatchAndLog
public class DataQueryDatasourceRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataQueryDatasourceRepresentationApplicationService {

    private DataQueryDatasourceQueryCommandExecutor dataQueryDatasourceQueryCommandExecutor;
    private DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor;
    @Override
    public SingleResponse<DataQueryDatasourceVO> queryDetail(IdCommand detailCommand) {
        return dataQueryDatasourceQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataQueryDatasourceVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataQueryDatasourceQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataQueryDatasourceVO> pageQuery(DataQueryDatasourcePageQueryCommand dataQueryDatasourcePageQueryCommand) {
        return dataQueryDatasourceQueryCommandExecutor.execute(dataQueryDatasourcePageQueryCommand);
    }

    @Override
    public Response warmUpForLight() {
        return dataQueryDataApiDataApiQueryCommandExecutor.warmUpLightForDataqueryDatasource();
    }

    @Override
    public MultiResponse<DataQueryDatasourceVO> queryList(DataQueryDatasourceQueryListCommand dataQueryDatasourceQueryListCommand) {
        return dataQueryDatasourceQueryCommandExecutor.execute(dataQueryDatasourceQueryListCommand);
    }

    @Autowired
    public void setDataQueryDatasourceQueryCommandExecutor(DataQueryDatasourceQueryCommandExecutor dataQueryDatasourceQueryCommandExecutor) {
        this.dataQueryDatasourceQueryCommandExecutor = dataQueryDatasourceQueryCommandExecutor;
    }
    @Autowired
    public void setDataQueryDataApiDataApiQueryCommandExecutor(DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor) {
        this.dataQueryDataApiDataApiQueryCommandExecutor = dataQueryDataApiDataApiQueryCommandExecutor;
    }
}
