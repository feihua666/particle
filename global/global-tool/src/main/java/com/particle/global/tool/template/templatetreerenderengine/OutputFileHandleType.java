package com.particle.global.tool.template.templatetreerenderengine;

/**
 * <p>
 * 代码版本模板输出类型为文件时 {@link OutputType#FILE}，的处理方式
 * </p>
 *
 * @author yangwei
 * @since 2023-01-06
 */
public enum OutputFileHandleType {
	/**
	 * 无动作，不输出文件，如果文件已存在，也不删除或覆盖
	 */
	NOACTION,
	/**
	 * 覆盖，内容覆盖
	 */
	OVERRIDE,
	/**
	 * 内容追加
	 */
	APPEND,
	/**
	 * 删除
	 */
	DELETE;

}
