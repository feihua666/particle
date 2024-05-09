package com.particle.crm.app.tag.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.app.tag.executor.representation.CrmCustomerTagRelQueryCommandExecutor;
import com.particle.crm.client.tag.api.representation.ICrmCustomerTagRelRepresentationApplicationService;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelPageQueryCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户标签关系 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Service
@CatchAndLog
public class CrmCustomerTagRelRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerTagRelRepresentationApplicationService {

    private CrmCustomerTagRelQueryCommandExecutor crmCustomerTagRelQueryCommandExecutor;

    @Override
    public SingleResponse<CrmCustomerTagRelVO> queryDetail(IdCommand detailCommand) {
        return crmCustomerTagRelQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrmCustomerTagRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return crmCustomerTagRelQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrmCustomerTagRelVO> pageQuery(CrmCustomerTagRelPageQueryCommand crmCustomerTagRelPageQueryCommand) {
        return crmCustomerTagRelQueryCommandExecutor.execute(crmCustomerTagRelPageQueryCommand);
    }

    @Override
    public MultiResponse<CrmCustomerTagRelVO> queryList(CrmCustomerTagRelQueryListCommand crmCustomerTagRelQueryListCommand) {
        return crmCustomerTagRelQueryCommandExecutor.execute(crmCustomerTagRelQueryListCommand);
    }

    @Autowired
    public void setCrmCustomerTagRelQueryCommandExecutor(CrmCustomerTagRelQueryCommandExecutor crmCustomerTagRelQueryCommandExecutor) {
        this.crmCustomerTagRelQueryCommandExecutor = crmCustomerTagRelQueryCommandExecutor;
    }
}
