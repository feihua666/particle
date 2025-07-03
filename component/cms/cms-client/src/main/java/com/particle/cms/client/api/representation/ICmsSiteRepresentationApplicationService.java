package com.particle.cms.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.cms.client.dto.command.representation.CmsSitePageQueryCommand;
import com.particle.cms.client.dto.command.representation.CmsSiteQueryListCommand;
import com.particle.cms.client.dto.data.CmsSiteVO;

/**
 * <p>
 * 站点 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ICmsSiteRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<CmsSiteVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<CmsSiteVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param cmsSiteQueryListCommand
	 * @return
	 */
	MultiResponse<CmsSiteVO> queryList(CmsSiteQueryListCommand cmsSiteQueryListCommand);

	/**
	 * 分页查询
	 * @param cmsSitePageQueryCommand
	 * @return
	 */
	PageResponse<CmsSiteVO> pageQuery(CmsSitePageQueryCommand cmsSitePageQueryCommand);

}
