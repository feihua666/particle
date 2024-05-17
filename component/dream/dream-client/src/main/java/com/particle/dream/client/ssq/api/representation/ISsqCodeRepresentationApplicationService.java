package com.particle.dream.client.ssq.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodePageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 双色球号码 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface ISsqCodeRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<SsqCodeVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param ssqCodeQueryListCommand
	 * @return
	 */
	MultiResponse<SsqCodeVO> queryList(SsqCodeQueryListCommand ssqCodeQueryListCommand);

	/**
	 * 分页查询
	 * @param ssqCodePageQueryCommand
	 * @return
	 */
	PageResponse<SsqCodeVO> pageQuery(SsqCodePageQueryCommand ssqCodePageQueryCommand);

}
