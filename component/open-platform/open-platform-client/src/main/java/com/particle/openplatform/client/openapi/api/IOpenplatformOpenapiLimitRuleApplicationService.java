package com.particle.openplatform.client.openapi.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiLimitRuleCreateCommand;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiLimitRuleUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
/**
 * <p>
 * 开放平台开放接口限制规则 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
public interface IOpenplatformOpenapiLimitRuleApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformOpenapiLimitRuleCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiLimitRuleVO> create(OpenplatformOpenapiLimitRuleCreateCommand openplatformOpenapiLimitRuleCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiLimitRuleVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformOpenapiLimitRuleUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformOpenapiLimitRuleVO> update(OpenplatformOpenapiLimitRuleUpdateCommand openplatformOpenapiLimitRuleUpdateCommand);
}
