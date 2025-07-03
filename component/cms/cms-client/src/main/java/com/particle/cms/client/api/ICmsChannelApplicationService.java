package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsChannelCreateCommand;
import com.particle.cms.client.dto.command.CmsChannelUpdateCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;
/**
 * <p>
 * 栏目 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
public interface ICmsChannelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsChannelCreateCommand
	 * @return
	 */
	SingleResponse<CmsChannelVO> create(CmsChannelCreateCommand cmsChannelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsChannelVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsChannelUpdateCommand
	 * @return
	 */
	SingleResponse<CmsChannelVO> update(CmsChannelUpdateCommand cmsChannelUpdateCommand);
}
