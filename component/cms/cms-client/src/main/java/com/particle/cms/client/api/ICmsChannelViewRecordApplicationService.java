package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsChannelViewRecordCreateCommand;
import com.particle.cms.client.dto.command.CmsChannelViewRecordUpdateCommand;
import com.particle.cms.client.dto.data.CmsChannelViewRecordVO;
/**
 * <p>
 * 栏目访问记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:22
 */
public interface ICmsChannelViewRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsChannelViewRecordCreateCommand
	 * @return
	 */
	SingleResponse<CmsChannelViewRecordVO> create(CmsChannelViewRecordCreateCommand cmsChannelViewRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsChannelViewRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsChannelViewRecordUpdateCommand
	 * @return
	 */
	SingleResponse<CmsChannelViewRecordVO> update(CmsChannelViewRecordUpdateCommand cmsChannelViewRecordUpdateCommand);
}
