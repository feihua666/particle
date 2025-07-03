package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsTemplateContentQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsTemplateContentRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentQueryListCommand;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 模板内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Service
@CatchAndLog
public class CmsTemplateContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsTemplateContentRepresentationApplicationService {

    private CmsTemplateContentQueryCommandExecutor cmsTemplateContentQueryCommandExecutor;

    @Override
    public SingleResponse<CmsTemplateContentVO> queryDetail(IdCommand detailCommand) {
        return cmsTemplateContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsTemplateContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsTemplateContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsTemplateContentVO> pageQuery(CmsTemplateContentPageQueryCommand cmsTemplateContentPageQueryCommand) {
        return cmsTemplateContentQueryCommandExecutor.execute(cmsTemplateContentPageQueryCommand);
    }

    @Override
    public MultiResponse<CmsTemplateContentVO> queryList(CmsTemplateContentQueryListCommand cmsTemplateContentQueryListCommand) {
        return cmsTemplateContentQueryCommandExecutor.execute(cmsTemplateContentQueryListCommand);
    }


    @Autowired
    public void setCmsTemplateContentQueryCommandExecutor(CmsTemplateContentQueryCommandExecutor cmsTemplateContentQueryCommandExecutor) {
        this.cmsTemplateContentQueryCommandExecutor = cmsTemplateContentQueryCommandExecutor;
    }
}
