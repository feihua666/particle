package com.particle.audit.adapter.auditrecord.rpc;

import com.particle.audit.client.auditrecord.api.representation.IAuditRecordRepresentationApplicationService;
import com.particle.audit.client.auditrecord.dto.command.AuditRecordCreateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.client.auditrecord.dto.data.AuditResultDictVO;
import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.audit.client.auditrecord.api.IAuditRecordApplicationService;
import com.particle.audit.adapter.feign.client.auditrecord.rpc.AuditRecordRpcFeignClient;
import com.particle.global.dto.response.SingleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 审核记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@Tag(name = "审核记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/audit_record")
public class AuditRecordRpcController extends AbstractBaseRpcAdapter implements AuditRecordRpcFeignClient  {

	@Autowired
	private IAuditRecordApplicationService iAuditRecordApplicationService;
	@Autowired
	private IAuditRecordRepresentationApplicationService iAuditRecordRepresentationApplicationService;

	@Operation(summary = "添加审核记录")
	@PostMapping("/create")
	@Override
	public SingleResponse<AuditRecordVO> create(@RequestBody AuditRecordCreateCommand auditRecordCreateCommand) {
		return iAuditRecordApplicationService.create(auditRecordCreateCommand);
	}
	@Operation(summary = "审批结果字典")
	@GetMapping("/auditResultDict")
	@Override
	public SingleResponse<AuditResultDictVO> auditResultDict() {
		return iAuditRecordRepresentationApplicationService.auditResultDict();
	}

}
