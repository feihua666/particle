package com.particle.cms.app.api.impl;

import com.particle.cms.app.executor.CmsContentCategoryCreateCommandExecutor;
import com.particle.cms.app.executor.CmsContentCategoryDeleteCommandExecutor;
import com.particle.cms.app.executor.CmsContentCategoryUpdateCommandExecutor;
import com.particle.cms.app.executor.CmsContentCategoryCommandExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.cms.client.dto.command.CmsContentCategoryUpdateCommand;
import com.particle.cms.client.api.ICmsContentCategoryApplicationService;
import com.particle.cms.client.dto.command.CmsContentCategoryCreateCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;


import com.particle.global.dto.response.SingleResponse;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.global.catchlog.CatchAndLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.particle.global.dto.response.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * 内容分类 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Transactional
@Service
@CatchAndLog
public class CmsContentCategoryApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsContentCategoryApplicationService {

    private CmsContentCategoryCreateCommandExecutor cmsContentCategoryCreateCommandExecutor;

    private CmsContentCategoryDeleteCommandExecutor cmsContentCategoryDeleteCommandExecutor;

    private CmsContentCategoryUpdateCommandExecutor cmsContentCategoryUpdateCommandExecutor;

    private CmsContentCategoryCommandExecutor cmsContentCategoryCommandExecutor;


    @Override
    public SingleResponse<CmsContentCategoryVO> create(CmsContentCategoryCreateCommand cmsContentCategoryCreateCommand) {
        return cmsContentCategoryCreateCommandExecutor.execute(cmsContentCategoryCreateCommand);
    }

    @Override
    public SingleResponse<CmsContentCategoryVO> delete(IdCommand deleteCommand) {
        return cmsContentCategoryDeleteCommandExecutor.execute(deleteCommand);
    }

    @Override
    public SingleResponse<CmsContentCategoryVO> update(CmsContentCategoryUpdateCommand cmsContentCategoryUpdateCommand) {
        return cmsContentCategoryUpdateCommandExecutor.execute(cmsContentCategoryUpdateCommand);
    }


    @Autowired
    public void setCmsContentCategoryCreateCommandExecutor(CmsContentCategoryCreateCommandExecutor cmsContentCategoryCreateCommandExecutor) {
        this.cmsContentCategoryCreateCommandExecutor = cmsContentCategoryCreateCommandExecutor;
    }

    @Autowired
    public void setCmsContentCategoryDeleteCommandExecutor(CmsContentCategoryDeleteCommandExecutor cmsContentCategoryDeleteCommandExecutor) {
        this.cmsContentCategoryDeleteCommandExecutor = cmsContentCategoryDeleteCommandExecutor;
    }
    @Autowired
    public void setCmsContentCategoryUpdateCommandExecutor(CmsContentCategoryUpdateCommandExecutor cmsContentCategoryUpdateCommandExecutor) {
        this.cmsContentCategoryUpdateCommandExecutor = cmsContentCategoryUpdateCommandExecutor;
    }
    @Autowired
    public void setCmsContentCategoryCommandExecutor(CmsContentCategoryCommandExecutor cmsContentCategoryCommandExecutor) {
        this.cmsContentCategoryCommandExecutor = cmsContentCategoryCommandExecutor;
    }
}
