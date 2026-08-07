package com.particle.crawler.adapter.definition.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crawler.client.definition.api.ICrawlerDefinitionHistoryApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫定义历史前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:55
 */
@Tag(name = "爬虫定义历史wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/crawler_definition_history")
public class CrawlerDefinitionHistoryFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrawlerDefinitionHistoryApplicationService iCrawlerDefinitionHistoryApplicationService;


}