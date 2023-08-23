package com.particle.dataquery.domain.dataapi.value;

import cn.hutool.json.JSONUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>
 * 数据查询服务api多接口聚合适配配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 23:53
 */
@Data
public class DataQueryDataApiMultipleAggregationAdaptConfig {



	private List<AggregationItem> aggregationItems;

	@Setter
	@Getter
	public static class AggregationItem{
		/**
		 * 聚合对象的key(聚合变量)
		 */
		private String outputKey;
		/**
		 * 数据查询数据源id
		 */
		private Long dataQueryDatasourceApiId;
		/**
		 * 数据查询数据源名称
		 */
		private String datasourceApiName;
	}

	public static DataQueryDataApiMultipleAggregationAdaptConfig createFromJsonStr(String jsonStr) {
		DataQueryDataApiMultipleAggregationAdaptConfig config = JSONUtil.toBean(jsonStr, DataQueryDataApiMultipleAggregationAdaptConfig.class);
		return config;
	}
}
