package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocExampleCodeQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;

/**
 * <p>
 * 开放接口文档示例代码 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocApiDocExampleCodeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocExampleCodeVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocExampleCodeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocApiDocExampleCodeQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocApiDocExampleCodeVO> queryList(OpenplatformDocApiDocExampleCodeQueryListCommand openplatformDocApiDocExampleCodeQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocApiDocExampleCodePageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocApiDocExampleCodeVO> pageQuery(OpenplatformDocApiDocExampleCodePageQueryCommand openplatformDocApiDocExampleCodePageQueryCommand);

}
