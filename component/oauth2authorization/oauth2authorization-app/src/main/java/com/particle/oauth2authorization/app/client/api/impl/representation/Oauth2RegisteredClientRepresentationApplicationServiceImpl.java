package com.particle.oauth2authorization.app.client.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oauth2authorization.app.client.executor.representation.Oauth2RegisteredClientQueryCommandExecutor;
import com.particle.oauth2authorization.client.client.api.representation.IOauth2RegisteredClientRepresentationApplicationService;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientPageQueryCommand;
import com.particle.oauth2authorization.client.client.dto.command.representation.Oauth2RegisteredClientQueryListCommand;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * oauth2客户端 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Service
@CatchAndLog
public class Oauth2RegisteredClientRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IOauth2RegisteredClientRepresentationApplicationService {

    private Oauth2RegisteredClientQueryCommandExecutor oauth2RegisteredClientQueryCommandExecutor;

    @Override
    public SingleResponse<Oauth2RegisteredClientVO> queryDetail(IdCommand detailCommand) {
        return oauth2RegisteredClientQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<Oauth2RegisteredClientVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return oauth2RegisteredClientQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<Oauth2RegisteredClientVO> pageQuery(Oauth2RegisteredClientPageQueryCommand oauth2RegisteredClientPageQueryCommand) {
        return oauth2RegisteredClientQueryCommandExecutor.execute(oauth2RegisteredClientPageQueryCommand);
    }

    @Override
    public MultiResponse<Oauth2RegisteredClientVO> queryList(Oauth2RegisteredClientQueryListCommand oauth2RegisteredClientQueryListCommand) {
        return oauth2RegisteredClientQueryCommandExecutor.execute(oauth2RegisteredClientQueryListCommand);
    }

    @Autowired
    public void setOauth2RegisteredClientQueryCommandExecutor(Oauth2RegisteredClientQueryCommandExecutor oauth2RegisteredClientQueryCommandExecutor) {
        this.oauth2RegisteredClientQueryCommandExecutor = oauth2RegisteredClientQueryCommandExecutor;
    }
}
