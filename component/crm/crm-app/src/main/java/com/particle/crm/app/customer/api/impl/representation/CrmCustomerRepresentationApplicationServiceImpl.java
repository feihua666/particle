package com.particle.crm.app.customer.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.customer.executor.representation.CrmCustomerQueryCommandExecutor;
import com.particle.crm.client.customer.api.representation.ICrmCustomerRepresentationApplicationService;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerQueryListCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Service
@CatchAndLog
public class CrmCustomerRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerRepresentationApplicationService {

    private CrmCustomerQueryCommandExecutor crmCustomerQueryCommandExecutor;

    @Override
    public SingleResponse<CrmCustomerVO> queryDetail(IdCommand detailCommand) {
        return crmCustomerQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrmCustomerVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return crmCustomerQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrmCustomerVO> pageQuery(CrmCustomerPageQueryCommand crmCustomerPageQueryCommand) {
        return crmCustomerQueryCommandExecutor.execute(crmCustomerPageQueryCommand);
    }

    @Override
    public MultiResponse<CrmCustomerVO> queryList(CrmCustomerQueryListCommand crmCustomerQueryListCommand) {
        return crmCustomerQueryCommandExecutor.execute(crmCustomerQueryListCommand);
    }

    @Autowired
    public void setCrmCustomerQueryCommandExecutor(CrmCustomerQueryCommandExecutor crmCustomerQueryCommandExecutor) {
        this.crmCustomerQueryCommandExecutor = crmCustomerQueryCommandExecutor;
    }
}
