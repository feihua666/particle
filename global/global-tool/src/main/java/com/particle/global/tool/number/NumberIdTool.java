package com.particle.global.tool.number;

/**
 * <p>
 * 数字id工具
 * </p>
 *
 * @author yangwei
 * @since 2022-08-11 14:55
 */
public class NumberIdTool {

	/**
	 * id是否有效，一般用于数据库字段检查id是否存在
	 * @param id
	 * @return
	 */
	public boolean isValid(Long id) {
		return id != null && id > 0;
	}
}
