package com.particle.global.big.datasource.bigdatasource.impl.neo4j.api.config;

import cn.hutool.core.util.StrUtil;
import com.particle.global.big.datasource.bigdatasource.api.config.AbstractBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.enums.Neo4jBigDatasourceApiConfigCqlTemplateType;
import com.particle.global.big.datasource.bigdatasource.impl.neo4j.enums.Neo4jBigDatasourceApiConfigDataType;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.neo4j.driver.Driver;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;

import javax.script.Bindings;
import java.util.Map;

/**
 * <p>
 * neo4j大数据源接口配置
 * </p>
 *
 * @author yangwei
 * @since 2023-11-21 11:20:54
 */
@Data
public class Neo4jBigDatasourceApiConfig extends AbstractBigDatasourceApiConfig {

	/**
	 * neo4j基础配置cql模板类型，即： cqlTemplate 类型
	 */
	private Neo4jBigDatasourceApiConfigCqlTemplateType cqlTemplateType;

	/**
	 * 是否分页查询
	 */
	private Neo4jBigDatasourceApiConfigDataType dataType;

	/**
	 * cql语句模板
	 */
	private String cqlTemplate;
	/**
	 * cql count 语句模板，用于在分页时计算总数，允许不填写，如果在分页时不填写，则默认总数为0
	 */
	private String cqlCountTemplate;

	/**
	 * 额外的数据绑定，主要用于在处理groovy脚本时，进行全局的一些脚本数据绑定
	 */
	protected Map<String, Object> extBindings;
	public static Neo4jBigDatasourceApiConfig create(Neo4jBigDatasourceApiConfigCqlTemplateType type, Neo4jBigDatasourceApiConfigDataType dataType, String cqlTemplate,String cqlCountTemplate) {
		Neo4jBigDatasourceApiConfig neo4jBigDatasourceApiConfig = new Neo4jBigDatasourceApiConfig();
		neo4jBigDatasourceApiConfig.setCqlTemplateType(type);
		neo4jBigDatasourceApiConfig.setDataType(dataType);
		neo4jBigDatasourceApiConfig.setCqlTemplate(cqlTemplate);
		neo4jBigDatasourceApiConfig.setCqlCountTemplate(cqlCountTemplate);
		return neo4jBigDatasourceApiConfig;
	}

	public static Neo4jBigDatasourceApiConfig createByWithRawType(Neo4jBigDatasourceApiConfigDataType dataType, String cqlTemplate,String cqlCountTemplate){
		return create(Neo4jBigDatasourceApiConfigCqlTemplateType.raw,dataType, cqlTemplate,cqlCountTemplate);
	}
	public static Neo4jBigDatasourceApiConfig createByWithEnjoyType(Neo4jBigDatasourceApiConfigDataType dataType, String cqlTemplate,String cqlCountTemplate){
		return create(Neo4jBigDatasourceApiConfigCqlTemplateType.enjoy_template,dataType, cqlTemplate,cqlCountTemplate);
	}
	public static Neo4jBigDatasourceApiConfig createByWithMybatisXmlType(Neo4jBigDatasourceApiConfigDataType dataType, String cqlTemplate,String cqlCountTemplate){
		return create(Neo4jBigDatasourceApiConfigCqlTemplateType.enjoy_template,dataType, cqlTemplate,cqlCountTemplate);
	}

	/**
	 * 返回渲染的cql
	 * @param driver 支持在脚本中直接使用 driver 自定义查询结果，一般用于 groovy 脚本
	 * @param neo4jClient 支持在脚本中直接使用 neo4jClient 自定义查询结果，一般用于 groovy 脚本
	 * @param neo4jTemplate 支持在脚本中直接使用 neo4jTemplate 自定义查询结果，一般用于 groovy 脚本
	 * @param command
	 * @param queryString
	 * @return
	 */
	@SneakyThrows
	public RenderResult render(Driver driver,
							   Neo4jClient neo4jClient,
							   Neo4jTemplate neo4jTemplate,
							   Map<String, Object> neo4jBigDatasourceInstanceMap,
							   Object command,
							   String queryString){

		if(cqlTemplateType == Neo4jBigDatasourceApiConfigCqlTemplateType.groovy_script_template){

			Map<String, Object> renderMap = TemplateRenderDataWrap.create(command).toRenderMap();
			renderMap.put("driver", driver);
			renderMap.put("neo4jClient", neo4jClient);
			renderMap.put("neo4jTemplate", neo4jTemplate);
			renderMap.put("neo4jBigDatasourceInstanceMap", neo4jBigDatasourceInstanceMap);

			Bindings bindings = GroovyTool.createBindings();
			if (extBindings != null) {
				bindings.putAll(extBindings);
			}
			bindings.putAll(renderMap);
			Object evalResult = GroovyTool.compileAndEval(cqlTemplate,bindings,true);

			// 如果返回的是字符串表示为结果为模板渲染结果，否则认为为直接返回的数据
			boolean isRenderedTemplateStr = evalResult instanceof String;
			if (isRenderedTemplateStr) {
				String countResult = null;
				if (StrUtil.isNotEmpty(cqlCountTemplate)) {
					countResult = (String) GroovyTool.compileAndEval(cqlCountTemplate,bindings,true);
				}
				return RenderResult.createByStrTemplateResultAndStrCountTemplateResult(((String) evalResult),countResult);

			}else {
				return RenderResult.createByResult(evalResult);

			}


		}
		if (cqlTemplateType == Neo4jBigDatasourceApiConfigCqlTemplateType.enjoy_template) {
			String result =  TemplateTool.render(cqlTemplate, TemplateRenderDataWrap.create(command));
			String countResult = null;
			if (StrUtil.isNotEmpty(cqlCountTemplate)) {
				countResult =  TemplateTool.render(cqlCountTemplate, TemplateRenderDataWrap.create(command));
			}
			return RenderResult.createByStrTemplateResultAndStrCountTemplateResult(result,countResult);
		}
		if (cqlTemplateType == Neo4jBigDatasourceApiConfigCqlTemplateType.raw) {
			return RenderResult.createByStrTemplateResultAndStrCountTemplateResult(cqlTemplate,cqlCountTemplate);
		}

		throw new BigDatasourceException("not support render for type " + cqlTemplateType.name());
	}

	/**
	 * 渲染结果
	 */
	@Data
	@Builder
	public static class RenderResult{

		/**
		 * 由脚本直接返回的结果就直接用
		 */
		Object result;
		/**
		 * 最低优先级的结果
		 */
		private String strTemplateResult;
		/**
		 * 最低优先级的计数结果
		 */
		private String strCountTemplateResult;

		public static RenderResult createByResult(Object result) {
			return RenderResult.builder().result(result).build();
		}
		public static RenderResult createByStrTemplateResultAndStrCountTemplateResult(String strTemplateResult,String strCountTemplateResult) {
			return RenderResult.builder()
					.strTemplateResult(strTemplateResult)
					.strCountTemplateResult(strCountTemplateResult)
					.build();
		}
	}
}
