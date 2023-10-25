package com.particle.usagecount.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordPageQueryCommand;
import com.particle.usagecount.client.dto.command.representation.UsageCountRecordQueryListCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordVO;

/**
 * <p>
 * 使用次数记录 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IUsageCountRecordRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<UsageCountRecordVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param usageCountRecordQueryListCommand
	 * @return
	 */
	MultiResponse<UsageCountRecordVO> queryList(UsageCountRecordQueryListCommand usageCountRecordQueryListCommand);

	/**
	 * 分页查询
	 * @param usageCountRecordPageQueryCommand
	 * @return
	 */
	PageResponse<UsageCountRecordVO> pageQuery(UsageCountRecordPageQueryCommand usageCountRecordPageQueryCommand);

}
