package com.particle.usagecount.adapter.feign.client.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.usagecount.client.dto.command.UsageCountRecordMarkCommand;
import com.particle.usagecount.client.dto.data.UsageCountRecordMarkVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 使用次数记录远程调用
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@FeignClient(name = "${particle.feign-client.usagecount.name:usagecount-start}", contextId = "usageCountRecordRpcFeignClient", url = "${particle.feign-client.usagecount.url:}", path = "/rpc/usage_count_record")
public interface UsageCountRecordRpcFeignClient {



    /**
     * 标记使用次数记录
     * @param usageCountRecordMarkCommand
     * @return
     */
    @PostMapping("/mark")
    SingleResponse<UsageCountRecordMarkVO> mark(@RequestBody UsageCountRecordMarkCommand usageCountRecordMarkCommand);






}
