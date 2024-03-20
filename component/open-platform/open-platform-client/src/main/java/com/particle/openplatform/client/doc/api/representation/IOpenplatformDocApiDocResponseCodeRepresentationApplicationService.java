package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocResponseCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocResponseCodeVO;

/**
 * <p>
 * 开放接口文档响应码 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocApiDocResponseCodeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocResponseCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocResponseCodeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocApiDocResponseCodeQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocApiDocResponseCodeVO> queryList(OpenplatformDocApiDocResponseCodeQueryListCommand openplatformDocApiDocResponseCodeQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocApiDocResponseCodePageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocApiDocResponseCodeVO> pageQuery(OpenplatformDocApiDocResponseCodePageQueryCommand openplatformDocApiDocResponseCodePageQueryCommand);

}
