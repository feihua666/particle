package com.particle.crm.client.relation.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationCreateCommand;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationUpdateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;

/**
 * <p>
 * 客户与客户关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
public interface ICrmCustomerRelationApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crmCustomerRelationCreateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationVO> create(CrmCustomerRelationCreateCommand crmCustomerRelationCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crmCustomerRelationUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationVO> update(CrmCustomerRelationUpdateCommand crmCustomerRelationUpdateCommand);

}
