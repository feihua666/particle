package com.particle.crm.app.tag.api.impl.representation;

import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.app.tag.executor.representation.CrmCustomerTagQueryCommandExecutor;
import com.particle.crm.client.tag.api.representation.ICrmCustomerTagRepresentationApplicationService;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagPageQueryCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 * 客户标签 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Service
@CatchAndLog
public class CrmCustomerTagRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements ICrmCustomerTagRepresentationApplicationService {

    private CrmCustomerTagQueryCommandExecutor crmCustomerTagQueryCommandExecutor;

    @Override
    public SingleResponse<CrmCustomerTagVO> queryDetail(IdCommand detailCommand) {
        return crmCustomerTagQueryCommandExecutor.executeDetail(detailCommand);
    }

    @Override
    public SingleResponse<CrmCustomerTagVO> queryDetailForUpdate(IdCommand detailForUpdateCommand) {
        return crmCustomerTagQueryCommandExecutor.executeDetailForUpdate(detailForUpdateCommand);
    }

    @Override
    public PageResponse<CrmCustomerTagVO> pageQuery(CrmCustomerTagPageQueryCommand crmCustomerTagPageQueryCommand) {
        return crmCustomerTagQueryCommandExecutor.execute(crmCustomerTagPageQueryCommand);
    }

    @Override
    public MultiResponse<CrmCustomerTagVO> queryList(CrmCustomerTagQueryListCommand crmCustomerTagQueryListCommand) {
        return crmCustomerTagQueryCommandExecutor.execute(crmCustomerTagQueryListCommand);
    }

    @Autowired
    public void setCrmCustomerTagQueryCommandExecutor(CrmCustomerTagQueryCommandExecutor crmCustomerTagQueryCommandExecutor) {
        this.crmCustomerTagQueryCommandExecutor = crmCustomerTagQueryCommandExecutor;
    }
}
