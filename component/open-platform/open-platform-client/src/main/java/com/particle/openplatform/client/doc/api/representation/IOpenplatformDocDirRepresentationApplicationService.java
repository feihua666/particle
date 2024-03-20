package com.particle.openplatform.client.doc.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocDirQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocDirVO;

/**
 * <p>
 * 开放接口目录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpenplatformDocDirRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocDirVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocDirVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param openplatformDocDirQueryListCommand
	 * @return
	 */
	MultiResponse<OpenplatformDocDirVO> queryList(OpenplatformDocDirQueryListCommand openplatformDocDirQueryListCommand);

	/**
	 * 分页查询
	 * @param openplatformDocDirPageQueryCommand
	 * @return
	 */
	PageResponse<OpenplatformDocDirVO> pageQuery(OpenplatformDocDirPageQueryCommand openplatformDocDirPageQueryCommand);

}
