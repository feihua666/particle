package com.particle.global.document.endpoint;

import cn.hutool.extra.servlet.JakartaServletUtil;
import com.particle.global.document.template.GlobalDocumentTemplate;
import com.particle.global.document.template.GlobalDocumentTemplateService;
import com.particle.global.tool.file.FileTool;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文档相关服务接口
 * </p>
 *
 * @author yangwei
 * @since 2023-06-01 15:52
 */
@Tag(name = "全局服务文档相关接口")
@RestController
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private GlobalDocumentTemplateService globalDocumentTemplateService;

	@Operation(summary = "下载模板")
	@GetMapping( "/downloadTemplate")
	public void downloadTemplate(HttpServletResponse response, String templateIdentifier) throws Throwable {
		GlobalDocumentTemplate globalDocumentTemplate = globalDocumentTemplateService.resolveTemplate(templateIdentifier);

		String mimeType = FileTool.getMimeType(globalDocumentTemplate.getTemplateName());

		JakartaServletUtil.write(response,globalDocumentTemplate.getInputStream(),mimeType,globalDocumentTemplate.getTemplateName());

	}
}
