package com.particle.oplog.adapter.feign.client.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.oplog.client.dto.command.OpLogCreateCommand;
import com.particle.oplog.client.dto.data.OpLogVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 操作日志远程调用
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@FeignClient(name = "${particle.feign-client.oplog.name:oplog-start}", contextId = "opLogRpcFeignClient", url = "${particle.feign-client.oplog.url:}", path = "/rpc/op_log")
public interface OpLogRpcFeignClient {

    /**
     * 添加操作日志
     * @param opLogCreateCommand
     * @return
     */
    @PostMapping("/create")
    public SingleResponse<OpLogVO> create(@RequestBody OpLogCreateCommand opLogCreateCommand);

}
