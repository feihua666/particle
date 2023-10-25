package com.particle.usagecount.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordDetailQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordDetailVO;

/**
 * <p>
 * 使用次数记录明细 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IUsageCountRecordDetailRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<UsageCountRecordDetailVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param usageCountRecordDetailQueryListCommand
	 * @return
	 */
	MultiResponse<UsageCountRecordDetailVO> queryList(UsageCountRecordDetailQueryListCommand usageCountRecordDetailQueryListCommand);

	/**
	 * 分页查询
	 * @param usageCountRecordDetailPageQueryCommand
	 * @return
	 */
	PageResponse<UsageCountRecordDetailVO> pageQuery(UsageCountRecordDetailPageQueryCommand usageCountRecordDetailPageQueryCommand);

}
