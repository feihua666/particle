package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordPageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteIndexViewRecordQueryListCommand;
import com.particle.cms.client.dto.data.CmsSiteIndexViewRecordVO;

/**
 * <p>
 * 站点首页访问记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsSiteIndexViewRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsSiteIndexViewRecordVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsSiteIndexViewRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsSiteIndexViewRecordQueryListCommand
	 * @return
	 */
	MultiResponse<CmsSiteIndexViewRecordVO> queryList(CmsSiteIndexViewRecordQueryListCommand cmsSiteIndexViewRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsSiteIndexViewRecordPageQueryCommand
	 * @return
	 */
	PageResponse<CmsSiteIndexViewRecordVO> pageQuery(CmsSiteIndexViewRecordPageQueryCommand cmsSiteIndexViewRecordPageQueryCommand);

}
