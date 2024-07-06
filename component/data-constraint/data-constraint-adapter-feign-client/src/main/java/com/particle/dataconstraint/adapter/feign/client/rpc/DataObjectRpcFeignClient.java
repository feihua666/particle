package com.particle.dataconstraint.adapter.feign.client.rpc;

import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * <p>
 * 数据对象远程调用
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@FeignClient(name = "${particle.feign-client.name.data-constraint:data-constraint}",path = "/rpc/data_object")
public interface DataObjectRpcFeignClient {


    /**
     * 根据id获取多个数据对象
     * @param dataObjectIds
     * @return
     */
    @GetMapping("/getByDataObjectIds")
    public MultiResponse<DataObjectVO> getByDataObjectIds(List<Long> dataObjectIds);







}
