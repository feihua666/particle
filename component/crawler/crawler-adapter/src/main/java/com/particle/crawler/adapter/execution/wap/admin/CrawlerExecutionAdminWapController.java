package com.particle.crawler.adapter.execution.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crawler.client.execution.api.ICrawlerExecutionApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫执行实例后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Tag(name = "爬虫执行实例wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/crawler_execution")
public class CrawlerExecutionAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrawlerExecutionApplicationService iCrawlerExecutionApplicationService;


}