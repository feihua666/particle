package com.particle.oplog.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.oplog.domain.OpLogAuditData;
import com.particle.oplog.domain.OpLogAuditDataId;

/**
 * <p>
 * 操作日志审计数据 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
public interface OpLogAuditDataGateway extends IBaseGateway<OpLogAuditDataId,OpLogAuditData> {
}
