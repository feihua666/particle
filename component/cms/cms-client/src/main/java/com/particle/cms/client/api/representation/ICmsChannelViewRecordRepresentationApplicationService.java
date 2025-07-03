package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsChannelViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;

/**
 * <p>
 * 栏目访问记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsChannelViewRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsChannelViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsChannelViewRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsChannelViewRecordQueryListCommand
	 * @return
	 */
	MultiResponse<CmsChannelViewRecordVO> queryList(CmsChannelViewRecordQueryListCommand cmsChannelViewRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsChannelViewRecordPageQueryCommand
	 * @return
	 */
	PageResponse<CmsChannelViewRecordVO> pageQuery(CmsChannelViewRecordPageQueryCommand cmsChannelViewRecordPageQueryCommand);

}
