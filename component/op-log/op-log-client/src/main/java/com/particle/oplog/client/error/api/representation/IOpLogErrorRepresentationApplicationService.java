package com.particle.oplog.client.error.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorPageQueryCommand;
import com.particle.oplog.client.error.dto.command.representation.OpLogErrorQueryListCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;

/**
 * <p>
 * 操作异常日志 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpLogErrorRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpLogErrorVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param opLogErrorQueryListCommand
	 * @return
	 */
	MultiResponse<OpLogErrorVO> queryList(OpLogErrorQueryListCommand opLogErrorQueryListCommand);

	/**
	 * 分页查询
	 * @param opLogErrorPageQueryCommand
	 * @return
	 */
	PageResponse<OpLogErrorVO> pageQuery(OpLogErrorPageQueryCommand opLogErrorPageQueryCommand);

}
