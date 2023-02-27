package com.particle.lowcode.domain.generator.gateway;

import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.LowcodeModelItemId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * 低代码模型项目 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
public interface LowcodeModelItemGateway extends IBaseGateway<LowcodeModelItemId,LowcodeModelItem> {

	/**
	 * 清空
	 * @param lowcodeModelId
	 * @return
	 */
	boolean clearByLowcodeModelId(LowcodeModelId lowcodeModelId);
}
