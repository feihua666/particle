package com.particle.generator.infrastructure.generator.table;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.particle.generator.domain.component.TableGenerateConf;

/**
 * <p>
 * 配配置逻辑
 * </p>
 *
 * @author yangwei
 * @since 2022-07-07 14:12
 */
public class GlobalConfigLogic {

	public static void config(GlobalConfig.Builder builder, TableGenerateConf tableGenerateConf) {
		builder.author(tableGenerateConf.getAuthor()) // 设置作者
				.enableSwagger() // 开启 swagger 模式
				.disableOpenDir() // 禁止打开输出目录
				.outputDir(tableGenerateConf.getOutputAbsoluteDir()); // 指定输出目录
		if (tableGenerateConf.getFileOverride()) {
			builder.fileOverride(); // 覆盖已生成文件
		}
	}
}
