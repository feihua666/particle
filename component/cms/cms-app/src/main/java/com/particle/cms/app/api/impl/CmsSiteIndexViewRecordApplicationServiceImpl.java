package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsSiteIndexViewRecordCreateCommandExecutor;
import com.particle.cms.app.executor.CmsSiteIndexViewRecordDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsSiteIndexViewRecordUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsSiteIndexViewRecordCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsSiteIndexViewRecordUpdateCommand;
import com.particle.cms.client.api.ICmsSiteIndexViewRecordApplicationService;
import com.particle.cms.client.dto.command.CmsSiteIndexViewRecordCreateCommand;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 站点首页访问记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Transactional
@Service
@CatchAndLog
public class CmsSiteIndexViewRecordApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsSiteIndexViewRecordApplicationService {

    private CmsSiteIndexViewRecordCreateCommandExecutor cmsSiteIndexViewRecordCreateCommandExecutor;

    private CmsSiteIndexViewRecordDeleteCommandExecutor cmsSiteIndexViewRecordDeleteCommandExecutor;

    private CmsSiteIndexViewRecordUpdateCommandExecutor cmsSiteIndexViewRecordUpdateCommandExecutor;

    private CmsSiteIndexViewRecordCommandExecutor cmsSiteIndexViewRecordCommandExecutor;


    @Override
    public SingleResponse<CmsSiteIndexViewRecordVO> create(CmsSiteIndexViewRecordCreateCommand cmsSiteIndexViewRecordCreateCommand) {
        return cmsSiteIndexViewRecordCreateCommandExecutor.execute(cmsSiteIndexViewRecordCreateCommand);
    }

    @Override
    public SingleResponse<CmsSiteIndexViewRecordVO> delete(IdCommand deleteCommand) {
        return cmsSiteIndexViewRecordDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsSiteIndexViewRecordVO> update(CmsSiteIndexViewRecordUpdateCommand cmsSiteIndexViewRecordUpdateCommand) {
        return cmsSiteIndexViewRecordUpdateCommandExecutor.execute(cmsSiteIndexViewRecordUpdateCommand);
    }


    @Autowired
    public void setCmsSiteIndexViewRecordCreateCommandExecutor(CmsSiteIndexViewRecordCreateCommandExecutor cmsSiteIndexViewRecordCreateCommandExecutor) {
        this.cmsSiteIndexViewRecordCreateCommandExecutor = cmsSiteIndexViewRecordCreateCommandExecutor;
    }

    @Autowired
    public void setCmsSiteIndexViewRecordDeleteCommandExecutor(CmsSiteIndexViewRecordDeleteCommandExecutor cmsSiteIndexViewRecordDeleteCommandExecutor) {
        this.cmsSiteIndexViewRecordDeleteCommandExecutor = cmsSiteIndexViewRecordDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsSiteIndexViewRecordUpdateCommandExecutor(CmsSiteIndexViewRecordUpdateCommandExecutor cmsSiteIndexViewRecordUpdateCommandExecutor) {
        this.cmsSiteIndexViewRecordUpdateCommandExecutor = cmsSiteIndexViewRecordUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsSiteIndexViewRecordCommandExecutor(CmsSiteIndexViewRecordCommandExecutor cmsSiteIndexViewRecordCommandExecutor) {
        this.cmsSiteIndexViewRecordCommandExecutor = cmsSiteIndexViewRecordCommandExecutor;
    }
}
