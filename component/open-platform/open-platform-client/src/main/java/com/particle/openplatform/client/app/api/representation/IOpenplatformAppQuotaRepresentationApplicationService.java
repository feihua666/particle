package com.particle.openplatform.client.app.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;

/**
 * <p>
 * 开放平台应用额度 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformAppQuotaRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppQuotaVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformAppQuotaVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformAppQuotaQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformAppQuotaVO> queryList(OpenplatformAppQuotaQueryListCommand openplatformAppQuotaQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformAppQuotaPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformAppQuotaVO> pageQuery(OpenplatformAppQuotaPageQueryCommand openplatformAppQuotaPageQueryCommand);

}
