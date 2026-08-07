package com.particle.crawler.adapter.definition.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.crawler.client.definition.api.ICrawlerProjectApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫项目前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Tag(name = "爬虫项目pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/crawler_project")
public class CrawlerProjectFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrawlerProjectApplicationService iCrawlerProjectApplicationService;


}