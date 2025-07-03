package com.particle.cms.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.dto.response.Response;
import com.particle.cms.client.dto.command.CmsSiteIndexViewRecordCreateCommand;
import com.particle.cms.client.dto.command.CmsSiteIndexViewRecordUpdateCommand;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;
/**
 * <p>
 * 站点首页访问记录 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:15:10
 */
public interface ICmsSiteIndexViewRecordApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param cmsSiteIndexViewRecordCreateCommand
	 * @return
	 */
	SingleResponse<CmsSiteIndexViewRecordVO> create(CmsSiteIndexViewRecordCreateCommand cmsSiteIndexViewRecordCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<CmsSiteIndexViewRecordVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param cmsSiteIndexViewRecordUpdateCommand
	 * @return
	 */
	SingleResponse<CmsSiteIndexViewRecordVO> update(CmsSiteIndexViewRecordUpdateCommand cmsSiteIndexViewRecordUpdateCommand);
}
