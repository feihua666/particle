package com.particle.crawler.adapter.execution.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.crawler.client.execution.api.ICrawlerExecutionApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫执行实例后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Tag(name = "爬虫执行实例移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/crawler_execution")
public class CrawlerExecutionAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrawlerExecutionApplicationService iCrawlerExecutionApplicationService;


}