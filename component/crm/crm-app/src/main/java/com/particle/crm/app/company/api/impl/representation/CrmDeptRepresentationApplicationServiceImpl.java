package com.particle.crm.app.company.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.app.company.executor.representation.CrmDeptQueryCommandExecutor;
import com.particle.crm.client.company.api.representation.ICrmDeptRepresentationApplicationService;
import com.particle.crm.client.company.dto.command.representation.CrmDeptPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmDeptQueryListCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户公司部门 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Service
@CatchAndLog
public class CrmDeptRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmDeptRepresentationApplicationService {

    private CrmDeptQueryCommandExecutor crmDeptQueryCommandExecutor;

    @Override
    public SingleResponse<CrmDeptVO> queryDetail(IdCommand detailCommand) {
        return crmDeptQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrmDeptVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return crmDeptQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrmDeptVO> pageQuery(CrmDeptPageQueryCommand crmDeptPageQueryCommand) {
        return crmDeptQueryCommandExecutor.execute(crmDeptPageQueryCommand);
    }

    @Override
    public MultiResponse<CrmDeptVO> queryList(CrmDeptQueryListCommand crmDeptQueryListCommand) {
        return crmDeptQueryCommandExecutor.execute(crmDeptQueryListCommand);
    }

    @Autowired
    public void setCrmDeptQueryCommandExecutor(CrmDeptQueryCommandExecutor crmDeptQueryCommandExecutor) {
        this.crmDeptQueryCommandExecutor = crmDeptQueryCommandExecutor;
    }
}
