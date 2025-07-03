package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsChannelViewRecordQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsChannelViewRecordRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 栏目访问记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
@Service
@CatchAndLog
public class CmsChannelViewRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsChannelViewRecordRepresentationApplicationService {

    private CmsChannelViewRecordQueryCommandExecutor cmsChannelViewRecordQueryCommandExecutor;

    @Override
    public SingleResponse<CmsChannelViewRecordVO> queryDetail(IdCommand detailCommand) {
        return cmsChannelViewRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsChannelViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsChannelViewRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsChannelViewRecordVO> pageQuery(CmsChannelViewRecordPageQueryCommand cmsChannelViewRecordPageQueryCommand) {
        return cmsChannelViewRecordQueryCommandExecutor.execute(cmsChannelViewRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<CmsChannelViewRecordVO> queryList(CmsChannelViewRecordQueryListCommand cmsChannelViewRecordQueryListCommand) {
        return cmsChannelViewRecordQueryCommandExecutor.execute(cmsChannelViewRecordQueryListCommand);
    }


    @Autowired
    public void setCmsChannelViewRecordQueryCommandExecutor(CmsChannelViewRecordQueryCommandExecutor cmsChannelViewRecordQueryCommandExecutor) {
        this.cmsChannelViewRecordQueryCommandExecutor = cmsChannelViewRecordQueryCommandExecutor;
    }
}
