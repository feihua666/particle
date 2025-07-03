package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsContentMultimediaCreateCommandExecutor;
import com.particle.cms.app.executor.CmsContentMultimediaDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsContentMultimediaUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsContentMultimediaCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsContentMultimediaUpdateCommand;
import com.particle.cms.client.api.ICmsContentMultimediaApplicationService;
import com.particle.cms.client.dto.command.CmsContentMultimediaCreateCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 内容多媒体 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Transactional
@Service
@CatchAndLog
public class CmsContentMultimediaApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsContentMultimediaApplicationService {

    private CmsContentMultimediaCreateCommandExecutor cmsContentMultimediaCreateCommandExecutor;

    private CmsContentMultimediaDeleteCommandExecutor cmsContentMultimediaDeleteCommandExecutor;

    private CmsContentMultimediaUpdateCommandExecutor cmsContentMultimediaUpdateCommandExecutor;

    private CmsContentMultimediaCommandExecutor cmsContentMultimediaCommandExecutor;


    @Override
    public SingleResponse<CmsContentMultimediaVO> create(CmsContentMultimediaCreateCommand cmsContentMultimediaCreateCommand) {
        return cmsContentMultimediaCreateCommandExecutor.execute(cmsContentMultimediaCreateCommand);
    }

    @Override
    public SingleResponse<CmsContentMultimediaVO> delete(IdCommand deleteCommand) {
        return cmsContentMultimediaDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsContentMultimediaVO> update(CmsContentMultimediaUpdateCommand cmsContentMultimediaUpdateCommand) {
        return cmsContentMultimediaUpdateCommandExecutor.execute(cmsContentMultimediaUpdateCommand);
    }


    @Autowired
    public void setCmsContentMultimediaCreateCommandExecutor(CmsContentMultimediaCreateCommandExecutor cmsContentMultimediaCreateCommandExecutor) {
        this.cmsContentMultimediaCreateCommandExecutor = cmsContentMultimediaCreateCommandExecutor;
    }

    @Autowired
    public void setCmsContentMultimediaDeleteCommandExecutor(CmsContentMultimediaDeleteCommandExecutor cmsContentMultimediaDeleteCommandExecutor) {
        this.cmsContentMultimediaDeleteCommandExecutor = cmsContentMultimediaDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsContentMultimediaUpdateCommandExecutor(CmsContentMultimediaUpdateCommandExecutor cmsContentMultimediaUpdateCommandExecutor) {
        this.cmsContentMultimediaUpdateCommandExecutor = cmsContentMultimediaUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsContentMultimediaCommandExecutor(CmsContentMultimediaCommandExecutor cmsContentMultimediaCommandExecutor) {
        this.cmsContentMultimediaCommandExecutor = cmsContentMultimediaCommandExecutor;
    }
}
