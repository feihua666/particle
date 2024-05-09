package com.particle.crm.client.customer.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerQueryListCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerVO;

/**
 * <p>
 * 客户 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrmCustomerRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrmCustomerVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crmCustomerQueryListCommand
	 * @return
	 */
	MultiResponse<CrmCustomerVO> queryList(CrmCustomerQueryListCommand crmCustomerQueryListCommand);

	/**
	 * 分页查询
	 * @param crmCustomerPageQueryCommand
	 * @return
	 */
	PageResponse<CrmCustomerVO> pageQuery(CrmCustomerPageQueryCommand crmCustomerPageQueryCommand);

}
