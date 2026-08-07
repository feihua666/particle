package com.particle.crawler.adapter.execution.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crawler.client.execution.api.ICrawlerDataStoreContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫结构数据存储内容前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Tag(name = "爬虫结构数据存储内容wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/crawler_data_store_content")
public class CrawlerDataStoreContentFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrawlerDataStoreContentApplicationService iCrawlerDataStoreContentApplicationService;


}