package com.particle.crm.client.company.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.company.dto.command.CrmCompanyCreateCommand;
import com.particle.crm.client.company.dto.command.CrmCompanyUpdateCommand;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户公司 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
public interface ICrmCompanyApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param crmCompanyCreateCommand
	 * @return
	 */
	SingleResponse<CrmCompanyVO> create(CrmCompanyCreateCommand crmCompanyCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CrmCompanyVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param crmCompanyUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCompanyVO> update(CrmCompanyUpdateCommand crmCompanyUpdateCommand);

}
