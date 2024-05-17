package com.particle.dream.client.ssq.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedCreateCommand;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedUpdateCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;

/**
 * <p>
 * 双色球开奖 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
public interface ISsqCodeOpenedApplicationService extends IBaseApplicationService {
	/**
	 * 添加/创建一个领域对象
	 * @param ssqCodeOpenedCreateCommand
	 * @return
	 */
	SingleResponse<SsqCodeOpenedVO> create(SsqCodeOpenedCreateCommand ssqCodeOpenedCreateCommand);

	/**
	 * 删除领域对象
	 * @param deleteCommand
	 * @return
	 */
	SingleResponse<SsqCodeOpenedVO> delete(IdCommand deleteCommand);

	/**
	 * 更新领域对象
	 * @param ssqCodeOpenedUpdateCommand
	 * @return
	 */
	SingleResponse<SsqCodeOpenedVO> update(SsqCodeOpenedUpdateCommand ssqCodeOpenedUpdateCommand);

}
