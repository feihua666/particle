package com.particle.generator.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractGatewayImpl;
import com.particle.generator.domain.Generator;
import com.particle.generator.domain.component.ComponentGenerateConf;
import com.particle.generator.domain.component.TableGenerateConf;
import com.particle.generator.domain.gateway.GeneratorGateway;
import com.particle.generator.infrastructure.generator.component.ComponentGenerator;
import com.particle.generator.infrastructure.generator.table.TableGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 代码生成器网关实现类
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 23:39
 */
@Component
public class GeneratorGatewayImpl extends AbstractGatewayImpl implements GeneratorGateway {

	private ComponentGenerator componentGenerator;
	private TableGenerator tableGenerator;
	@Override
	public boolean componentGenerate( ComponentGenerateConf componentGenerateConf) {
		return componentGenerator.componentGenerate(componentGenerateConf);
	}

	@Override
	public boolean tableGenerate(Generator generator) {
		for (TableGenerateConf tableGenerateConf : generator.getTableGenerateConfs()) {

			tableGenerateConf.overrideByComponentGenerateConf(generator.getComponentGenerateConf());
			tableGenerator.tableGenerate(tableGenerateConf);
		}
		return true;
	}


	@Autowired
	public void setComponentGenerator(ComponentGenerator componentGenerator) {
		this.componentGenerator = componentGenerator;
	}

	@Autowired
	public void setTableGenerator(TableGenerator tableGenerator) {
		this.tableGenerator = tableGenerator;
	}
}
