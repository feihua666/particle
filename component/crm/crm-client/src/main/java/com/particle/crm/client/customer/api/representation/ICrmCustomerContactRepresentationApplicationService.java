package com.particle.crm.client.customer.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactPageQueryCommand;
import com.particle.crm.client.customer.dto.command.representation.CrmCustomerContactQueryListCommand;
import com.particle.crm.client.customer.dto.data.CrmCustomerContactVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户联系方式 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrmCustomerContactRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerContactVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrmCustomerContactVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crmCustomerContactQueryListCommand
	 * @return
	 */
	MultiResponse<CrmCustomerContactVO> queryList(CrmCustomerContactQueryListCommand crmCustomerContactQueryListCommand);

	/**
	 * 分页查询
	 * @param crmCustomerContactPageQueryCommand
	 * @return
	 */
	PageResponse<CrmCustomerContactVO> pageQuery(CrmCustomerContactPageQueryCommand crmCustomerContactPageQueryCommand);

}
