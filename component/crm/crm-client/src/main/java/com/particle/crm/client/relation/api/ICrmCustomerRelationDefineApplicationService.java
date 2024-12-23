package com.particle.crm.client.relation.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationDefineCreateCommand;
import com.particle.crm.client.relation.dto.command.CrmCustomerRelationDefineUpdateCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户关系定义 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:31:00
 */
public interface ICrmCustomerRelationDefineApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crmCustomerRelationDefineCreateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationDefineVO> create(CrmCustomerRelationDefineCreateCommand crmCustomerRelationDefineCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationDefineVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crmCustomerRelationDefineUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationDefineVO> update(CrmCustomerRelationDefineUpdateCommand crmCustomerRelationDefineUpdateCommand);

}
