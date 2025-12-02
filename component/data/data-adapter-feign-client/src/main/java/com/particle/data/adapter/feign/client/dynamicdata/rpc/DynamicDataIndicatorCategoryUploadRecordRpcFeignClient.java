package com.particle.data.adapter.feign.client.dynamicdata.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 动态数据指标分类上传记录远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/dynamic_data_indicator_category_upload_record")
public interface DynamicDataIndicatorCategoryUploadRecordRpcFeignClient {









}
