package com.particle.oplog.client.error.api.representation;

import com.particle.common.client.api.IBaseApplicationService;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.error.dto.data.OpLogErrorContentVO;

/**
 * <p>
 * 操作异常日志内容 应用门面展示服务类
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
public interface IOpLogErrorContentRepresentationApplicationService extends IBaseApplicationService {

	/**
	 * 查询详情，仅展示详情使用
	 * @param detailCommand
	 * @return
	 */
	SingleResponse<OpLogErrorContentVO> queryDetail(IdCommand detailCommand);

	SingleResponse<OpLogErrorContentVO> detailByOpLogErrorId(IdCommand detailCommand);

}
