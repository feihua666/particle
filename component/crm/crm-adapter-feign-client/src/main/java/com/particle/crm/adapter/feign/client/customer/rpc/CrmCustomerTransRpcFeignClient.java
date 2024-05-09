package com.particle.crm.adapter.feign.client.customer.rpc;

import com.particle.crm.client.customer.dto.data.CrmCustomerTransVO;
import com.particle.global.trans.api.ITransService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>
 * 客户翻译远程调用
 * </p>
 *
 * @author yw
 * @since 2024-05-06 16:29:57
 */
@FeignClient(name = "${particle.feign-client.name.crm:crm}",path = "/rpc/crm_customer")
public interface CrmCustomerTransRpcFeignClient extends ITransService<CrmCustomerTransVO,Long> {
}
