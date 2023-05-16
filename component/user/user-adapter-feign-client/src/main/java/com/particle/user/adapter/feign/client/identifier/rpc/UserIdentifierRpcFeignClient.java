package com.particle.user.adapter.feign.client.identifier.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 用户登录标识远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.name.user:user}",path = "/rpc/user-identifier")
public interface UserIdentifierRpcFeignClient {

	/**
	 * 根据登录标识获取
	 * @param identifier
	 * @return
	 */
	public SingleResponse<UserIdentifierVO> getByIdentifier(String identifier);


}
