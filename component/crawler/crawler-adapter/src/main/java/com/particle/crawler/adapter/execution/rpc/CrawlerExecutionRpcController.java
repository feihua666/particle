package com.particle.crawler.adapter.execution.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crawler.client.execution.api.ICrawlerExecutionApplicationService;
import com.particle.crawler.adapter.feign.client.execution.rpc.CrawlerExecutionRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫执行实例远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:13
 */
@Tag(name = "爬虫执行实例远程调用相关接口")
@RestController
@RequestMapping("/rpc/crawler_execution")
public class CrawlerExecutionRpcController extends AbstractBaseRpcAdapter implements CrawlerExecutionRpcFeignClient  {

	@Autowired
	private ICrawlerExecutionApplicationService iCrawlerExecutionApplicationService;


}