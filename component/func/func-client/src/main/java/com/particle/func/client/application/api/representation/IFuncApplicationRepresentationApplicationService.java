package com.particle.func.client.application.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.func.client.application.dto.command.representation.FuncApplicationPageQueryCommand;
import com.particle.func.client.application.dto.command.representation.FuncApplicationQueryListCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;

/**
 * <p>
 * 功能应用 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IFuncApplicationRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<FuncApplicationVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<FuncApplicationVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param funcApplicationQueryListCommand
	 * @return
	 */
	MultiResponse<FuncApplicationVO> queryList(FuncApplicationQueryListCommand funcApplicationQueryListCommand);

	/**
	 * 分页查询
	 * @param funcApplicationPageQueryCommand
	 * @return
	 */
	PageResponse<FuncApplicationVO> pageQuery(FuncApplicationPageQueryCommand funcApplicationPageQueryCommand);

}
