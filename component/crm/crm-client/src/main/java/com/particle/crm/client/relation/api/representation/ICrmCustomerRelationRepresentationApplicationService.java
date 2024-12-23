package com.particle.crm.client.relation.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationPageQueryCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationQueryListCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 客户与客户关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrmCustomerRelationRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crmCustomerRelationQueryListCommand
	 * @return
	 */
	MultiResponse<CrmCustomerRelationVO> queryList(CrmCustomerRelationQueryListCommand crmCustomerRelationQueryListCommand);

	/**
	 * 分页查询
	 * @param crmCustomerRelationPageQueryCommand
	 * @return
	 */
	PageResponse<CrmCustomerRelationVO> pageQuery(CrmCustomerRelationPageQueryCommand crmCustomerRelationPageQueryCommand);

}
