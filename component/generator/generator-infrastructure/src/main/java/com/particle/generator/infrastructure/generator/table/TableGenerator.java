package com.particle.generator.infrastructure.generator.table;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.particle.generator.domain.component.TableGenerateConf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 根据表的代码生成器
 * </p>
 *
 * @author yangwei
 * @since 2022-07-05 23:41
 */
@Slf4j
@Component
public class TableGenerator {

	public boolean tableGenerate(TableGenerateConf tableGenerateConf) {
		return doTableGenerate(tableGenerateConf);
	}
	private boolean doTableGenerate(TableGenerateConf tableGenerateConf) {
		FastAutoGenerator.create(tableGenerateConf.getDatasourceConf().getUrl(),
				tableGenerateConf.getDatasourceConf().getUsername(),
				tableGenerateConf.getDatasourceConf().getPassword())
				.globalConfig(builder -> {
					GlobalConfigLogic.config(builder,tableGenerateConf);
				})
				.packageConfig(builder -> {
					PackageConfigLogic.config(builder,tableGenerateConf);
				})
				.templateConfig(builder -> {
					TemplateConfigLogic.config(builder,tableGenerateConf);
				})
				.injectionConfig(builder -> {
					InjectionConfigLogic.config(builder,tableGenerateConf);
				})
				.strategyConfig(builder -> {
					StrategyConfigLogic.config(builder,tableGenerateConf);
				})
				// 使用Freemarker引擎模板，默认的是Velocity引擎模板
				.templateEngine(new EnhanceFreemarkerTemplateEngine())
				.execute();
		return true;
	}
}
