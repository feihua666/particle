package com.particle.agi.client.model.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.agi.client.model.dto.command.AgiModelProviderCreateCommand;
import com.particle.agi.client.model.dto.command.AgiModelProviderUpdateCommand;
import com.particle.agi.client.model.dto.data.AgiModelProviderVO;
/**
 * <p>
 * AI模型提供商 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
public interface IAgiModelProviderApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param agiModelProviderCreateCommand
	 * @return
	 */
	SingleResponse<AgiModelProviderVO> create(AgiModelProviderCreateCommand agiModelProviderCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<AgiModelProviderVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param agiModelProviderUpdateCommand
	 * @return
	 */
	SingleResponse<AgiModelProviderVO> update(AgiModelProviderUpdateCommand agiModelProviderUpdateCommand);
}
