package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsChannelCreateCommandExecutor;
import com.particle.cms.app.executor.CmsChannelDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsChannelUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsChannelCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsChannelUpdateCommand;
import com.particle.cms.client.api.ICmsChannelApplicationService;
import com.particle.cms.client.dto.command.CmsChannelCreateCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 栏目 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Transactional
@Service
@CatchAndLog
public class CmsChannelApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsChannelApplicationService {

    private CmsChannelCreateCommandExecutor cmsChannelCreateCommandExecutor;

    private CmsChannelDeleteCommandExecutor cmsChannelDeleteCommandExecutor;

    private CmsChannelUpdateCommandExecutor cmsChannelUpdateCommandExecutor;

    private CmsChannelCommandExecutor cmsChannelCommandExecutor;


    @Override
    public SingleResponse<CmsChannelVO> create(CmsChannelCreateCommand cmsChannelCreateCommand) {
        return cmsChannelCreateCommandExecutor.execute(cmsChannelCreateCommand);
    }

    @Override
    public SingleResponse<CmsChannelVO> delete(IdCommand deleteCommand) {
        return cmsChannelDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsChannelVO> update(CmsChannelUpdateCommand cmsChannelUpdateCommand) {
        return cmsChannelUpdateCommandExecutor.execute(cmsChannelUpdateCommand);
    }


    @Autowired
    public void setCmsChannelCreateCommandExecutor(CmsChannelCreateCommandExecutor cmsChannelCreateCommandExecutor) {
        this.cmsChannelCreateCommandExecutor = cmsChannelCreateCommandExecutor;
    }

    @Autowired
    public void setCmsChannelDeleteCommandExecutor(CmsChannelDeleteCommandExecutor cmsChannelDeleteCommandExecutor) {
        this.cmsChannelDeleteCommandExecutor = cmsChannelDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsChannelUpdateCommandExecutor(CmsChannelUpdateCommandExecutor cmsChannelUpdateCommandExecutor) {
        this.cmsChannelUpdateCommandExecutor = cmsChannelUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsChannelCommandExecutor(CmsChannelCommandExecutor cmsChannelCommandExecutor) {
        this.cmsChannelCommandExecutor = cmsChannelCommandExecutor;
    }
}
