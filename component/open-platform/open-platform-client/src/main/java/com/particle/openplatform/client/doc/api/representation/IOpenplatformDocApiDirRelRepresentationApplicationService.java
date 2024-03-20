package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDirRelQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDirRelVO;

/**
 * <p>
 * 开放接口文档接口与目录关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocApiDirRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDirRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDirRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocApiDirRelQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocApiDirRelVO> queryList(OpenplatformDocApiDirRelQueryListCommand openplatformDocApiDirRelQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocApiDirRelPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocApiDirRelVO> pageQuery(OpenplatformDocApiDirRelPageQueryCommand openplatformDocApiDirRelPageQueryCommand);

}
