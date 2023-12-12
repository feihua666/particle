package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiEsBasicConfigDataType;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiEsBasicConfigDslTemplateType;
import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 * es 数据源接口基础配置
 * 该配置大致与{@link com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.api.config.ElasticsearchBigDatasourceApiConfig} 相匹配
 * </p>
 *
 * @author yangwei
 * @since 2023-12-12 12:52:32
 */
@Data
public class DataQueryDatasourceApiEsBasicConfig extends Value {
	/**
	 * 类型
	 */
	private DataQueryDatasourceApiEsBasicConfigDslTemplateType dslTemplateType;

	private DataQueryDatasourceApiEsBasicConfigDataType dataType;
	/**
	 * 索引名称，多个以逗号分隔
	 */
	private String indexNames;

	/**
	 * dsl语句模板
	 * DSL（Domain Specific Language），一种特定领域的查询语言，用于构建复杂的查询和聚合操作
	 * DSL语法具有JSON格式，因此它非常易于阅读和编写
	 */
	private String dslTemplate;
	/**
	 * dsl count 语句模板，用于在分页时计算总数，允许不填写，如果在分页时不填写，则默认总数为0
	 */
	private String dslCountTemplate;

	public static DataQueryDatasourceApiEsBasicConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiEsBasicConfig dataQueryDatasourceApiEsBasicConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiEsBasicConfig.class);
		return dataQueryDatasourceApiEsBasicConfig;
	}

	public static DataQueryDatasourceApiEsBasicConfig create(DataQueryDatasourceApiEsBasicConfigDslTemplateType type,
															 DataQueryDatasourceApiEsBasicConfigDataType dataType,
															 String indexNames,
															 String dslTemplate,
															 String dslCountTemplate) {
		DataQueryDatasourceApiEsBasicConfig dataQueryDatasourceApiEsBasicConfig = new DataQueryDatasourceApiEsBasicConfig();
		dataQueryDatasourceApiEsBasicConfig.setDslTemplateType(type);
		dataQueryDatasourceApiEsBasicConfig.setDataType(dataType);
		dataQueryDatasourceApiEsBasicConfig.setIndexNames(indexNames);
		dataQueryDatasourceApiEsBasicConfig.setDslTemplate(dslTemplate);
		dataQueryDatasourceApiEsBasicConfig.setDslCountTemplate(dslCountTemplate);
		return dataQueryDatasourceApiEsBasicConfig;
	}
}
