package com.particle.openplatform.client.openapi.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRulePageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiLimitRuleQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;

/**
 * <p>
 * 开放平台开放接口限制规则 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformOpenapiLimitRuleRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiLimitRuleVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiLimitRuleVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformOpenapiLimitRuleQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformOpenapiLimitRuleVO> queryList(OpenplatformOpenapiLimitRuleQueryListCommand openplatformOpenapiLimitRuleQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformOpenapiLimitRulePageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformOpenapiLimitRuleVO> pageQuery(OpenplatformOpenapiLimitRulePageQueryCommand openplatformOpenapiLimitRulePageQueryCommand);

}
