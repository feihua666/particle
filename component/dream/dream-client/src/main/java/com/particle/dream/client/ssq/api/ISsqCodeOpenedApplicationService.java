package com.particle.dream.client.ssq.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.Response;

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
	 * 初始化所有开奖号码
	 * @return
	 */
	public Response allCodeInit();

	/**
	 * 停止初始化所有开奖号码
	 * @return
	 */
	public Response allCodeStop();

}
