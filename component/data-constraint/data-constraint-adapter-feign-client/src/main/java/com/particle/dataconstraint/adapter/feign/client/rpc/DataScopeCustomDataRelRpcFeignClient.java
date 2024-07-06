package com.particle.dataconstraint.adapter.feign.client.rpc;

import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 * 数据范围自定义数据关系远程调用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@FeignClient(name = "${particle.feign-client.name.data-constraint:data-constraint}",path = "/rpc/data_scope_custom_data_rel")
public interface DataScopeCustomDataRelRpcFeignClient {



    /**
     * 根据id获取多个数据范围
     * @param dataScopeIds
     * @return
     */
    @GetMapping("/getByDataScopeIds")
    public MultiResponse<DataScopeCustomDataRelVO> getByDataScopeIds(List<Long> dataScopeIds);

}
