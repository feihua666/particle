package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsContentCategoryQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsContentCategoryRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 内容分类 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Service
@CatchAndLog
public class CmsContentCategoryRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsContentCategoryRepresentationApplicationService {

    private CmsContentCategoryQueryCommandExecutor cmsContentCategoryQueryCommandExecutor;

    @Override
    public SingleResponse<CmsContentCategoryVO> queryDetail(IdCommand detailCommand) {
        return cmsContentCategoryQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsContentCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsContentCategoryQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsContentCategoryVO> pageQuery(CmsContentCategoryPageQueryCommand cmsContentCategoryPageQueryCommand) {
        return cmsContentCategoryQueryCommandExecutor.execute(cmsContentCategoryPageQueryCommand);
    }

    @Override
    public MultiResponse<CmsContentCategoryVO> queryList(CmsContentCategoryQueryListCommand cmsContentCategoryQueryListCommand) {
        return cmsContentCategoryQueryCommandExecutor.execute(cmsContentCategoryQueryListCommand);
    }


    @Autowired
    public void setCmsContentCategoryQueryCommandExecutor(CmsContentCategoryQueryCommandExecutor cmsContentCategoryQueryCommandExecutor) {
        this.cmsContentCategoryQueryCommandExecutor = cmsContentCategoryQueryCommandExecutor;
    }
}
