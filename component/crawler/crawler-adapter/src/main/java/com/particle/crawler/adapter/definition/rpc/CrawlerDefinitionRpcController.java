package com.particle.crawler.adapter.definition.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crawler.client.definition.api.ICrawlerDefinitionApplicationService;
import com.particle.crawler.adapter.feign.client.definition.rpc.CrawlerDefinitionRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫定义远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:22:37
 */
@Tag(name = "爬虫定义远程调用相关接口")
@RestController
@RequestMapping("/rpc/crawler_definition")
public class CrawlerDefinitionRpcController extends AbstractBaseRpcAdapter implements CrawlerDefinitionRpcFeignClient  {

	@Autowired
	private ICrawlerDefinitionApplicationService iCrawlerDefinitionApplicationService;


}