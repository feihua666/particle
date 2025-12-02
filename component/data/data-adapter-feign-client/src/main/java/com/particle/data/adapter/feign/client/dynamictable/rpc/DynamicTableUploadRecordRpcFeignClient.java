package com.particle.data.adapter.feign.client.dynamictable.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 动态数据表格上传记录远程调用
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@FeignClient(name = "${particle.feign-client.name.data:data}",path = "/rpc/dynamic_table_upload_record")
public interface DynamicTableUploadRecordRpcFeignClient {









}
