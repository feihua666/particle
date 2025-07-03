package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsContentQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsContentRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsContentPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 内容 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Service
@CatchAndLog
public class CmsContentRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsContentRepresentationApplicationService {

    private CmsContentQueryCommandExecutor cmsContentQueryCommandExecutor;

    @Override
    public SingleResponse<CmsContentVO> queryDetail(IdCommand detailCommand) {
        return cmsContentQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsContentQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsContentVO> pageQuery(CmsContentPageQueryCommand cmsContentPageQueryCommand) {
        return cmsContentQueryCommandExecutor.execute(cmsContentPageQueryCommand);
    }

    @Override
    public MultiResponse<CmsContentVO> queryList(CmsContentQueryListCommand cmsContentQueryListCommand) {
        return cmsContentQueryCommandExecutor.execute(cmsContentQueryListCommand);
    }


    @Autowired
    public void setCmsContentQueryCommandExecutor(CmsContentQueryCommandExecutor cmsContentQueryCommandExecutor) {
        this.cmsContentQueryCommandExecutor = cmsContentQueryCommandExecutor;
    }
}
