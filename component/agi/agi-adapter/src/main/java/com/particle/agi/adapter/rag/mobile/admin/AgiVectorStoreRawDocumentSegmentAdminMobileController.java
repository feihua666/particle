package com.particle.agi.adapter.rag.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.agi.client.rag.api.IAgiVectorStoreRawDocumentSegmentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 知识存储原始文档片段后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2025-01-08 16:55:48
 */
@Tag(name = "知识存储原始文档片段移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/agi_vector_store_raw_document_segment")
public class AgiVectorStoreRawDocumentSegmentAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IAgiVectorStoreRawDocumentSegmentApplicationService iAgiVectorStoreRawDocumentSegmentApplicationService;


}
