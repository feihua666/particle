package com.particle.crawler.adapter.execution.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.crawler.client.execution.api.ICrawlerDataStoreApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫结构数据存储前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Tag(name = "爬虫结构数据存储pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/crawler_data_store")
public class CrawlerDataStoreFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrawlerDataStoreApplicationService iCrawlerDataStoreApplicationService;


}