package com.particle.func.adapter.feign.client.rpc;

import com.particle.func.client.dto.data.FuncTransVO;
import com.particle.global.trans.api.ITransService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 功能菜单翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.func:func}",path = "/rpc/func")
public interface FuncTransRpcFeignClient extends ITransService<FuncTransVO,Long> {
}
