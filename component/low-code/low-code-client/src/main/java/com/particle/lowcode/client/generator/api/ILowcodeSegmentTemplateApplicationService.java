package com.particle.lowcode.client.generator.api;

import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateCreateCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateRenderCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateRenderVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.common.client.api.IBaseApplicationService;

/**
 * <p>
 * 低代码片段模板 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
public interface ILowcodeSegmentTemplateApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param lowcodeSegmentTemplateCreateCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentTemplateVO> create(LowcodeSegmentTemplateCreateCommand lowcodeSegmentTemplateCreateCommand);

	/**
	 * 片段模板渲染测试
	 * @param lowcodeSegmentTemplateRenderCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentTemplateRenderVO> renderTest(LowcodeSegmentTemplateRenderCommand lowcodeSegmentTemplateRenderCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentTemplateVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param lowcodeSegmentTemplateUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentTemplateVO> update(LowcodeSegmentTemplateUpdateCommand lowcodeSegmentTemplateUpdateCommand);

}
