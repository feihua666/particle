package com.particle.dream.client.ssq.api;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.dream.client.ssq.dto.command.SsqCodeOpenedPredictionParameterTuningCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedPredictionParameterTuningRegionVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.Response;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.web.bind.annotation.RequestBody;

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

	/**
	 * 根据双色球开奖号码调参预测
	 * @param predictionParameterTuningCommand
	 * @return
	 */
	public MultiResponse<SsqCodeOpenedPredictionParameterTuningRegionVO> predictionParameterTuning(@RequestBody SsqCodeOpenedPredictionParameterTuningCommand predictionParameterTuningCommand);
}
