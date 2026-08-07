package com.particle.crawler.adapter.execution.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreApplicationService;
import com.particle.crawler.adapter.feign.client.execution.rpc.CrawlerRawStoreRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫原始数据存储远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:23:47
 */
@Tag(name = "爬虫原始数据存储远程调用相关接口")
@RestController
@RequestMapping("/rpc/crawler_raw_store")
public class CrawlerRawStoreRpcController extends AbstractBaseRpcAdapter implements CrawlerRawStoreRpcFeignClient  {

	@Autowired
	private ICrawlerRawStoreApplicationService iCrawlerRawStoreApplicationService;


}