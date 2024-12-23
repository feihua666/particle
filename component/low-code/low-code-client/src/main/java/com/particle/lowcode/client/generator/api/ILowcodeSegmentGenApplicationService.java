package com.particle.lowcode.client.generator.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenCreateCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenRenderGenCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenRenderGenVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;

/**
 * <p>
 * 低代码生成 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
public interface ILowcodeSegmentGenApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param lowcodeSegmentGenCreateCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentGenVO> create(LowcodeSegmentGenCreateCommand lowcodeSegmentGenCreateCommand);

	/**
	 * 设计和渲染
	 * @param lowcodeSegmentGenRenderGenCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentGenRenderGenVO> renderGen(LowcodeSegmentGenRenderGenCommand lowcodeSegmentGenRenderGenCommand);
	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentGenVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param lowcodeSegmentGenUpdateCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentGenVO> update(LowcodeSegmentGenUpdateCommand lowcodeSegmentGenUpdateCommand);

	/**
	 * 重新加载模型json数据
	 * @param reloadCommand
	 * @return
	 */
	SingleResponse<LowcodeSegmentGenVO> reloadLowcodeModelJson(IdCommand reloadCommand);

}
