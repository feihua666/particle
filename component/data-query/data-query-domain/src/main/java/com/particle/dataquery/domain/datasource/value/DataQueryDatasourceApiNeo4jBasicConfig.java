package com.particle.dataquery.domain.datasource.value;

import cn.hutool.json.JSONUtil;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiNeo4jBasicConfigCqlTemplateType;
import com.particle.dataquery.domain.datasource.enums.DataQueryDatasourceApiNeo4jBasicConfigDataType;
import com.particle.global.dto.basic.Value;
import lombok.Data;

/**
 * <p>
 * neo4j 数据源接口基础配置
 * 该配置大致与{@link com.particle.global.big.datasource.bigdatasource.impl.neo4j.api.config.Neo4jBigDatasourceApiConfig} 相匹配
 * </p>
 *
 * @author yangwei
 * @since 2023-11-22 10:43:20
 */
@Data
public class DataQueryDatasourceApiNeo4jBasicConfig extends Value {
	/**
	 * 类型
	 */
	private DataQueryDatasourceApiNeo4jBasicConfigCqlTemplateType cqlTemplateType;

	private DataQueryDatasourceApiNeo4jBasicConfigDataType dataType;
	/**
	 * 内容
	 */
	private String cqlTemplate;

	/**
	 * cql count 语句模板，用于在分页时计算总数，允许不填写，如果在分页时不填写，则默认总数为0
	 */
	private String cqlCountTemplate;

	public static DataQueryDatasourceApiNeo4jBasicConfig createFromJsonStr(String jsonStr) {
		DataQueryDatasourceApiNeo4jBasicConfig dataQueryDatasourceApiNeo4jBasicConfig = JSONUtil.toBean(jsonStr, DataQueryDatasourceApiNeo4jBasicConfig.class);
		return dataQueryDatasourceApiNeo4jBasicConfig;
	}

	public static DataQueryDatasourceApiNeo4jBasicConfig create(DataQueryDatasourceApiNeo4jBasicConfigCqlTemplateType type, DataQueryDatasourceApiNeo4jBasicConfigDataType dataType, String cqlTemplate,String cqlCountTemplate) {
		DataQueryDatasourceApiNeo4jBasicConfig dataQueryDatasourceApiNeo4jBasicConfig = new DataQueryDatasourceApiNeo4jBasicConfig();
		dataQueryDatasourceApiNeo4jBasicConfig.setCqlTemplateType(type);
		dataQueryDatasourceApiNeo4jBasicConfig.setDataType(dataType);
		dataQueryDatasourceApiNeo4jBasicConfig.setCqlTemplate(cqlTemplate);
		dataQueryDatasourceApiNeo4jBasicConfig.setCqlCountTemplate(cqlCountTemplate);
		return dataQueryDatasourceApiNeo4jBasicConfig;
	}
}
