package com.particle.crawler.adapter.execution.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.crawler.client.execution.api.ICrawlerRawStoreContentApplicationService;
import com.particle.crawler.adapter.feign.client.execution.rpc.CrawlerRawStoreContentRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 爬虫原始数据存储内容远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2026-05-11 11:24:02
 */
@Tag(name = "爬虫原始数据存储内容远程调用相关接口")
@RestController
@RequestMapping("/rpc/crawler_raw_store_content")
public class CrawlerRawStoreContentRpcController extends AbstractBaseRpcAdapter implements CrawlerRawStoreContentRpcFeignClient  {

	@Autowired
	private ICrawlerRawStoreContentApplicationService iCrawlerRawStoreContentApplicationService;


}