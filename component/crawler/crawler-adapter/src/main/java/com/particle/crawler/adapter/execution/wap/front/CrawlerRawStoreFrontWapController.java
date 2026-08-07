package com.particle.crawler.adapter.execution.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫原始数据存储前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Tag(name = "爬虫原始数据存储wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/crawler_raw_store")
public class CrawlerRawStoreFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrawlerRawStoreApplicationService iCrawlerRawStoreApplicationService;


}