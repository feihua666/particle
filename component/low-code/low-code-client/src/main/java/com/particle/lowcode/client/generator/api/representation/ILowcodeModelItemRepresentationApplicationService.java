package com.particle.lowcode.client.generator.api.representation;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 低代码模型项目 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
public interface ILowcodeModelItemRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeModelItemVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<LowcodeModelItemVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param lowcodeModelItemQueryListCommand
	 * @return
	 */
	MultiResponse<LowcodeModelItemVO> queryList(LowcodeModelItemQueryListCommand lowcodeModelItemQueryListCommand);

	/**
	 * 分页查询
	 * @param lowcodeModelItemPageQueryCommand
	 * @return
	 */
	PageResponse<LowcodeModelItemVO> pageQuery(LowcodeModelItemPageQueryCommand lowcodeModelItemPageQueryCommand);

}
