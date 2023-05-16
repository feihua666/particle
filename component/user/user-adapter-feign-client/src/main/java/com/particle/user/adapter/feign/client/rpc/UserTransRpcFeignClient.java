package com.particle.user.adapter.feign.client.rpc;

import com.particle.global.trans.api.ITransService;
import com.particle.user.client.dto.data.UserTransVO;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 字典翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.user:user}",path = "/rpc/user")
public interface UserTransRpcFeignClient extends ITransService<UserTransVO,Long> {

}
