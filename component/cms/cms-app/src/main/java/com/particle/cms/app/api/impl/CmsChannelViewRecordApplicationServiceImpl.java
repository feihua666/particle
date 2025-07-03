package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsChannelViewRecordCreateCommandExecutor;
import com.particle.cms.app.executor.CmsChannelViewRecordDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsChannelViewRecordUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsChannelViewRecordCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsChannelViewRecordUpdateCommand;
import com.particle.cms.client.api.ICmsChannelViewRecordApplicationService;
import com.particle.cms.client.dto.command.CmsChannelViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 栏目访问记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Transactional
@Service
@CatchAndLog
public class CmsChannelViewRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsChannelViewRecordApplicationService {

    private CmsChannelViewRecordCreateCommandExecutor cmsChannelViewRecordCreateCommandExecutor;

    private CmsChannelViewRecordDeleteCommandExecutor cmsChannelViewRecordDeleteCommandExecutor;

    private CmsChannelViewRecordUpdateCommandExecutor cmsChannelViewRecordUpdateCommandExecutor;

    private CmsChannelViewRecordCommandExecutor cmsChannelViewRecordCommandExecutor;


    @Override
    public SingleResponse<CmsChannelViewRecordVO> create(CmsChannelViewRecordCreateCommand cmsChannelViewRecordCreateCommand) {
        return cmsChannelViewRecordCreateCommandExecutor.execute(cmsChannelViewRecordCreateCommand);
    }

    @Override
    public SingleResponse<CmsChannelViewRecordVO> delete(IdCommand deleteCommand) {
        return cmsChannelViewRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsChannelViewRecordVO> update(CmsChannelViewRecordUpdateCommand cmsChannelViewRecordUpdateCommand) {
        return cmsChannelViewRecordUpdateCommandExecutor.execute(cmsChannelViewRecordUpdateCommand);
    }


    @Autowired
    public void setCmsChannelViewRecordCreateCommandExecutor(CmsChannelViewRecordCreateCommandExecutor cmsChannelViewRecordCreateCommandExecutor) {
        this.cmsChannelViewRecordCreateCommandExecutor = cmsChannelViewRecordCreateCommandExecutor;
    }

    @Autowired
    public void setCmsChannelViewRecordDeleteCommandExecutor(CmsChannelViewRecordDeleteCommandExecutor cmsChannelViewRecordDeleteCommandExecutor) {
        this.cmsChannelViewRecordDeleteCommandExecutor = cmsChannelViewRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsChannelViewRecordUpdateCommandExecutor(CmsChannelViewRecordUpdateCommandExecutor cmsChannelViewRecordUpdateCommandExecutor) {
        this.cmsChannelViewRecordUpdateCommandExecutor = cmsChannelViewRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsChannelViewRecordCommandExecutor(CmsChannelViewRecordCommandExecutor cmsChannelViewRecordCommandExecutor) {
        this.cmsChannelViewRecordCommandExecutor = cmsChannelViewRecordCommandExecutor;
    }
}
