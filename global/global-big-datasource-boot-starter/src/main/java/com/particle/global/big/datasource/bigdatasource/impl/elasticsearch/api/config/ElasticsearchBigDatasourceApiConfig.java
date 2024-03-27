package com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.api.config;

import cn.hutool.core.util.StrUtil;
import com.particle.global.big.datasource.bigdatasource.api.config.AbstractBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.enums.ElasticsearchBigDatasourceApiConfigDataType;
import com.particle.global.big.datasource.bigdatasource.impl.elasticsearch.enums.ElasticsearchBigDatasourceApiConfigDslTemplateType;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.script.Bindings;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * elasticsearch大数据源接口配置
 * 支持两种方式设置索引名称，分别为独立字段{@link ElasticsearchBigDatasourceApiConfig#indexNames} 和 json字段{@link ElasticsearchBigDatasourceApiConfig#dslTemplate} 添加 indexNames 属性，建议前者，因为es本身并不支持json查询时添加额外不能解析的属性
 * </p>
 *
 * @author yangwei
 * @since 2023-12-08 13:31:30
 */
@Data
public class ElasticsearchBigDatasourceApiConfig extends AbstractBigDatasourceApiConfig {

	/**
	 * elasticsearch基础配置dsl模板类型，即： dslTemplate 类型
	 */
	private ElasticsearchBigDatasourceApiConfigDslTemplateType dslTemplateType;

	/**
	 * 是否分页查询
	 */
	private ElasticsearchBigDatasourceApiConfigDataType dataType;

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


	/**
	 * 额外的数据绑定，主要用于在处理groovy脚本时，进行全局的一些脚本数据绑定
	 */
	protected Map<String, Object> extBindings;
	/**
	 * 添加一个添加indexNames方法
	 * @param indexNames
	 */
	public ElasticsearchBigDatasourceApiConfig withIndexNames(String indexNames) {
		this.indexNames = indexNames;
		return this;
	}

	public static ElasticsearchBigDatasourceApiConfig create(ElasticsearchBigDatasourceApiConfigDslTemplateType type,
															 ElasticsearchBigDatasourceApiConfigDataType dataType,
															 String indexNames,
															 String dslTemplate,
															 String dslCountTemplate) {
		ElasticsearchBigDatasourceApiConfig elasticsearchBigDatasourceApiConfig = new ElasticsearchBigDatasourceApiConfig();
		elasticsearchBigDatasourceApiConfig.setDslTemplateType(type);
		elasticsearchBigDatasourceApiConfig.setDataType(dataType);
		elasticsearchBigDatasourceApiConfig.setIndexNames(indexNames);
		elasticsearchBigDatasourceApiConfig.setDslTemplate(dslTemplate);
		elasticsearchBigDatasourceApiConfig.setDslCountTemplate(dslCountTemplate);
		return elasticsearchBigDatasourceApiConfig;
	}

	public static ElasticsearchBigDatasourceApiConfig createByWithRawType(ElasticsearchBigDatasourceApiConfigDataType dataType,
																		  String indexNames,
																		  String dslTemplate,
																		  String dslCountTemplate){
		return create(ElasticsearchBigDatasourceApiConfigDslTemplateType.raw,dataType,indexNames, dslTemplate,dslCountTemplate);
	}
	public static ElasticsearchBigDatasourceApiConfig createByWithEnjoyType(ElasticsearchBigDatasourceApiConfigDataType dataType,
																			String indexNames,
																			String dslTemplate,
																			String dslCountTemplate){
		return create(ElasticsearchBigDatasourceApiConfigDslTemplateType.enjoy_template,dataType,indexNames, dslTemplate,dslCountTemplate);
	}
	public static ElasticsearchBigDatasourceApiConfig createByWithMybatisXmlType(ElasticsearchBigDatasourceApiConfigDataType dataType,
																				 String indexNames,
																				 String dslTemplate,
																				 String dslCountTemplate){
		return create(ElasticsearchBigDatasourceApiConfigDslTemplateType.enjoy_template,dataType,indexNames, dslTemplate,dslCountTemplate);
	}


	/**
	 * 提取索引名称
	 * @return
	 */
	public List<String> obtainIndexNames() {
		if (StrUtil.isEmpty(indexNames)) {
			return Collections.emptyList();
		}
		return Arrays.stream(indexNames.split(",")).collect(Collectors.toList());
	}

	/**
	 * 返回渲染的dsl
	 * @param elasticsearchRestTemplate 支持在脚本中直接使用 elasticsearchRestTemplate 自定义查询结果，一般用于 groovy 脚本
	 * @param restHighLevelClient 支持在脚本中直接使用 restHighLevelClient 自定义查询结果，一般用于 groovy 脚本
	 * @param restClient 支持在脚本中直接使用 restClient 自定义查询结果，一般用于 groovy 脚本
	 * @param command
	 * @param queryString
	 * @return
	 */
	@SneakyThrows
	public RenderResult render(ElasticsearchRestTemplate elasticsearchRestTemplate,
							   RestHighLevelClient restHighLevelClient,
							   RestClient restClient,
							   Map<String, Object> elasticsearchBigDatasourceInstanceMap,
							   Object command,
							   String queryString){

		if(dslTemplateType == ElasticsearchBigDatasourceApiConfigDslTemplateType.groovy_script_template){

			Map<String, Object> renderMap = TemplateRenderDataWrap.create(command).toRenderMap();
			renderMap.put("elasticsearchRestTemplate", elasticsearchRestTemplate);
			renderMap.put("restHighLevelClient", restHighLevelClient);
			renderMap.put("restClient", restClient);
			renderMap.put("elasticsearchBigDatasourceInstanceMap", elasticsearchBigDatasourceInstanceMap);

			Bindings bindings = GroovyTool.createBindings();
			if (extBindings != null) {
				bindings.putAll(extBindings);
			}
			bindings.putAll(renderMap);
			Object evalResult = GroovyTool.compileAndEval(dslTemplate,bindings,true);

			// 如果返回的是字符串表示为结果为模板渲染结果，否则认为为直接返回的数据
			boolean isRenderedTemplateStr = evalResult instanceof String;
			if (isRenderedTemplateStr) {
				String countResult = null;
				if (StrUtil.isNotEmpty(dslCountTemplate)) {
					countResult = (String) GroovyTool.compileAndEval(dslCountTemplate,bindings,true);
				}
				return RenderResult.createByStrTemplateResultAndStrCountTemplateResult(((String) evalResult),countResult);

			}else {
				return RenderResult.createByResult(evalResult);

			}


		}
		if (dslTemplateType == ElasticsearchBigDatasourceApiConfigDslTemplateType.enjoy_template) {
			String result =  TemplateTool.render(dslTemplate, TemplateRenderDataWrap.create(command));
			String countResult = null;
			if (StrUtil.isNotEmpty(dslCountTemplate)) {
				countResult =  TemplateTool.render(dslCountTemplate, TemplateRenderDataWrap.create(command));
			}
			return RenderResult.createByStrTemplateResultAndStrCountTemplateResult(result,countResult);
		}
		if (dslTemplateType == ElasticsearchBigDatasourceApiConfigDslTemplateType.raw) {
			return RenderResult.createByStrTemplateResultAndStrCountTemplateResult(dslTemplate,dslCountTemplate);
		}

		throw new BigDatasourceException("not support render for type " + dslTemplateType.name());
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
		 * 最低优先级的结果,该结果应该能被解析为json
		 * 比原来的查询多了一个 indexNames,同理 {@link RenderResult#strCountTemplateResult}保持一样
		 * 例：{
		 * 		"indexNames"["test_index"]:
		 *     "query":
		 *     {
		 *         "match":
		 *         {
		 *             "title": "测试"
		 *         }
		 *     }
		 * }
		 */
		private String strTemplateResult;
		/**
		 * 最低优先级的计数结果,该结果应该能被解析为json
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
