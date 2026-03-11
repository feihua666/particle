package com.particle.audit.adapter.feign.client.auditrecord.rpc;

import com.particle.audit.client.auditrecord.dto.command.AuditRecordCreateCommand;
import com.particle.audit.client.auditrecord.dto.data.AuditRecordVO;
import com.particle.audit.client.auditrecord.dto.data.AuditResultDictVO;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 审核记录远程调用
 * </p>
 *
 * @author yw
 * @since 2026-01-19 14:57:45
 */
@FeignClient(name = "${particle.feign-client.audit.name:audit-start}", contextId = "auditRecordRpcFeignClient", url = "${particle.feign-client.audit.url:}", path = "/rpc/audit_record")
public interface AuditRecordRpcFeignClient {

    /**
     * 添加审批记录
     *
     * @param auditRecordCreateCommand
     * @return
     */
    @PostMapping("/create")
    public SingleResponse<AuditRecordVO> create(@RequestBody AuditRecordCreateCommand auditRecordCreateCommand);

    /**
     * 审批结果字典
     *
     * @return
     */
    @GetMapping("/auditResultDict")
    public SingleResponse<AuditResultDictVO> auditResultDict();

}
