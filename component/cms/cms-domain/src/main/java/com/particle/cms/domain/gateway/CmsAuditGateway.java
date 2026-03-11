package com.particle.cms.domain.gateway;

import com.particle.cms.domain.CmsContentId;
import com.particle.common.domain.gateway.IGateway;

/**
 * <p>
 * cms  依赖的审核
 * </p>
 *
 * @author yangwei
 * @since 2026-01-19 16:17:39
 */
public interface CmsAuditGateway extends IGateway {

	/**
	 * 创建内容审核记录
	 *
	 * @param cmsContentId
	 * @param auditResultDictId
	 * @param userId 操作用户 id
	 * @param preStatusDictId 数据审核之前状态，字典id
	 * @param postStatusDictId 是否通过审核
	 * @param comment 审核意见
	 * @return
	 */
	Boolean createCmsContentAuditRecord(CmsContentId cmsContentId,
										Long auditResultDictId,
										Long userId,
										Long preStatusDictId,
										Long postStatusDictId,
										String comment );

	/**
	 * 获取通过审核的字典id
	 * @return
	 */
	Long getPassAuditResultDictId();
	/**
	 * 获取未通过审核的字典id
	 * @return
	 */
	Long getUnPassAuditResultDictId();
}
