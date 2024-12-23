package com.particle.oplog.domain.error.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.oplog.domain.error.OpLogErrorContent;
import com.particle.oplog.domain.error.OpLogErrorContentId;

/**
 * <p>
 * 操作异常日志内容 防腐层
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
public interface OpLogErrorContentGateway extends IBaseGateway<OpLogErrorContentId,OpLogErrorContent> {
}
