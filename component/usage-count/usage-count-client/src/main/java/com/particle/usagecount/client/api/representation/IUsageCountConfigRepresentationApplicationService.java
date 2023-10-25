package com.particle.usagecount.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountConfigQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountConfigVO;

/**
 * <p>
 * 使用次数配置 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IUsageCountConfigRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<UsageCountConfigVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<UsageCountConfigVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param usageCountConfigQueryListCommand
	 * @return
	 */
	MultiResponse<UsageCountConfigVO> queryList(UsageCountConfigQueryListCommand usageCountConfigQueryListCommand);

	/**
	 * 分页查询
	 * @param usageCountConfigPageQueryCommand
	 * @return
	 */
	PageResponse<UsageCountConfigVO> pageQuery(UsageCountConfigPageQueryCommand usageCountConfigPageQueryCommand);

}
