package com.particle.generator.domain.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.generator.domain.Generator;
import com.particle.generator.domain.component.ComponentGenerateConf;

/**
 * <p>
 * 代码生成器网关
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 23:32
 */
public interface GeneratorGateway extends IGateway {
	/**
	 * 组件生成
	 * @param componentGenerateConf
	 * @return
	 */
	boolean componentGenerate( ComponentGenerateConf componentGenerateConf);

	/**
	 * 表生成
	 * @param generator
	 * @return
	 */
	boolean tableGenerate(Generator generator);
}
