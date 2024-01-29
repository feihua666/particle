package com.particle.dataquery.app.datasource.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiDataApiQueryCommandExecutor;
import com.particle.dataquery.app.datasource.executor.representation.DataQueryDatasourceApiTestCommandExecutor;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataquery.app.datasource.executor.representation.DataQueryDatasourceApiQueryCommandExecutor;
import com.particle.dataquery.client.datasource.api.representation.IDataQueryDatasourceApiRepresentationApplicationService;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiPageQueryCommand;
import com.particle.dataquery.client.datasource.dto.command.representation.DataQueryDatasourceApiQueryListCommand;
import com.particle.dataquery.client.datasource.dto.data.DataQueryDatasourceApiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 数据查询数据源接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Service
@CatchAndLog
public class DataQueryDatasourceApiRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataQueryDatasourceApiRepresentationApplicationService {

    private DataQueryDatasourceApiQueryCommandExecutor dataQueryDatasourceApiQueryCommandExecutor;
    private DataQueryDatasourceApiTestCommandExecutor dataQueryDatasourceApiTestCommandExecutor;
    private DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor;
    @Override
    public SingleResponse<DataQueryDatasourceApiVO> queryDetail(IdCommand detailCommand) {
        return dataQueryDatasourceApiQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataQueryDatasourceApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataQueryDatasourceApiQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataQueryDatasourceApiVO> pageQuery(DataQueryDatasourceApiPageQueryCommand dataQueryDatasourceApiPageQueryCommand) {
        return dataQueryDatasourceApiQueryCommandExecutor.execute(dataQueryDatasourceApiPageQueryCommand);
    }

    @Override
    public Object test(DataQueryDatasourceApiQueryCommand dataQueryDatasourceApiQueryCommand) {
        return dataQueryDatasourceApiTestCommandExecutor.test(dataQueryDatasourceApiQueryCommand);
    }

    @Override
    public Response warmUpForLight() {
        return dataQueryDataApiDataApiQueryCommandExecutor.warmUpLightForDataqueryDatasourceApi();
    }

    @Override
    public MultiResponse<DataQueryDatasourceApiVO> queryList(DataQueryDatasourceApiQueryListCommand dataQueryDatasourceApiQueryListCommand) {
        return dataQueryDatasourceApiQueryCommandExecutor.execute(dataQueryDatasourceApiQueryListCommand);
    }

    @Autowired
    public void setDataQueryDatasourceApiQueryCommandExecutor(DataQueryDatasourceApiQueryCommandExecutor dataQueryDatasourceApiQueryCommandExecutor) {
        this.dataQueryDatasourceApiQueryCommandExecutor = dataQueryDatasourceApiQueryCommandExecutor;
    }

    @Autowired
    public void setDataQueryDatasourceApiTestCommandExecutor(DataQueryDatasourceApiTestCommandExecutor dataQueryDatasourceApiTestCommandExecutor) {
        this.dataQueryDatasourceApiTestCommandExecutor = dataQueryDatasourceApiTestCommandExecutor;
    }
    @Autowired
    public void setDataQueryDataApiDataApiQueryCommandExecutor(DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor) {
        this.dataQueryDataApiDataApiQueryCommandExecutor = dataQueryDataApiDataApiQueryCommandExecutor;
    }
}
