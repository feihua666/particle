package com.particle.oplog.adapter.feign.client.error.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.error.dto.command.OpLogErrorCreateCommand;
import com.particle.oplog.client.error.dto.data.OpLogErrorVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 操作异常日志远程调用
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@FeignClient(name = "${particle.feign-client.oplog.name:oplog-start}", contextId = "opLogErrorRpcFeignClient", url = "${particle.feign-client.oplog.url:}", path = "/rpc/op_log_error")
public interface OpLogErrorRpcFeignClient {
    /**
     * 添加异常日志
     * @param opLogErrorCreateCommand
     * @return
     */
    @PostMapping("/create")
    public SingleResponse<OpLogErrorVO> create(@RequestBody OpLogErrorCreateCommand opLogErrorCreateCommand);

}
