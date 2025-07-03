package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentMultimediaQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentMultimediaVO;

/**
 * <p>
 * 内容多媒体 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsContentMultimediaRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsContentMultimediaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsContentMultimediaVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsContentMultimediaQueryListCommand
	 * @return
	 */
	MultiResponse<CmsContentMultimediaVO> queryList(CmsContentMultimediaQueryListCommand cmsContentMultimediaQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsContentMultimediaPageQueryCommand
	 * @return
	 */
	PageResponse<CmsContentMultimediaVO> pageQuery(CmsContentMultimediaPageQueryCommand cmsContentMultimediaPageQueryCommand);

}
