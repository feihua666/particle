package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentCategoryQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentCategoryVO;

/**
 * <p>
 * 内容分类 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsContentCategoryRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsContentCategoryVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsContentCategoryVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsContentCategoryQueryListCommand
	 * @return
	 */
	MultiResponse<CmsContentCategoryVO> queryList(CmsContentCategoryQueryListCommand cmsContentCategoryQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsContentCategoryPageQueryCommand
	 * @return
	 */
	PageResponse<CmsContentCategoryVO> pageQuery(CmsContentCategoryPageQueryCommand cmsContentCategoryPageQueryCommand);

}
