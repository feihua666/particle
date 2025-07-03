package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsSiteCreateCommand;
import com.particle.cms.client.dto.command.CmsSiteUpdateCommand;
import com.particle.cms.client.dto.data.CmsSiteVO;
/**
 * <p>
 * 站点 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
public interface ICmsSiteApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsSiteCreateCommand
	 * @return
	 */
	SingleResponse<CmsSiteVO> create(CmsSiteCreateCommand cmsSiteCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsSiteVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsSiteUpdateCommand
	 * @return
	 */
	SingleResponse<CmsSiteVO> update(CmsSiteUpdateCommand cmsSiteUpdateCommand);
}
