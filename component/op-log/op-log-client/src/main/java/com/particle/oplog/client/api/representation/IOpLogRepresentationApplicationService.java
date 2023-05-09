package com.particle.oplog.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.dto.command.representation.OpLogPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogVO;

/**
 * <p>
 * 操作日志 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpLogRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpLogVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param opLogQueryListCommand
	 * @return
	 */
	MultiResponse<OpLogVO> queryList(OpLogQueryListCommand opLogQueryListCommand);

	/**
	 * 分页查询
	 * @param opLogPageQueryCommand
	 * @return
	 */
	PageResponse<OpLogVO> pageQuery(OpLogPageQueryCommand opLogPageQueryCommand);

}
