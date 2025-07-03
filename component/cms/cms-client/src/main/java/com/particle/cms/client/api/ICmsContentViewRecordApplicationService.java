package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsContentViewRecordCreateCommand;
import com.particle.cms.client.dto.command.CmsContentViewRecordUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentViewRecordVO;
/**
 * <p>
 * 内容访问记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:38
 */
public interface ICmsContentViewRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsContentViewRecordCreateCommand
	 * @return
	 */
	SingleResponse<CmsContentViewRecordVO> create(CmsContentViewRecordCreateCommand cmsContentViewRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsContentViewRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsContentViewRecordUpdateCommand
	 * @return
	 */
	SingleResponse<CmsContentViewRecordVO> update(CmsContentViewRecordUpdateCommand cmsContentViewRecordUpdateCommand);
}
