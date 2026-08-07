package com.particle.crawler.adapter.definition.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crawler.client.definition.api.ICrawlerProjectApplicationService;
import com.particle.crawler.adapter.feign.client.definition.rpc.CrawlerProjectRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫项目远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:21
 */
@Tag(name = "爬虫项目远程调用相关接口")
@RestController
@RequestMapping("/rpc/crawler_project")
public class CrawlerProjectRpcController extends AbstractBaseRpcAdapter implements CrawlerProjectRpcFeignClient  {

	@Autowired
	private ICrawlerProjectApplicationService iCrawlerProjectApplicationService;


}