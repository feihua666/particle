package com.particle.func.client.funcapplicationfuncrel.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelPageQueryCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelQueryListCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 功能应用功能关系 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IFuncApplicationFuncRelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<FuncApplicationFuncRelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<FuncApplicationFuncRelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param funcApplicationFuncRelQueryListCommand
	 * @return
	 */
	MultiResponse<FuncApplicationFuncRelVO> queryList(FuncApplicationFuncRelQueryListCommand funcApplicationFuncRelQueryListCommand);

	/**
	 * 分页查询
	 * @param funcApplicationFuncRelPageQueryCommand
	 * @return
	 */
	PageResponse<FuncApplicationFuncRelVO> pageQuery(FuncApplicationFuncRelPageQueryCommand funcApplicationFuncRelPageQueryCommand);




	/**
	 * 查询功能应用已分配的功能id
	 * @param funcIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncApplicationIdsByFuncId(IdCommand funcIdCommand);

	/**
	 * 查询功能已分配的功能应用id
	 * @param funcApplicationIdCommand
	 * @return
	 */
	public MultiResponse<Long> queryFuncIdsByFuncApplicationId(IdCommand funcApplicationIdCommand);
}
