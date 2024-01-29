package com.particle.dataquery.app.dataapi.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiDataApiQueryCommandExecutor;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dataquery.app.dataapi.executor.representation.DataQueryDataApiQueryCommandExecutor;
import com.particle.dataquery.client.dataapi.api.representation.IDataQueryDataApiRepresentationApplicationService;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiPageQueryCommand;
import com.particle.dataquery.client.dataapi.dto.command.representation.DataQueryDataApiQueryListCommand;
import com.particle.dataquery.client.dataapi.dto.data.DataQueryDataApiVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 数据查询数据接口 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Service
@CatchAndLog
public class DataQueryDataApiRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDataQueryDataApiRepresentationApplicationService {

    private DataQueryDataApiQueryCommandExecutor dataQueryDataApiQueryCommandExecutor;
    private DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor;

    @Override
    public SingleResponse<DataQueryDataApiVO> queryDetail(IdCommand detailCommand) {
        return dataQueryDataApiQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<DataQueryDataApiVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return dataQueryDataApiQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<DataQueryDataApiVO> pageQuery(DataQueryDataApiPageQueryCommand dataQueryDataApiPageQueryCommand) {
        return dataQueryDataApiQueryCommandExecutor.execute(dataQueryDataApiPageQueryCommand);
    }

    @Override
    public Object dataApiQuery(DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand) {
        return dataQueryDataApiDataApiQueryCommandExecutor.dataApiQuery(dataQueryDataApiQueryCommand);
    }

    @Override
    public Object dataApiQueryTest(DataQueryDataApiQueryCommand dataQueryDataApiQueryCommand) {
        return dataQueryDataApiDataApiQueryCommandExecutor.dataApiQueryTest(dataQueryDataApiQueryCommand);
    }

    @Override
    public Response warmUp() {
        return dataQueryDataApiDataApiQueryCommandExecutor.warmUp();
    }

    @Override
    public Response warmUpForLight() {
        Response response = dataQueryDataApiDataApiQueryCommandExecutor.warmUpLightForDataqueryApi();
        return response;
    }

    @Override
    public MultiResponse<DataQueryDataApiVO> queryList(DataQueryDataApiQueryListCommand dataQueryDataApiQueryListCommand) {
        return dataQueryDataApiQueryCommandExecutor.execute(dataQueryDataApiQueryListCommand);
    }

    @Autowired
    public void setDataQueryDataApiQueryCommandExecutor(DataQueryDataApiQueryCommandExecutor dataQueryDataApiQueryCommandExecutor) {
        this.dataQueryDataApiQueryCommandExecutor = dataQueryDataApiQueryCommandExecutor;
    }

    @Autowired
    public void setDataQueryDataApiDataApiQueryCommandExecutor(DataQueryDataApiDataApiQueryCommandExecutor dataQueryDataApiDataApiQueryCommandExecutor) {
        this.dataQueryDataApiDataApiQueryCommandExecutor = dataQueryDataApiDataApiQueryCommandExecutor;
    }
}
