package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsChannelPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelQueryListCommand;
import com.particle.cms.client.dto.data.CmsChannelVO;

/**
 * <p>
 * 栏目 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsChannelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsChannelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsChannelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsChannelQueryListCommand
	 * @return
	 */
	MultiResponse<CmsChannelVO> queryList(CmsChannelQueryListCommand cmsChannelQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsChannelPageQueryCommand
	 * @return
	 */
	PageResponse<CmsChannelVO> pageQuery(CmsChannelPageQueryCommand cmsChannelPageQueryCommand);

}
