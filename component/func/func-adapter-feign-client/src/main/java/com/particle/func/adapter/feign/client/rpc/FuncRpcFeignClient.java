package com.particle.func.adapter.feign.client.rpc;

import com.particle.func.client.dto.command.representation.FuncQueryListByIdsCommand;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 菜单功能远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.func:func}",path = "/rpc/func")
public interface FuncRpcFeignClient {

    /**
     * 列表查询菜单功能
     * @param funcQueryListByIdsCommand
     * @return
     */

    @GetMapping("/listByIds")
    public MultiResponse<FuncVO> queryListByIds(FuncQueryListByIdsCommand funcQueryListByIdsCommand);

}
