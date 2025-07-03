package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsTemplateContentCreateCommandExecutor;
import com.particle.cms.app.executor.CmsTemplateContentDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsTemplateContentUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsTemplateContentCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsTemplateContentUpdateCommand;
import com.particle.cms.client.api.ICmsTemplateContentApplicationService;
import com.particle.cms.client.dto.command.CmsTemplateContentCreateCommand;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 模板内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Transactional
@Service
@CatchAndLog
public class CmsTemplateContentApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsTemplateContentApplicationService {

    private CmsTemplateContentCreateCommandExecutor cmsTemplateContentCreateCommandExecutor;

    private CmsTemplateContentDeleteCommandExecutor cmsTemplateContentDeleteCommandExecutor;

    private CmsTemplateContentUpdateCommandExecutor cmsTemplateContentUpdateCommandExecutor;

    private CmsTemplateContentCommandExecutor cmsTemplateContentCommandExecutor;


    @Override
    public SingleResponse<CmsTemplateContentVO> create(CmsTemplateContentCreateCommand cmsTemplateContentCreateCommand) {
        return cmsTemplateContentCreateCommandExecutor.execute(cmsTemplateContentCreateCommand);
    }

    @Override
    public SingleResponse<CmsTemplateContentVO> delete(IdCommand deleteCommand) {
        return cmsTemplateContentDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsTemplateContentVO> update(CmsTemplateContentUpdateCommand cmsTemplateContentUpdateCommand) {
        return cmsTemplateContentUpdateCommandExecutor.execute(cmsTemplateContentUpdateCommand);
    }


    @Autowired
    public void setCmsTemplateContentCreateCommandExecutor(CmsTemplateContentCreateCommandExecutor cmsTemplateContentCreateCommandExecutor) {
        this.cmsTemplateContentCreateCommandExecutor = cmsTemplateContentCreateCommandExecutor;
    }

    @Autowired
    public void setCmsTemplateContentDeleteCommandExecutor(CmsTemplateContentDeleteCommandExecutor cmsTemplateContentDeleteCommandExecutor) {
        this.cmsTemplateContentDeleteCommandExecutor = cmsTemplateContentDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsTemplateContentUpdateCommandExecutor(CmsTemplateContentUpdateCommandExecutor cmsTemplateContentUpdateCommandExecutor) {
        this.cmsTemplateContentUpdateCommandExecutor = cmsTemplateContentUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsTemplateContentCommandExecutor(CmsTemplateContentCommandExecutor cmsTemplateContentCommandExecutor) {
        this.cmsTemplateContentCommandExecutor = cmsTemplateContentCommandExecutor;
    }
}
