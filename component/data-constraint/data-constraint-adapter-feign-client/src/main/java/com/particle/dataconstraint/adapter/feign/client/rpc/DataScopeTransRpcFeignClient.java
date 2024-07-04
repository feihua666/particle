package com.particle.dataconstraint.adapter.feign.client.rpc;

import com.particle.dataconstraint.client.dto.data.DataScopeTransVO;
import com.particle.global.trans.api.ITransService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 数据对象翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2024-07-02 11:58:56
 */
@FeignClient(name = "${particle.feign-client.name.data-constraint:data-constraint}",path = "/rpc/data_scope")
public interface DataScopeTransRpcFeignClient extends ITransService<DataScopeTransVO,Long> {
}
