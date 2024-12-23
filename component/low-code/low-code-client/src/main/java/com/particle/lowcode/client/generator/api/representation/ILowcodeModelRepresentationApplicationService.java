package com.particle.lowcode.client.generator.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;

/**
 * <p>
 * 低代码模型 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
public interface ILowcodeModelRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeModelVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<LowcodeModelVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param lowcodeModelQueryListCommand
	 * @return
	 */
	MultiResponse<LowcodeModelVO> queryList(LowcodeModelQueryListCommand lowcodeModelQueryListCommand);

	/**
	 * 分页查询
	 * @param lowcodeModelPageQueryCommand
	 * @return
	 */
	PageResponse<LowcodeModelVO> pageQuery(LowcodeModelPageQueryCommand lowcodeModelPageQueryCommand);

}
