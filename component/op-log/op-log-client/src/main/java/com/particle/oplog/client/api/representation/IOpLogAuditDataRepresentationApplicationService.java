package com.particle.oplog.client.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataPageQueryCommand;
import com.particle.oplog.client.dto.command.representation.OpLogAuditDataQueryListCommand;
import com.particle.oplog.client.dto.data.OpLogAuditDataVO;

/**
 * <p>
 * 操作日志审计数据 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpLogAuditDataRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpLogAuditDataVO> queryDetail(IdCommand detailCommand);

	/**
	 * 列表查询
	 * @param opLogAuditDataQueryListCommand
	 * @return
	 */
	MultiResponse<OpLogAuditDataVO> queryList(OpLogAuditDataQueryListCommand opLogAuditDataQueryListCommand);

	/**
	 * 分页查询
	 * @param opLogAuditDataPageQueryCommand
	 * @return
	 */
	PageResponse<OpLogAuditDataVO> pageQuery(OpLogAuditDataPageQueryCommand opLogAuditDataPageQueryCommand);

}
