package com.particle.usagecount.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefinePageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountDefineQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountDefineVO;

/**
 * <p>
 * 使用次数定义 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IUsageCountDefineRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅更新时使用
	 * @param detailForUpdateCommand
	 * @return
	 */
	SingleResponse<UsageCountDefineVO> queryDetailForUpdate(IdCommand detailForUpdateCommand);

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<UsageCountDefineVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param usageCountDefineQueryListCommand
	 * @return
	 */
	MultiResponse<UsageCountDefineVO> queryList(UsageCountDefineQueryListCommand usageCountDefineQueryListCommand);

	/**
	 * 分页查询
	 * @param usageCountDefinePageQueryCommand
	 * @return
	 */
	PageResponse<UsageCountDefineVO> pageQuery(UsageCountDefinePageQueryCommand usageCountDefinePageQueryCommand);

}
