package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsTemplateCreateCommand;
import com.particle.cms.client.dto.command.CmsTemplateUpdateCommand;
import com.particle.cms.client.dto.data.CmsTemplateVO;
/**
 * <p>
 * 模板 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
public interface ICmsTemplateApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsTemplateCreateCommand
	 * @return
	 */
	SingleResponse<CmsTemplateVO> create(CmsTemplateCreateCommand cmsTemplateCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsTemplateVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsTemplateUpdateCommand
	 * @return
	 */
	SingleResponse<CmsTemplateVO> update(CmsTemplateUpdateCommand cmsTemplateUpdateCommand);
}
