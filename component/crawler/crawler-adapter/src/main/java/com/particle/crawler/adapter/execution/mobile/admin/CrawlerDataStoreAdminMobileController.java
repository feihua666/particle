package com.particle.crawler.adapter.execution.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.crawler.client.execution.api.ICrawlerDataStoreApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫结构数据存储后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 12:37:19
 */
@Tag(name = "爬虫结构数据存储移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/crawler_data_store")
public class CrawlerDataStoreAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrawlerDataStoreApplicationService iCrawlerDataStoreApplicationService;


}