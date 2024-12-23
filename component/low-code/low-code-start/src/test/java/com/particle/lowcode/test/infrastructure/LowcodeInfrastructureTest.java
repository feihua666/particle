package com.particle.lowcode.test.infrastructure;

import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.global.test.InfrastructureTest;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <p>
 * 基础设施测试类
 * </p>
 *
 * @author yw
 * @since 2023-01-03 13:17:50
 */
@SpringBootTest
public class LowcodeInfrastructureTest extends InfrastructureTest {

	@Autowired
	private ILowcodeSegmentTemplateService iLowcodeSegmentTemplateService;

	@Autowired
	private DictRpcFeignClient dictRpcFeignClient;

}
