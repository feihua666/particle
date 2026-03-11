package com.particle.user.adapter.feign.client.identifier.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 用户登录标识远程调用
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@FeignClient(name = "${particle.feign-client.user.name:user-start}", contextId = "userIdentifierRpcFeignClient", url = "${particle.feign-client.user.url:}", path = "/rpc/user-identifier")
public interface UserIdentifierRpcFeignClient {

	/**
	 * 根据登录标识获取
	 * @param identifier
	 * @return
	 */
    @GetMapping("/getByIdentifier")
	public SingleResponse<UserIdentifierVO> getByIdentifier(@RequestParam String identifier);

    /**
     * 根据用户id和登录标识类型获取
     * @param userId
     * @param identifierTypeDictId
     * @return
     */
    @GetMapping("/getByUserIdAndType")
	public SingleResponse<UserIdentifierVO> getByUserIdAndType(@RequestParam Long userId,@RequestParam Long identifierTypeDictId);


}
