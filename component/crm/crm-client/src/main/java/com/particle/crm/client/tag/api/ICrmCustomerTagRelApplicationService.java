package com.particle.crm.client.tag.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagRelCreateCommand;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagRelUpdateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;

/**
 * <p>
 * 客户标签关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
public interface ICrmCustomerTagRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crmCustomerTagRelCreateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagRelVO> create(CrmCustomerTagRelCreateCommand crmCustomerTagRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagRelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crmCustomerTagRelUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagRelVO> update(CrmCustomerTagRelUpdateCommand crmCustomerTagRelUpdateCommand);

}
