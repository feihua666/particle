package com.particle.dept.adapter.feign.client.rpc;

import com.particle.dept.client.dto.data.DeptTransVO;
import com.particle.global.trans.api.ITransService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 部门翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@FeignClient(name = "${particle.feign-client.name.dept:dept}",path = "/rpc/dept")
public interface DeptTransRpcFeignClient extends ITransService<DeptTransVO,Long> {
}
