package com.particle.generator.domain;

import com.particle.common.domain.AggreateRoot;
import com.particle.generator.domain.component.ComponentGenerateConf;
import com.particle.generator.domain.component.TableGenerateConf;
import com.particle.global.domain.DomainFactory;
import com.particle.global.domain.Entity;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 生成器领域模型
 * </p>
 *
 * @author yangwei
 * @since 2022-06-30 18:22
 */
@Data
@Entity
public class Generator extends AggreateRoot {

	/**
	 * 生成组件时需要的配置
	 */
	private ComponentGenerateConf componentGenerateConf;
	/**
	 * 根据表生成代码时需要的配置
	 */
	private List<TableGenerateConf> tableGenerateConfs;

	/**
	 * 生成组件使用
	 */
	public static Generator create(ComponentGenerateConf componentGenerateConf){
		Generator generator = DomainFactory.create(Generator.class);
		generator.componentGenerateConf = componentGenerateConf;
		return generator;
	}

	/**
	 * 生成表使用
	 * @param componentGenerateConf
	 * @param tableGenerateConfs
	 * @return
	 */
	public static Generator create(ComponentGenerateConf componentGenerateConf,List<TableGenerateConf> tableGenerateConfs){
		Generator generator = DomainFactory.create(Generator.class);
		generator.componentGenerateConf = componentGenerateConf;
		generator.tableGenerateConfs = tableGenerateConfs;

		return generator;
	}
}
