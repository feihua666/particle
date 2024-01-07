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
	 * 字典id
	 */
	private String id;
	/**
	 * 字典名称
	 */
	private String code;
	/**
	 * 字典名称
	 */
	private String name;
	/**
	 * 字典项
	 */
	private List<DictItem> dictItems;

	public static DictGroup create(String id, String name,String code, List<DictItem> dictItems) {
		DictGroup dictGroup = new DictGroup();
		dictGroup.id = id;
		dictGroup.name = name;
		dictGroup.code = code;
		dictGroup.dictItems = dictItems;
		return dictGroup;
	}
}
