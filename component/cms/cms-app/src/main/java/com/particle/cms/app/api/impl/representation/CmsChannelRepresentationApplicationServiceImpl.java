package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsChannelQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsChannelRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsChannelPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelQueryListCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 栏目 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Service
@CatchAndLog
public class CmsChannelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsChannelRepresentationApplicationService {

    private CmsChannelQueryCommandExecutor cmsChannelQueryCommandExecutor;

    @Override
    public SingleResponse<CmsChannelVO> queryDetail(IdCommand detailCommand) {
        return cmsChannelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsChannelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsChannelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsChannelVO> pageQuery(CmsChannelPageQueryCommand cmsChannelPageQueryCommand) {
        return cmsChannelQueryCommandExecutor.execute(cmsChannelPageQueryCommand);
    }

    @Override
    public MultiResponse<CmsChannelVO> queryList(CmsChannelQueryListCommand cmsChannelQueryListCommand) {
        return cmsChannelQueryCommandExecutor.execute(cmsChannelQueryListCommand);
    }


    @Autowired
    public void setCmsChannelQueryCommandExecutor(CmsChannelQueryCommandExecutor cmsChannelQueryCommandExecutor) {
        this.cmsChannelQueryCommandExecutor = cmsChannelQueryCommandExecutor;
    }
}
