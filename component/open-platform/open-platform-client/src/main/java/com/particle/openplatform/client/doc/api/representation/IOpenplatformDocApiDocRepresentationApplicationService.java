package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocVO;

/**
 * <p>
 * 开放接口文档 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocApiDocRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocApiDocQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocApiDocVO> queryList(OpenplatformDocApiDocQueryListCommand openplatformDocApiDocQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocApiDocPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocApiDocVO> pageQuery(OpenplatformDocApiDocPageQueryCommand openplatformDocApiDocPageQueryCommand);

}
