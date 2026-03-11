package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsSiteCommandExecutor;
import com.particle.cms.app.executor.CmsSiteCreateCommandExecutor;
import com.particle.cms.app.executor.CmsSiteDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsSiteUpdateCommandExecutor;
import com.particle.cms.client.api.ICmsSiteApplicationService;
import com.particle.cms.client.dto.command.CmsSiteCreateCommand;
import com.particle.cms.client.dto.command.CmsSiteUpdateCommand;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.common.client.dto.command.CommonPublicCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 站点 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Transactional
@Service
@CatchAndLog
public class CmsSiteApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsSiteApplicationService {

    private CmsSiteCreateCommandExecutor cmsSiteCreateCommandExecutor;

    private CmsSiteDeleteCommandExecutor cmsSiteDeleteCommandExecutor;

    private CmsSiteUpdateCommandExecutor cmsSiteUpdateCommandExecutor;

    private CmsSiteCommandExecutor cmsSiteCommandExecutor;


    @Override
    public SingleResponse<CmsSiteVO> create(CmsSiteCreateCommand cmsSiteCreateCommand) {
        return cmsSiteCreateCommandExecutor.execute(cmsSiteCreateCommand);
    }

    @Override
    public SingleResponse<CmsSiteVO> delete(CommonIdCommand deleteCommand) {
        return cmsSiteDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsSiteVO> update(CmsSiteUpdateCommand cmsSiteUpdateCommand) {
        return cmsSiteUpdateCommandExecutor.execute(cmsSiteUpdateCommand);
    }


    @Override
    public SingleResponse<CmsSiteVO> publish(CommonPublicCommand cmsSitePublicCommand) {
        return cmsSiteUpdateCommandExecutor.publish(cmsSitePublicCommand);
    }

    @Autowired
    public void setCmsSiteCreateCommandExecutor(CmsSiteCreateCommandExecutor cmsSiteCreateCommandExecutor) {
        this.cmsSiteCreateCommandExecutor = cmsSiteCreateCommandExecutor;
    }

    @Autowired
    public void setCmsSiteDeleteCommandExecutor(CmsSiteDeleteCommandExecutor cmsSiteDeleteCommandExecutor) {
        this.cmsSiteDeleteCommandExecutor = cmsSiteDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsSiteUpdateCommandExecutor(CmsSiteUpdateCommandExecutor cmsSiteUpdateCommandExecutor) {
        this.cmsSiteUpdateCommandExecutor = cmsSiteUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsSiteCommandExecutor(CmsSiteCommandExecutor cmsSiteCommandExecutor) {
        this.cmsSiteCommandExecutor = cmsSiteCommandExecutor;
    }
}
