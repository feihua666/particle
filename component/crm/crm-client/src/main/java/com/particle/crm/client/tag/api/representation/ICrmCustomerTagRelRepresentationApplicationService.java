package com.particle.crm.client.tag.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelPageQueryCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagRelQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagRelVO;

/**
 * <p>
 * 客户标签关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrmCustomerTagRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crmCustomerTagRelQueryListCommand
	 * @return
	 */
	MultiResponse<CrmCustomerTagRelVO> queryList(CrmCustomerTagRelQueryListCommand crmCustomerTagRelQueryListCommand);

	/**
	 * 分页查询
	 * @param crmCustomerTagRelPageQueryCommand
	 * @return
	 */
	PageResponse<CrmCustomerTagRelVO> pageQuery(CrmCustomerTagRelPageQueryCommand crmCustomerTagRelPageQueryCommand);

}
