package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsTemplateContentQueryListCommand;
import com.particle.cms.client.dto.data.CmsTemplateContentVO;

/**
 * <p>
 * 模板内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsTemplateContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsTemplateContentVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsTemplateContentVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsTemplateContentQueryListCommand
	 * @return
	 */
	MultiResponse<CmsTemplateContentVO> queryList(CmsTemplateContentQueryListCommand cmsTemplateContentQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsTemplateContentPageQueryCommand
	 * @return
	 */
	PageResponse<CmsTemplateContentVO> pageQuery(CmsTemplateContentPageQueryCommand cmsTemplateContentPageQueryCommand);

}
