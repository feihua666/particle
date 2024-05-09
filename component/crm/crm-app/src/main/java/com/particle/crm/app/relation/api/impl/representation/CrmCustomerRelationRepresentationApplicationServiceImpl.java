package com.particle.crm.app.relation.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.app.relation.executor.representation.CrmCustomerRelationQueryCommandExecutor;
import com.particle.crm.client.relation.api.representation.ICrmCustomerRelationRepresentationApplicationService;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationPageQueryCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationQueryListCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户与客户关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Service
@CatchAndLog
public class CrmCustomerRelationRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerRelationRepresentationApplicationService {

    private CrmCustomerRelationQueryCommandExecutor crmCustomerRelationQueryCommandExecutor;

    @Override
    public SingleResponse<CrmCustomerRelationVO> queryDetail(IdCommand detailCommand) {
        return crmCustomerRelationQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrmCustomerRelationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return crmCustomerRelationQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrmCustomerRelationVO> pageQuery(CrmCustomerRelationPageQueryCommand crmCustomerRelationPageQueryCommand) {
        return crmCustomerRelationQueryCommandExecutor.execute(crmCustomerRelationPageQueryCommand);
    }

    @Override
    public MultiResponse<CrmCustomerRelationVO> queryList(CrmCustomerRelationQueryListCommand crmCustomerRelationQueryListCommand) {
        return crmCustomerRelationQueryCommandExecutor.execute(crmCustomerRelationQueryListCommand);
    }

    @Autowired
    public void setCrmCustomerRelationQueryCommandExecutor(CrmCustomerRelationQueryCommandExecutor crmCustomerRelationQueryCommandExecutor) {
        this.crmCustomerRelationQueryCommandExecutor = crmCustomerRelationQueryCommandExecutor;
    }
}
