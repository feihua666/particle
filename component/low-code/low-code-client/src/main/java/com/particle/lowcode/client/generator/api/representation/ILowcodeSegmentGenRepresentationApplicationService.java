package com.particle.lowcode.client.generator.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;

/**
 * <p>
 * 低代码生成 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
public interface ILowcodeSegmentGenRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentGenVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentGenVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param lowcodeSegmentGenQueryListCommand
	 * @return
	 */
	MultiResponse<LowcodeSegmentGenVO> queryList(LowcodeSegmentGenQueryListCommand lowcodeSegmentGenQueryListCommand);

	/**
	 * 分页查询
	 * @param lowcodeSegmentGenPageQueryCommand
	 * @return
	 */
	PageResponse<LowcodeSegmentGenVO> pageQuery(LowcodeSegmentGenPageQueryCommand lowcodeSegmentGenPageQueryCommand);

}
