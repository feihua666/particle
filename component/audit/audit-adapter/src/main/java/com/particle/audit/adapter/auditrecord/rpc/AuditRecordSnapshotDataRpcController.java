package com.particle.audit.adapter.auditrecord.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.audit.client.auditrecord.api.IAuditRecordSnapshotDataApplicationService;
import com.particle.audit.adapter.feign.client.auditrecord.rpc.AuditRecordSnapshotDataRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 审核记录数据快照远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:58:02
 */
@Tag(name = "审核记录数据快照远程调用相关接口")
@RestController
@RequestMapping("/rpc/audit_record_snapshot_data")
public class AuditRecordSnapshotDataRpcController extends AbstractBaseRpcAdapter implements AuditRecordSnapshotDataRpcFeignClient  {

	@Autowired
	private IAuditRecordSnapshotDataApplicationService iAuditRecordSnapshotDataApplicationService;


}