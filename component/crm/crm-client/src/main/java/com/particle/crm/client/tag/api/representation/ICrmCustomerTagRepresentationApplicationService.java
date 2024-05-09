package com.particle.crm.client.tag.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagPageQueryCommand;
import com.particle.crm.client.tag.dto.command.representation.CrmCustomerTagQueryListCommand;
import com.particle.crm.client.tag.dto.data.CrmCustomerTagVO;

/**
 * <p>
 * 客户标签 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrmCustomerTagRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrmCustomerTagVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crmCustomerTagQueryListCommand
	 * @return
	 */
	MultiResponse<CrmCustomerTagVO> queryList(CrmCustomerTagQueryListCommand crmCustomerTagQueryListCommand);

	/**
	 * 分页查询
	 * @param crmCustomerTagPageQueryCommand
	 * @return
	 */
	PageResponse<CrmCustomerTagVO> pageQuery(CrmCustomerTagPageQueryCommand crmCustomerTagPageQueryCommand);

}
