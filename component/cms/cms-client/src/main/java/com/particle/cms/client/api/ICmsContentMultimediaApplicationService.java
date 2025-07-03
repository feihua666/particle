package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsContentMultimediaCreateCommand;
import com.particle.cms.client.dto.command.CmsContentMultimediaUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;
/**
 * <p>
 * 内容多媒体 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
public interface ICmsContentMultimediaApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsContentMultimediaCreateCommand
	 * @return
	 */
	SingleResponse<CmsContentMultimediaVO> create(CmsContentMultimediaCreateCommand cmsContentMultimediaCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsContentMultimediaVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsContentMultimediaUpdateCommand
	 * @return
	 */
	SingleResponse<CmsContentMultimediaVO> update(CmsContentMultimediaUpdateCommand cmsContentMultimediaUpdateCommand);
}
