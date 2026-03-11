package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsTemplatePageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateQueryListCommand;
import com.particle.cms.client.dto.data.CmsTemplateVO;

/**
 * <p>
 * 模板 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsTemplateRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsTemplateVO> queryDetailForUpdate(CommonIdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsTemplateVO> queryDetail(CommonIdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsTemplateQueryListCommand
	 * @return
	 */
	MultiResponse<CmsTemplateVO> queryList(CmsTemplateQueryListCommand cmsTemplateQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsTemplatePageQueryCommand
	 * @return
	 */
	PageResponse<CmsTemplateVO> pageQuery(CmsTemplatePageQueryCommand cmsTemplatePageQueryCommand);

}
