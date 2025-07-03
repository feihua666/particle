package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsSiteQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsSiteRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsSitePageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteQueryListCommand;
import com.particle.cms.client.dto.data.CmsSiteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 站点 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Service
@CatchAndLog
public class CmsSiteRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsSiteRepresentationApplicationService {

    private CmsSiteQueryCommandExecutor cmsSiteQueryCommandExecutor;

    @Override
    public SingleResponse<CmsSiteVO> queryDetail(IdCommand detailCommand) {
        return cmsSiteQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsSiteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsSiteQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsSiteVO> pageQuery(CmsSitePageQueryCommand cmsSitePageQueryCommand) {
        return cmsSiteQueryCommandExecutor.execute(cmsSitePageQueryCommand);
    }

    @Override
    public MultiResponse<CmsSiteVO> queryList(CmsSiteQueryListCommand cmsSiteQueryListCommand) {
        return cmsSiteQueryCommandExecutor.execute(cmsSiteQueryListCommand);
    }


    @Autowired
    public void setCmsSiteQueryCommandExecutor(CmsSiteQueryCommandExecutor cmsSiteQueryCommandExecutor) {
        this.cmsSiteQueryCommandExecutor = cmsSiteQueryCommandExecutor;
    }
}
