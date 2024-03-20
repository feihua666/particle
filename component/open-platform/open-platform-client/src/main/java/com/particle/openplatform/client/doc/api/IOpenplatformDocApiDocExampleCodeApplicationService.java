package com.particle.openplatform.client.doc.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocExampleCodeCreateCommand;
import com.particle.openplatform.client.doc.dto.command.OpenplatformDocApiDocExampleCodeUpdateCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocExampleCodeVO;

/**
 * <p>
 * 开放接口文档示例代码 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
public interface IOpenplatformDocApiDocExampleCodeApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param openplatformDocApiDocExampleCodeCreateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocExampleCodeVO> create(OpenplatformDocApiDocExampleCodeCreateCommand openplatformDocApiDocExampleCodeCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocExampleCodeVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param openplatformDocApiDocExampleCodeUpdateCommand
	 * @return
	 */
	SingleResponse<OpenplatformDocApiDocExampleCodeVO> update(OpenplatformDocApiDocExampleCodeUpdateCommand openplatformDocApiDocExampleCodeUpdateCommand);

}
