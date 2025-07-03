package com.particle.cms.app.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.app.executor.representation.CmsSiteIndexViewRecordQueryCommandExecutor;
import com.particle.cms.client.api.representation.ICmsSiteIndexViewRecordRepresentationApplicationService;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 站点首页访问记录 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
@Service
@CatchAndLog
public class CmsSiteIndexViewRecordRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICmsSiteIndexViewRecordRepresentationApplicationService {

    private CmsSiteIndexViewRecordQueryCommandExecutor cmsSiteIndexViewRecordQueryCommandExecutor;

    @Override
    public SingleResponse<CmsSiteIndexViewRecordVO> queryDetail(IdCommand detailCommand) {
        return cmsSiteIndexViewRecordQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CmsSiteIndexViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return cmsSiteIndexViewRecordQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CmsSiteIndexViewRecordVO> pageQuery(CmsSiteIndexViewRecordPageQueryCommand cmsSiteIndexViewRecordPageQueryCommand) {
        return cmsSiteIndexViewRecordQueryCommandExecutor.execute(cmsSiteIndexViewRecordPageQueryCommand);
    }

    @Override
    public MultiResponse<CmsSiteIndexViewRecordVO> queryList(CmsSiteIndexViewRecordQueryListCommand cmsSiteIndexViewRecordQueryListCommand) {
        return cmsSiteIndexViewRecordQueryCommandExecutor.execute(cmsSiteIndexViewRecordQueryListCommand);
    }


    @Autowired
    public void setCmsSiteIndexViewRecordQueryCommandExecutor(CmsSiteIndexViewRecordQueryCommandExecutor cmsSiteIndexViewRecordQueryCommandExecutor) {
        this.cmsSiteIndexViewRecordQueryCommandExecutor = cmsSiteIndexViewRecordQueryCommandExecutor;
    }
}
