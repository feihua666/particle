package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsContentCategoryCreateCommand;
import com.particle.cms.client.dto.command.CmsContentCategoryUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;
/**
 * <p>
 * 内容分类 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
public interface ICmsContentCategoryApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsContentCategoryCreateCommand
	 * @return
	 */
	SingleResponse<CmsContentCategoryVO> create(CmsContentCategoryCreateCommand cmsContentCategoryCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsContentCategoryVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsContentCategoryUpdateCommand
	 * @return
	 */
	SingleResponse<CmsContentCategoryVO> update(CmsContentCategoryUpdateCommand cmsContentCategoryUpdateCommand);
}
