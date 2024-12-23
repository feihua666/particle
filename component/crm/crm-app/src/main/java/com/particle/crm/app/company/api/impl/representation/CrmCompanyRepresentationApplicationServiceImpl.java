package com.particle.crm.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.company.executor.representation.CrmCompanyQueryCommandExecutor;
import com.particle.crm.client.company.api.representation.ICrmCompanyRepresentationApplicationService;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyQueryListCommand;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户公司 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Service
@CatchAndLog
public class CrmCompanyRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCompanyRepresentationApplicationService {

    private CrmCompanyQueryCommandExecutor crmCompanyQueryCommandExecutor;

    @Override
    public SingleResponse<CrmCompanyVO> queryDetail(IdCommand detailCommand) {
        return crmCompanyQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrmCompanyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return crmCompanyQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrmCompanyVO> pageQuery(CrmCompanyPageQueryCommand crmCompanyPageQueryCommand) {
        return crmCompanyQueryCommandExecutor.execute(crmCompanyPageQueryCommand);
    }

    @Override
    public MultiResponse<CrmCompanyVO> queryList(CrmCompanyQueryListCommand crmCompanyQueryListCommand) {
        return crmCompanyQueryCommandExecutor.execute(crmCompanyQueryListCommand);
    }

    @Autowired
    public void setCrmCompanyQueryCommandExecutor(CrmCompanyQueryCommandExecutor crmCompanyQueryCommandExecutor) {
        this.crmCompanyQueryCommandExecutor = crmCompanyQueryCommandExecutor;
    }
}
