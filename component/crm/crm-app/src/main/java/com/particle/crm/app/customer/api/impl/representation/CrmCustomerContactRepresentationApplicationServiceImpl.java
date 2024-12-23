package com.particle.crm.app.customer.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.customer.executor.representation.CrmCustomerContactQueryCommandExecutor;
import com.particle.crm.client.customer.api.representation.ICrmCustomerContactRepresentationApplicationService;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactQueryListCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户联系方式 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Service
@CatchAndLog
public class CrmCustomerContactRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerContactRepresentationApplicationService {

    private CrmCustomerContactQueryCommandExecutor crmCustomerContactQueryCommandExecutor;

    @Override
    public SingleResponse<CrmCustomerContactVO> queryDetail(IdCommand detailCommand) {
        return crmCustomerContactQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrmCustomerContactVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return crmCustomerContactQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrmCustomerContactVO> pageQuery(CrmCustomerContactPageQueryCommand crmCustomerContactPageQueryCommand) {
        return crmCustomerContactQueryCommandExecutor.execute(crmCustomerContactPageQueryCommand);
    }

    @Override
    public MultiResponse<CrmCustomerContactVO> queryList(CrmCustomerContactQueryListCommand crmCustomerContactQueryListCommand) {
        return crmCustomerContactQueryCommandExecutor.execute(crmCustomerContactQueryListCommand);
    }

    @Autowired
    public void setCrmCustomerContactQueryCommandExecutor(CrmCustomerContactQueryCommandExecutor crmCustomerContactQueryCommandExecutor) {
        this.crmCustomerContactQueryCommandExecutor = crmCustomerContactQueryCommandExecutor;
    }
}
