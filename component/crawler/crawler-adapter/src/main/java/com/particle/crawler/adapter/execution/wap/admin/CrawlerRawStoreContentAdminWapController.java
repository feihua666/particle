package com.particle.crawler.adapter.execution.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫原始数据存储内容后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Tag(name = "爬虫原始数据存储内容wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/crawler_raw_store_content")
public class CrawlerRawStoreContentAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrawlerRawStoreContentApplicationService iCrawlerRawStoreContentApplicationService;


}