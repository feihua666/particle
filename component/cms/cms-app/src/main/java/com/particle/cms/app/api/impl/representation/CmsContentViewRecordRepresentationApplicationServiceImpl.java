package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsContentViewRecordQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsContentViewRecordRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 内容访问记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
@Service
@CatchAndLog
public class CmsContentViewRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsContentViewRecordRepresentationApplicationService {

    private CmsContentViewRecordQueryCommandExecutor cmsContentViewRecordQueryCommandExecutor;

    @Override
    public SingleResponse<CmsContentViewRecordVO> queryDetail(IdCommand detailCommand) {
        return cmsContentViewRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsContentViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsContentViewRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsContentViewRecordVO> pageQuery(CmsContentViewRecordPageQueryCommand cmsContentViewRecordPageQueryCommand) {
        return cmsContentViewRecordQueryCommandExecutor.execute(cmsContentViewRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<CmsContentViewRecordVO> queryList(CmsContentViewRecordQueryListCommand cmsContentViewRecordQueryListCommand) {
        return cmsContentViewRecordQueryCommandExecutor.execute(cmsContentViewRecordQueryListCommand);
    }


    @Autowired
    public void setCmsContentViewRecordQueryCommandExecutor(CmsContentViewRecordQueryCommandExecutor cmsContentViewRecordQueryCommandExecutor) {
        this.cmsContentViewRecordQueryCommandExecutor = cmsContentViewRecordQueryCommandExecutor;
    }
}
