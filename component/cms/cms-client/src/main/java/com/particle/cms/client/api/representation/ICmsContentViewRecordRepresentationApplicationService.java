package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsContentViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;

/**
 * <p>
 * 内容访问记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsContentViewRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsContentViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsContentViewRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsContentViewRecordQueryListCommand
	 * @return
	 */
	MultiResponse<CmsContentViewRecordVO> queryList(CmsContentViewRecordQueryListCommand cmsContentViewRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsContentViewRecordPageQueryCommand
	 * @return
	 */
	PageResponse<CmsContentViewRecordVO> pageQuery(CmsContentViewRecordPageQueryCommand cmsContentViewRecordPageQueryCommand);

}
