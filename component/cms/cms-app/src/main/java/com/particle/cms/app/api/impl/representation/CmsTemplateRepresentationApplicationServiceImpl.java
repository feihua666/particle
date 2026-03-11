package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsTemplateQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsTemplateRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsTemplatePageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateQueryListCommand;
import com.particle.cms.client.dto.data.CmsTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 模板 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Service
@CatchAndLog
public class CmsTemplateRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsTemplateRepresentationApplicationService {

    private CmsTemplateQueryCommandExecutor cmsTemplateQueryCommandExecutor;

    @Override
    public SingleResponse<CmsTemplateVO> queryDetail(CommonIdCommand detailCommand) {
        return cmsTemplateQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsTemplateVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand) {
        return cmsTemplateQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsTemplateVO> pageQuery(CmsTemplatePageQueryCommand cmsTemplatePageQueryCommand) {
        return cmsTemplateQueryCommandExecutor.execute(cmsTemplatePageQueryCommand);
    }

    @Override
    public MultiResponse<CmsTemplateVO> queryList(CmsTemplateQueryListCommand cmsTemplateQueryListCommand) {
        return cmsTemplateQueryCommandExecutor.execute(cmsTemplateQueryListCommand);
    }


    @Autowired
    public void setCmsTemplateQueryCommandExecutor(CmsTemplateQueryCommandExecutor cmsTemplateQueryCommandExecutor) {
        this.cmsTemplateQueryCommandExecutor = cmsTemplateQueryCommandExecutor;
    }
}
