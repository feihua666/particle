package com.particle.crawler.adapter.execution.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.crawler.client.execution.api.ICrawlerExecutionApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫执行实例前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Tag(name = "爬虫执行实例wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/crawler_execution")
public class CrawlerExecutionFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ICrawlerExecutionApplicationService iCrawlerExecutionApplicationService;


}