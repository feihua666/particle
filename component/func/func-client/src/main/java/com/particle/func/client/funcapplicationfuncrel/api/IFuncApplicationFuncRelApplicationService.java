package com.particle.func.client.funcapplicationfuncrel.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncApplicationAssignFuncCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncApplicationFuncRelCreateCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.FuncAssignFuncApplicationCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 功能应用功能关系 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
public interface IFuncApplicationFuncRelApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param funcApplicationFuncRelCreateCommand
	 * @return
	 */
	SingleResponse<FuncApplicationFuncRelVO> create(FuncApplicationFuncRelCreateCommand funcApplicationFuncRelCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<FuncApplicationFuncRelVO> delete(IdCommand deleteCommand);



	/**
	 * 功能分配功能应用
	 * @param cf
	 * @return
	 */
	Response funcAssignFuncApplication(FuncAssignFuncApplicationCommand cf);

	/**
	 * 功能应用分配功能
	 * @param cf
	 * @return
	 */
	Response funcApplicationAssignFunc(FuncApplicationAssignFuncCommand cf);

	/**
	 * 根据功能id删除
	 * @param funcIdCommand
	 * @return
	 */
	public Response deleteByFuncId(IdCommand funcIdCommand);

	/**
	 * 根据功能应用id删除
	 * @param funcApplicationIdCommand
	 * @return
	 */
	public Response deleteByFuncApplicationId(IdCommand funcApplicationIdCommand);}
