package com.particle.lowcode.client.generator.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplatePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplateQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;

/**
 * <p>
 * 低代码片段模板 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
public interface ILowcodeSegmentTemplateRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentTemplateVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentTemplateVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param lowcodeSegmentTemplateQueryListCommand
	 * @return
	 */
	MultiResponse<LowcodeSegmentTemplateVO> queryList(LowcodeSegmentTemplateQueryListCommand lowcodeSegmentTemplateQueryListCommand);

	/**
	 * 分页查询
	 * @param lowcodeSegmentTemplatePageQueryCommand
	 * @return
	 */
	PageResponse<LowcodeSegmentTemplateVO> pageQuery(LowcodeSegmentTemplatePageQueryCommand lowcodeSegmentTemplatePageQueryCommand);

}
