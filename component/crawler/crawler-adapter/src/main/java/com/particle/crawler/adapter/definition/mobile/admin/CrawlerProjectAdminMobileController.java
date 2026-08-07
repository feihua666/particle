package com.particle.crawler.adapter.definition.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.crawler.client.definition.api.ICrawlerProjectApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫项目后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Tag(name = "爬虫项目移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/crawler_project")
public class CrawlerProjectAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ICrawlerProjectApplicationService iCrawlerProjectApplicationService;


}