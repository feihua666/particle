package com.particle.generator.infrastructure.generator.table;

import com.particle.generator.domain.component.TableGenerateConf;

/**
 * <p>
 * 模板配置逻辑
 * </p>
 *
 * @author yangwei
 * @since 2022-07-07 14:08
 */
public class TemplateConfigLogic {

	public static void config(com.baomidou.mybatisplus.generator.config.TemplateConfig.Builder builder, TableGenerateConf tableGenerateConf) {
		// 禁用所有的
		builder.disable();
	}

}
