package com.particle.dataquery.domain.datasource.value;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiParamValidateType;
import com.particle.global.dto.basic.Value;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 出参翻译配置
 * 和{@link com.particle.global.big.datasource.bigdatasource.api.config.BigDatasourceApiTransConfig} 基本保持一致
 * </p>
 *
 * @author yangwei
 * @since 2024-01-07 16:45:25
 */
@Data
public class DataQueryDatasourceApiTransConfig extends Value {


	/**
	 * 翻译配置信息
	 */
	private List<TransItem> transItems;

	public static DataQueryDatasourceApiTransConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiTransConfig config = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiTransConfig.class);
		return config;
	}
	/**
	 * 翻译的配置项
	 * 和 {@link com.particle.global.light.share.trans.anno.TransItem} 基本一致
	 */
	@Data
	public static class TransItem{
		/**
		 * 类型,翻译实现支持的类型
		 */
		private String type;
		/**
		 * 需要翻译的字段名称
		 */
		private String byFieldName;
		/**
		 * 需要被翻译的字段名称
		 */
		private String forFieldName;
		/**
		 * 当翻译结果是一个对象时，可以使用该字段取对象的一个属性值
		 */
		private String mapValueField;
		/**
		 * 如果是集合是否转为字符串拼接，仅支持字符串字段
		 */
		private Boolean isJoin;
		/**
		 * 当翻译结果是一个集合时，可以使用的分隔符
		 */
		private String mapJoinSeparator;
		/**
		 * 当值存在时不翻译
		 */
		private Boolean isNotTransWhenExist;

		/**
		 * 因为该翻译配置仅支持以字典配置为数据，所以这里添加加两个字段以提示字典数据，和翻译结果对应的key字段为唯一标识
		 * 使用的结果key作为唯一标识
		 */
		private String mapKeyField;
		/**
		 * 字典组编码,仅限字典配置，并不是字典管理模块
		 */
		private String dicGroupCode;

		public static TransItem create(String type,
									   String byFieldName,
									   String forFieldName,
									   String mapValueField,
									   Boolean isJoin,
									   String mapJoinSeparator,
									   Boolean isNotTransWhenExist,
									   String mapKeyField,
									   String dicGroupCode){
			TransItem transItem = new TransItem();

			transItem.type = type;
			transItem.byFieldName = byFieldName;
			transItem.forFieldName = forFieldName;
			transItem.mapValueField = mapValueField;
			transItem.isJoin = isJoin;
			transItem.mapJoinSeparator =  mapJoinSeparator;
			transItem.isNotTransWhenExist = isNotTransWhenExist;
			transItem.mapKeyField = mapKeyField;
			transItem.dicGroupCode = dicGroupCode;
			return transItem;
		}
	}

}
