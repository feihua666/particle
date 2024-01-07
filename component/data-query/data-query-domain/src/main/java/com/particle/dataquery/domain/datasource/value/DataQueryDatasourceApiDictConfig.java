package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.global.dto.basic.Value;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 出参字典配置
 * </p>
 *
 * @author yangwei
 * @since 2024-01-07 16:45:25
 */
@Data
public class DataQueryDatasourceApiDictConfig extends Value {


	/**
	 * 字典配置信息
	 */
	private List<DictItem> dictItems;

	public static DataQueryDatasourceApiDictConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiDictConfig config = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiDictConfig.class);
		return config;
	}


	@Data
	public static class DictItem {
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
		 * 是否为字典组
		 */
		private Boolean isGroup;

		/**
		 * 父级id
		 */
		private String parentId;

		/**
		 * 子线为字典项
		 */
		private List<DictItem> children;

		public static DictItem create(String id,
									  String name,
									  String value,
									  Boolean isGroup,
									  String parentId,
									  List<DictItem> children) {
			DictItem dictItem = new DictItem();
			dictItem.id = id;
			dictItem.name = name;
			dictItem.value = value;
			dictItem.isGroup = isGroup;
			dictItem.parentId = parentId;
			dictItem.children = children;

			return dictItem;
		}
	}
}
