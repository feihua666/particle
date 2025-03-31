package com.particle.agi.adapter.rag.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.agi.client.rag.api.IAgiVectorStoreRawDocumentSegmentApplicationService;
import com.particle.agi.adapter.feign.client.rag.rpc.AgiVectorStoreRawDocumentSegmentRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 知识存储原始文档片段远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Tag(name = "知识存储原始文档片段远程调用相关接口")
@RestController
@RequestMapping("/rpc/agi_vector_store_raw_document_segment")
public class AgiVectorStoreRawDocumentSegmentRpcController extends AbstractBaseRpcAdapter implements AgiVectorStoreRawDocumentSegmentRpcFeignClient  {

	@Autowired
	private IAgiVectorStoreRawDocumentSegmentApplicationService iAgiVectorStoreRawDocumentSegmentApplicationService;


}
