package com.particle.oplog.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.oplog.domain.OpLog;
import com.particle.oplog.domain.OpLogId;

/**
 * <p>
 * 操作日志 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
public interface OpLogGateway extends IBaseGateway<OpLogId,OpLog> {
}
