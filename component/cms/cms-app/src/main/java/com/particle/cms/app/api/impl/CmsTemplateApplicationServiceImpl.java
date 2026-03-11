package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsTemplateCreateCommandExecutor;
import com.particle.cms.app.executor.CmsTemplateDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsTemplateUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsTemplateCommandExecutor;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.cms.client.dto.command.CmsTemplateUpdateCommand;
import com.particle.cms.client.api.ICmsTemplateApplicationService;
import com.particle.cms.client.dto.command.CmsTemplateCreateCommand;
import com.particle.cms.client.dto.data.CmsTemplateVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Transactional
@Service
@CatchAndLog
public class CmsTemplateApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsTemplateApplicationService {

    private CmsTemplateCreateCommandExecutor cmsTemplateCreateCommandExecutor;

    private CmsTemplateDeleteCommandExecutor cmsTemplateDeleteCommandExecutor;

    private CmsTemplateUpdateCommandExecutor cmsTemplateUpdateCommandExecutor;

    private CmsTemplateCommandExecutor cmsTemplateCommandExecutor;


    @Override
    public SingleResponse<CmsTemplateVO> create(CmsTemplateCreateCommand cmsTemplateCreateCommand) {
        return cmsTemplateCreateCommandExecutor.execute(cmsTemplateCreateCommand);
    }

    @Override
    public SingleResponse<CmsTemplateVO> delete(CommonIdCommand deleteCommand) {
        return cmsTemplateDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsTemplateVO> update(CmsTemplateUpdateCommand cmsTemplateUpdateCommand) {
        return cmsTemplateUpdateCommandExecutor.execute(cmsTemplateUpdateCommand);
    }


    @Autowired
    public void setCmsTemplateCreateCommandExecutor(CmsTemplateCreateCommandExecutor cmsTemplateCreateCommandExecutor) {
        this.cmsTemplateCreateCommandExecutor = cmsTemplateCreateCommandExecutor;
    }

    @Autowired
    public void setCmsTemplateDeleteCommandExecutor(CmsTemplateDeleteCommandExecutor cmsTemplateDeleteCommandExecutor) {
        this.cmsTemplateDeleteCommandExecutor = cmsTemplateDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsTemplateUpdateCommandExecutor(CmsTemplateUpdateCommandExecutor cmsTemplateUpdateCommandExecutor) {
        this.cmsTemplateUpdateCommandExecutor = cmsTemplateUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsTemplateCommandExecutor(CmsTemplateCommandExecutor cmsTemplateCommandExecutor) {
        this.cmsTemplateCommandExecutor = cmsTemplateCommandExecutor;
    }
}
