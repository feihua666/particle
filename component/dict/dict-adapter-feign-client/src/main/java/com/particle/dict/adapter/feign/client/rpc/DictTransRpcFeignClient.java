package com.particle.dict.adapter.feign.client.rpc;

import com.particle.dict.client.dto.data.DictTransVO;
import com.particle.global.trans.api.ITransService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 字典翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.dict:dict}",path = "/rpc/dict")
public interface DictTransRpcFeignClient extends ITransService<DictTransVO,Long> {
}
