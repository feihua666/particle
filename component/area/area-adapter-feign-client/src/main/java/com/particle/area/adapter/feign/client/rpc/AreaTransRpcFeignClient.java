package com.particle.area.adapter.feign.client.rpc;

import com.particle.area.client.dto.data.AreaTransVO;
import com.particle.global.trans.api.ITransService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 区域翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.area:area}",path = "/rpc/area")
public interface AreaTransRpcFeignClient extends ITransService<AreaTransVO,Long> {
}
