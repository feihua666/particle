package com.particle.global.big.datasource.bigdatasource.api.config;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

/**
 * <p>
 * 字典项
 * </p>
 *
 * @author yangwei
 * @since 2023-03-13 14:32
 */
@Data
public class DictItem extends DTO {

	/**
	 * 字典id
	 */
	private String id;
	/**
	 * 字典名称
	 */
	private String name;
	/**
	 * 字典值
	 */
	private String value;

	/**
	 * 字典单位
	 */
	private String unit;


	public static DictItem create(String id,
								  String name,
								  String value,
								  String unit) {
		DictItem dictItem = new DictItem();
		dictItem.id = id;
		dictItem.name = name;
		dictItem.value = value;
		dictItem.unit = unit;

		return dictItem;
	}
}

