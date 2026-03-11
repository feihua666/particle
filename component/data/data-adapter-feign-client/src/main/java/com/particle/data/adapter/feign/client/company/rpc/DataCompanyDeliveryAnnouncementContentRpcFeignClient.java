package com.particle.data.adapter.feign.client.company.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 企业送达公告内容远程调用
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:18
 */
@FeignClient(name = "${particle.feign-client.data.name:data-start}", contextId = "dataCompanyDeliveryAnnouncementContentRpcFeignClient", url = "${particle.feign-client.data.url:}", path = "/rpc/data_company_delivery_announcement_content")
public interface DataCompanyDeliveryAnnouncementContentRpcFeignClient {









}
