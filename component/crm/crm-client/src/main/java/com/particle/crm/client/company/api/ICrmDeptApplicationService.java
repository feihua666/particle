package com.particle.crm.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.company.dto.command.CrmDeptCreateCommand;
import com.particle.crm.client.company.dto.command.CrmDeptUpdateCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户公司部门 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
public interface ICrmDeptApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crmDeptCreateCommand
	 * @return
	 */
	SingleResponse<CrmDeptVO> create(CrmDeptCreateCommand crmDeptCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrmDeptVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crmDeptUpdateCommand
	 * @return
	 */
	SingleResponse<CrmDeptVO> update(CrmDeptUpdateCommand crmDeptUpdateCommand);

}
