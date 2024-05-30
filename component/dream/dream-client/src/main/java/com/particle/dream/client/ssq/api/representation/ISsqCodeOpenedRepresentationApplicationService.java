package com.particle.dream.client.ssq.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedPageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 双色球开奖 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISsqCodeOpenedRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SsqCodeOpenedVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param ssqCodeOpenedQueryListCommand
	 * @return
	 */
	MultiResponse<SsqCodeOpenedVO> queryList(SsqCodeOpenedQueryListCommand ssqCodeOpenedQueryListCommand);

	/**
	 * 分页查询
	 * @param ssqCodeOpenedPageQueryCommand
	 * @return
	 */
	PageResponse<SsqCodeOpenedVO> pageQuery(SsqCodeOpenedPageQueryCommand ssqCodeOpenedPageQueryCommand);

}
