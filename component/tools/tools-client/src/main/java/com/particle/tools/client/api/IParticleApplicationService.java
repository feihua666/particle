package com.particle.tools.client.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.Response;
import com.particle.tools.client.dto.command.AddFieldCommand;
import com.particle.tools.client.dto.command.AddWarehouseAndExWarehouseCommand;
import com.particle.tools.client.dto.command.DeleteModelServiceCommand;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * particle 项目相关 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:15
 */
public interface IParticleApplicationService extends IBaseApplicationService {

	/**
	 * 主要用于对已生成的代码，添加字段，包括常用的po、command、vo等，注意不包括sql
	 * @param addFieldCommand
	 * @return
	 */
	public Response addField(AddFieldCommand addFieldCommand);

	/**
	 * 主要用于对已生成的代码，删除模型服务，主要用于生成错误，或者删除多余的服务
	 * @param deleteModelServiceCommand
	 * @return
	 */
	public Response deleteModelService(DeleteModelServiceCommand deleteModelServiceCommand);

	/**
	 * 添加出入库
	 * @param addWarehouseAndExWarehouseCommand
	 * @return
	 */
	public Response addWarehouseAndExWarehouse(AddWarehouseAndExWarehouseCommand addWarehouseAndExWarehouseCommand);
}
