package com.particle.crm.client.relation.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefinePageQueryCommand;
import com.particle.crm.client.relation.dto.command.representation.CrmCustomerRelationDefineQueryListCommand;
import com.particle.crm.client.relation.dto.data.CrmCustomerRelationDefineVO;

/**
 * <p>
 * 客户关系定义 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrmCustomerRelationDefineRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationDefineVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrmCustomerRelationDefineVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crmCustomerRelationDefineQueryListCommand
	 * @return
	 */
	MultiResponse<CrmCustomerRelationDefineVO> queryList(CrmCustomerRelationDefineQueryListCommand crmCustomerRelationDefineQueryListCommand);

	/**
	 * 分页查询
	 * @param crmCustomerRelationDefinePageQueryCommand
	 * @return
	 */
	PageResponse<CrmCustomerRelationDefineVO> pageQuery(CrmCustomerRelationDefinePageQueryCommand crmCustomerRelationDefinePageQueryCommand);

}
