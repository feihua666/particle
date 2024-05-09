package com.particle.crm.client.company.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.crm.client.company.dto.command.representation.CrmDeptPageQueryCommand;
import com.particle.crm.client.company.dto.command.representation.CrmDeptQueryListCommand;
import com.particle.crm.client.company.dto.data.CrmDeptVO;

/**
 * <p>
 * 客户公司部门 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICrmDeptRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CrmDeptVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CrmDeptVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param crmDeptQueryListCommand
	 * @return
	 */
	MultiResponse<CrmDeptVO> queryList(CrmDeptQueryListCommand crmDeptQueryListCommand);

	/**
	 * 分页查询
	 * @param crmDeptPageQueryCommand
	 * @return
	 */
	PageResponse<CrmDeptVO> pageQuery(CrmDeptPageQueryCommand crmDeptPageQueryCommand);

}
