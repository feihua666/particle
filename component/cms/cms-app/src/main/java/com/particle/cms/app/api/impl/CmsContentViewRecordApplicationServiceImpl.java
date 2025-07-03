package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsContentViewRecordCreateCommandExecutor;
import com.particle.cms.app.executor.CmsContentViewRecordDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsContentViewRecordUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsContentViewRecordCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsContentViewRecordUpdateCommand;
import com.particle.cms.client.api.ICmsContentViewRecordApplicationService;
import com.particle.cms.client.dto.command.CmsContentViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 内容访问记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Transactional
@Service
@CatchAndLog
public class CmsContentViewRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsContentViewRecordApplicationService {

    private CmsContentViewRecordCreateCommandExecutor cmsContentViewRecordCreateCommandExecutor;

    private CmsContentViewRecordDeleteCommandExecutor cmsContentViewRecordDeleteCommandExecutor;

    private CmsContentViewRecordUpdateCommandExecutor cmsContentViewRecordUpdateCommandExecutor;

    private CmsContentViewRecordCommandExecutor cmsContentViewRecordCommandExecutor;


    @Override
    public SingleResponse<CmsContentViewRecordVO> create(CmsContentViewRecordCreateCommand cmsContentViewRecordCreateCommand) {
        return cmsContentViewRecordCreateCommandExecutor.execute(cmsContentViewRecordCreateCommand);
    }

    @Override
    public SingleResponse<CmsContentViewRecordVO> delete(IdCommand deleteCommand) {
        return cmsContentViewRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsContentViewRecordVO> update(CmsContentViewRecordUpdateCommand cmsContentViewRecordUpdateCommand) {
        return cmsContentViewRecordUpdateCommandExecutor.execute(cmsContentViewRecordUpdateCommand);
    }


    @Autowired
    public void setCmsContentViewRecordCreateCommandExecutor(CmsContentViewRecordCreateCommandExecutor cmsContentViewRecordCreateCommandExecutor) {
        this.cmsContentViewRecordCreateCommandExecutor = cmsContentViewRecordCreateCommandExecutor;
    }

    @Autowired
    public void setCmsContentViewRecordDeleteCommandExecutor(CmsContentViewRecordDeleteCommandExecutor cmsContentViewRecordDeleteCommandExecutor) {
        this.cmsContentViewRecordDeleteCommandExecutor = cmsContentViewRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsContentViewRecordUpdateCommandExecutor(CmsContentViewRecordUpdateCommandExecutor cmsContentViewRecordUpdateCommandExecutor) {
        this.cmsContentViewRecordUpdateCommandExecutor = cmsContentViewRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsContentViewRecordCommandExecutor(CmsContentViewRecordCommandExecutor cmsContentViewRecordCommandExecutor) {
        this.cmsContentViewRecordCommandExecutor = cmsContentViewRecordCommandExecutor;
    }
}
