package com.particle.crawler.adapter.execution.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫原始数据存储内容前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Tag(name = "爬虫原始数据存储内容pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/crawler_raw_store_content")
public class CrawlerRawStoreContentFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ICrawlerRawStoreContentApplicationService iCrawlerRawStoreContentApplicationService;


}