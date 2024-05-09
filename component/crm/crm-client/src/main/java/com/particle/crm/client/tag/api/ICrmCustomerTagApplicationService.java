package com.particle.crm.client.tag.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagCreateCommand;
import com.particle.crm.client.tag.dto.command.CrmCustomerTagUpdateCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;

/**
 * <p>
 * 客户标签 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
public interface ICrmCustomerTagApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crmCustomerTagCreateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagVO> create(CrmCustomerTagCreateCommand crmCustomerTagCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crmCustomerTagUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagVO> update(CrmCustomerTagUpdateCommand crmCustomerTagUpdateCommand);

}
