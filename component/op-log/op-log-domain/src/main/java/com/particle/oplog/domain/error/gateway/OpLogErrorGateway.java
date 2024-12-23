package com.particle.oplog.domain.error.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.oplog.domain.error.OpLogError;
import com.particle.oplog.domain.error.OpLogErrorId;

/**
 * <p>
 * 操作异常日志 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
public interface OpLogErrorGateway extends IBaseGateway<OpLogErrorId,OpLogError> {
}
