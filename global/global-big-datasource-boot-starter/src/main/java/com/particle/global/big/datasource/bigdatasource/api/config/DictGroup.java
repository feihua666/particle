package com.particle.global.big.datasource.bigdatasource.api.config;

import lombok.Data;

import java.util.List;

/**
 * <p>
 * 字典组
 * </p>
 *
 * @author yangwei
 * @since 2023-03-13 21:11
 */
@Data
public class DictGroup {

	/**
	 * 字典组描述
	 */
	private String remark;
	/**
	 * 字典项
	 */
	private List<DictItem> dictItems;
}
