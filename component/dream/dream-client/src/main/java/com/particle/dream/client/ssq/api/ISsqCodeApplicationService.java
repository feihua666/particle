package com.particle.dream.client.ssq.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.global.dto.response.Response;

/**
 * <p>
 * 双色球号码 应用门面服务类
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
public interface ISsqCodeApplicationService extends IBaseApplicationService {

	/**
	 * 初始化所有号码
	 * @return
	 */
	public Response allCodeInit();

	/**
	 * 更新所有号码，主要用于在初始化完成后，可能代码有问题，或加了字段，重新跑
	 * @return
	 */
	public Response allCodeUpdate();
	public Response allCodeStop();

}
