package com.particle.crm.app.relation.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.app.relation.executor.representation.CrmCustomerRelationDefineQueryCommandExecutor;
import com.particle.crm.client.relation.api.representation.ICrmCustomerRelationDefineRepresentationApplicationService;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefinePageQueryCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefineQueryListCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户关系定义 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
@Service
@CatchAndLog
public class CrmCustomerRelationDefineRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerRelationDefineRepresentationApplicationService {

    private CrmCustomerRelationDefineQueryCommandExecutor crmCustomerRelationDefineQueryCommandExecutor;

    @Override
    public SingleResponse<CrmCustomerRelationDefineVO> queryDetail(IdCommand detailCommand) {
        return crmCustomerRelationDefineQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrmCustomerRelationDefineVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return crmCustomerRelationDefineQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrmCustomerRelationDefineVO> pageQuery(CrmCustomerRelationDefinePageQueryCommand crmCustomerRelationDefinePageQueryCommand) {
        return crmCustomerRelationDefineQueryCommandExecutor.execute(crmCustomerRelationDefinePageQueryCommand);
    }

    @Override
    public MultiResponse<CrmCustomerRelationDefineVO> queryList(CrmCustomerRelationDefineQueryListCommand crmCustomerRelationDefineQueryListCommand) {
        return crmCustomerRelationDefineQueryCommandExecutor.execute(crmCustomerRelationDefineQueryListCommand);
    }

    @Autowired
    public void setCrmCustomerRelationDefineQueryCommandExecutor(CrmCustomerRelationDefineQueryCommandExecutor crmCustomerRelationDefineQueryCommandExecutor) {
        this.crmCustomerRelationDefineQueryCommandExecutor = crmCustomerRelationDefineQueryCommandExecutor;
    }
}
