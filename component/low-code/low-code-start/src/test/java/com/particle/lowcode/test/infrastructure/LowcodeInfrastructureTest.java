package com.particle.lowcode.test.infrastructure;

import cn.hutool.core.io.FileUtil;
import com.particle.dict.adapter.feign.client.rpc.DictRpcFeignClient;
import com.particle.global.test.InfrastructureTest;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentTemplateDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

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

	@Test
	public void loadComponenttemplateToSegmentTemplateTest(){
		String path = "/Users/yw/fh/git-source/particle/component/componenttemplate";

		File file = FileUtil.file(path);

	}

	private LowcodeSegmentTemplateDO fileToLowcodeSegmentTemplateDO(File file,LowcodeSegmentTemplateDO parent) {
		Long dirDictId = 1625787699807485954L;
		Long FileDictId = 1625787619817914370L;
		LowcodeSegmentTemplateDO lowcodeSegmentTemplateDO = new LowcodeSegmentTemplateDO();
		lowcodeSegmentTemplateDO.setName(file.getName());
		if (file.isDirectory()) {
			lowcodeSegmentTemplateDO.setOutputTypeDictId(dirDictId);
		}
		if (file.isFile()) {
			lowcodeSegmentTemplateDO.setOutputTypeDictId(FileDictId);
		}

		return lowcodeSegmentTemplateDO;
	}
}
