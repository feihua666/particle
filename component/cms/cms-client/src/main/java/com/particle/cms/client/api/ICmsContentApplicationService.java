package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.CommonAuditCommand;
import com.particle.common.client.dto.command.CommonIdCommand;
import com.particle.common.client.dto.command.CommonPublicCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.CmsContentCreateCommand;
import com.particle.cms.client.dto.command.CmsContentUpdateCommand;
import com.particle.cms.client.dto.data.CmsContentVO;
/**
 * <p>
 * 内容 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
public interface ICmsContentApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsContentCreateCommand
	 * @return
	 */
	SingleResponse<CmsContentVO> create(CmsContentCreateCommand cmsContentCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsContentVO> delete(CommonIdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsContentUpdateCommand
	 * @return
	 */
	SingleResponse<CmsContentVO> update(CmsContentUpdateCommand cmsContentUpdateCommand);
	/**
	 * 发布内容或取消发布
	 * @param cmsContentPublicCommand
	 * @return
	 */
	SingleResponse<CmsContentVO> publish(CommonPublicCommand cmsContentPublicCommand);

	/**
	 * 审核内容
	 * @param commonAuditCommand
	 * @return
	 */
	SingleResponse<CmsContentVO> audit(CommonAuditCommand commonAuditCommand);
}
