package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsContentCreateCommandExecutor;
import com.particle.cms.app.executor.CmsContentDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsContentUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsContentCommandExecutor;
import com.particle.cms.client.dto.command.CmsContentPublicCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsContentUpdateCommand;
import com.particle.cms.client.api.ICmsContentApplicationService;
import com.particle.cms.client.dto.command.CmsContentCreateCommand;
import com.particle.cms.client.dto.data.CmsContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Transactional
@Service
@CatchAndLog
public class CmsContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsContentApplicationService {

    private CmsContentCreateCommandExecutor cmsContentCreateCommandExecutor;

    private CmsContentDeleteCommandExecutor cmsContentDeleteCommandExecutor;

    private CmsContentUpdateCommandExecutor cmsContentUpdateCommandExecutor;

    private CmsContentCommandExecutor cmsContentCommandExecutor;


    @Override
    public SingleResponse<CmsContentVO> create(CmsContentCreateCommand cmsContentCreateCommand) {
        return cmsContentCreateCommandExecutor.execute(cmsContentCreateCommand);
    }

    @Override
    public SingleResponse<CmsContentVO> delete(IdCommand deleteCommand) {
        return cmsContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsContentVO> update(CmsContentUpdateCommand cmsContentUpdateCommand) {
        return cmsContentUpdateCommandExecutor.execute(cmsContentUpdateCommand);
    }

    @Override
    public SingleResponse<CmsContentVO> publish(CmsContentPublicCommand cmsContentPublicCommand) {
        return cmsContentUpdateCommandExecutor.publish(cmsContentPublicCommand);
    }


    @Autowired
    public void setCmsContentCreateCommandExecutor(CmsContentCreateCommandExecutor cmsContentCreateCommandExecutor) {
        this.cmsContentCreateCommandExecutor = cmsContentCreateCommandExecutor;
    }

    @Autowired
    public void setCmsContentDeleteCommandExecutor(CmsContentDeleteCommandExecutor cmsContentDeleteCommandExecutor) {
        this.cmsContentDeleteCommandExecutor = cmsContentDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsContentUpdateCommandExecutor(CmsContentUpdateCommandExecutor cmsContentUpdateCommandExecutor) {
        this.cmsContentUpdateCommandExecutor = cmsContentUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsContentCommandExecutor(CmsContentCommandExecutor cmsContentCommandExecutor) {
        this.cmsContentCommandExecutor = cmsContentCommandExecutor;
    }
}
