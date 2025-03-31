package com.particle.agi.adapter.rag.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.agi.client.rag.api.IAgiVectorStoreRawDocumentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 知识存储原始文档前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:54:59
 */
@Tag(name = "知识存储原始文档pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/agi_vector_store_raw_document")
public class AgiVectorStoreRawDocumentFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IAgiVectorStoreRawDocumentApplicationService iAgiVectorStoreRawDocumentApplicationService;


}
