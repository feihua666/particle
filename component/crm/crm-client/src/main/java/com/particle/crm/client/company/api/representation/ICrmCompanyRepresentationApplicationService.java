package com.particle.crm.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmCompanyQueryListCommand;
import com.particle.crm.client.company.dto.data.CrmCompanyVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户公司 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrmCompanyRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCompanyVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrmCompanyVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crmCompanyQueryListCommand
	 * @return
	 */
	MultiResponse<CrmCompanyVO> queryList(CrmCompanyQueryListCommand crmCompanyQueryListCommand);

	/**
	 * 分页查询
	 * @param crmCompanyPageQueryCommand
	 * @return
	 */
	PageResponse<CrmCompanyVO> pageQuery(CrmCompanyPageQueryCommand crmCompanyPageQueryCommand);

}
