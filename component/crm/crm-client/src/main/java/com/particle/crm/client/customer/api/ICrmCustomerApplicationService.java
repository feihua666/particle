package com.particle.crm.client.customer.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.customer.dto.command.CrmCustomerCreateCommand;
import com.particle.crm.client.customer.dto.command.CrmCustomerUpdateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
public interface ICrmCustomerApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crmCustomerCreateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerVO> create(CrmCustomerCreateCommand crmCustomerCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrmCustomerVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crmCustomerUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerVO> update(CrmCustomerUpdateCommand crmCustomerUpdateCommand);

}
