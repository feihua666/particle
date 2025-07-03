package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsTemplateContentCreateCommand;
import com.particle.cms.client.dto.command.CmsTemplateContentUpdateCommand;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;
/**
 * <p>
 * 模板内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
public interface ICmsTemplateContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsTemplateContentCreateCommand
	 * @return
	 */
	SingleResponse<CmsTemplateContentVO> create(CmsTemplateContentCreateCommand cmsTemplateContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsTemplateContentVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsTemplateContentUpdateCommand
	 * @return
	 */
	SingleResponse<CmsTemplateContentVO> update(CmsTemplateContentUpdateCommand cmsTemplateContentUpdateCommand);
}
