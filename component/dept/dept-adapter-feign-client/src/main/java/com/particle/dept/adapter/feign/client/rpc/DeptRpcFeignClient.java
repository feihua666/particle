package com.particle.dept.adapter.feign.client.rpc;

import com.particle.dept.client.dto.data.DeptVO;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 * 部门远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@FeignClient(name = "${particle.feign-client.name.dept:dept}",path = "/rpc/dept")
public interface DeptRpcFeignClient {

    /**
     * 主要用于获取当前登录用户的数据权限内的部门信息
     * @return
     */
    @GetMapping("/dataConstrainList")
    public MultiResponse<DeptVO> dataConstrainList();

    /**
     * 根据用户id获取部门信息
     * @param userId
     * @return
     */
    @GetMapping("/getByUserId")
    public SingleResponse<DeptVO> getByUserId(Long userId);
}
