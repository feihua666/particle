package com.particle.global.tool.template.templatetreerenderengine.config;

import cn.hutool.core.date.DateUtil;
import lombok.Data;

import java.io.File;

/**
 * <p>
 * 配置内置系统数据
 * </p>
 *
 * @author yangwei
 * @since 2023-02-10 16:36
 */
@Data
public class SystemData {


	/**
	 * 当前日期
	 */
	private String currentDate = DateUtil.today();
	/**
	 * 当前日期时间
	 */
	private String currentDateTime = DateUtil.now();

	/**
	 * 文件系统分隔符
	 */
	private String fileSeparator = File.separator;

}
