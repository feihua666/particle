package com.particle.user.adapter.feign.client.identifier.rpc;

import com.particle.global.dto.response.SingleResponse;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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
    @GetMapping("/getByIdentifier")
	public SingleResponse<UserIdentifierVO> getByIdentifier(String identifier);

    /**
     * 根据用户id和登录标识类型获取
     * @param userId
     * @param identifierTypeDictId
     * @return
     */
    @GetMapping("/getByUserIdAndType")
	public SingleResponse<UserIdentifierVO> getByUserIdAndType(Long userId, Long identifierTypeDictId);


}
