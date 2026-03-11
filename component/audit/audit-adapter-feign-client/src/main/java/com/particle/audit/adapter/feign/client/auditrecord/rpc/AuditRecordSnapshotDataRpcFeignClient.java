package com.particle.audit.adapter.feign.client.auditrecord.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 审核记录数据快照远程调用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@FeignClient(name = "${particle.feign-client.audit.name:audit-start}", contextId = "auditRecordSnapshotDataRpcFeignClient", url = "${particle.feign-client.audit.url:}", path = "/rpc/audit_record_snapshot_data")
public interface AuditRecordSnapshotDataRpcFeignClient {









}
