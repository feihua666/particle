package com.particle.generator.infrastructure.generator.table;

import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.particle.generator.domain.component.TableGenerateConf;

/**
 * <p>
 * 包配置逻辑
 * </p>
 *
 * @author yangwei
 * @since 2022-07-07 14:14
 */
public class PackageConfigLogic {

	public static void config(PackageConfig.Builder builder, TableGenerateConf tableGenerateConf) {
		builder.parent(tableGenerateConf.getPackageParent()) // 设置父包名
				.moduleName(tableGenerateConf.getPackageModuleName()) // 设置父包模块名
				// do 是关键字不能出现在包名中
				.entity("dos")
				.controller("");
	}
}
