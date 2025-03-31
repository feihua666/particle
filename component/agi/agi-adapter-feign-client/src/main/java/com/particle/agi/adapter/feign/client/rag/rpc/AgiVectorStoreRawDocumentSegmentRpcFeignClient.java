package com.particle.agi.adapter.feign.client.rag.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 知识存储原始文档片段远程调用
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@FeignClient(name = "${particle.feign-client.name.agi:agi}",path = "/rpc/agi_vector_store_raw_document_segment")
public interface AgiVectorStoreRawDocumentSegmentRpcFeignClient {









}
