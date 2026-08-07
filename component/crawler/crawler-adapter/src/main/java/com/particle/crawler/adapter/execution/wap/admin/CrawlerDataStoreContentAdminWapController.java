package com.particle.crawler.adapter.execution.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crawler.client.execution.api.ICrawlerDataStoreContentApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫结构数据存储内容后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:34
 */
@Tag(name = "爬虫结构数据存储内容wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/crawler_data_store_content")
public class CrawlerDataStoreContentAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrawlerDataStoreContentApplicationService iCrawlerDataStoreContentApplicationService;


}