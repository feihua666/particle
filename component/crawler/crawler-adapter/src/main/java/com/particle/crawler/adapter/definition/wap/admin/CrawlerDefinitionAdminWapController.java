package com.particle.crawler.adapter.definition.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crawler.client.definition.api.ICrawlerDefinitionApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫定义后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Tag(name = "爬虫定义wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/crawler_definition")
public class CrawlerDefinitionAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrawlerDefinitionApplicationService iCrawlerDefinitionApplicationService;


}