package com.particle.crm.client.customer.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.customer.dto.command.CrmCustomerContactCreateCommand;
import com.particle.crm.client.customer.dto.command.CrmCustomerContactUpdateCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户联系方式 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
public interface ICrmCustomerContactApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crmCustomerContactCreateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerContactVO> create(CrmCustomerContactCreateCommand crmCustomerContactCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrmCustomerContactVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crmCustomerContactUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerContactVO> update(CrmCustomerContactUpdateCommand crmCustomerContactUpdateCommand);

}
