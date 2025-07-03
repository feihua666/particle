package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsContentMultimediaQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsContentMultimediaRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 内容多媒体 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Service
@CatchAndLog
public class CmsContentMultimediaRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsContentMultimediaRepresentationApplicationService {

    private CmsContentMultimediaQueryCommandExecutor cmsContentMultimediaQueryCommandExecutor;

    @Override
    public SingleResponse<CmsContentMultimediaVO> queryDetail(IdCommand detailCommand) {
        return cmsContentMultimediaQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsContentMultimediaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsContentMultimediaQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsContentMultimediaVO> pageQuery(CmsContentMultimediaPageQueryCommand cmsContentMultimediaPageQueryCommand) {
        return cmsContentMultimediaQueryCommandExecutor.execute(cmsContentMultimediaPageQueryCommand);
    }

    @Override
    public MultiResponse<CmsContentMultimediaVO> queryList(CmsContentMultimediaQueryListCommand cmsContentMultimediaQueryListCommand) {
        return cmsContentMultimediaQueryCommandExecutor.execute(cmsContentMultimediaQueryListCommand);
    }


    @Autowired
    public void setCmsContentMultimediaQueryCommandExecutor(CmsContentMultimediaQueryCommandExecutor cmsContentMultimediaQueryCommandExecutor) {
        this.cmsContentMultimediaQueryCommandExecutor = cmsContentMultimediaQueryCommandExecutor;
    }
}
