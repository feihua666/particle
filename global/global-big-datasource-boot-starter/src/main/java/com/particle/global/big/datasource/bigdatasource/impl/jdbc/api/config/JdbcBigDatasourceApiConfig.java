package com.particle.global.big.datasource.bigdatasource.impl.jdbc.api.config;

import com.particle.global.big.datasource.bigdatasource.api.config.AbstractBigDatasourceApiConfig;
import com.particle.global.big.datasource.bigdatasource.exception.BigDatasourceException;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigDataType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.enums.JdbcBigDatasourceApiConfigSqlTemplateType;
import com.particle.global.big.datasource.bigdatasource.impl.jdbc.service.IJdbcService;
import com.particle.global.mybatis.plus.mybatis.DynamicSqlSourceHelperTool;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;

import javax.script.Bindings;
import java.util.Map;

/**
 * <p>
 * jdbc大数据源接口配置
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 19:11
 */
@Data
public class JdbcBigDatasourceApiConfig extends AbstractBigDatasourceApiConfig {

	/**
	 * jdbc基础配置sql模板类型，即： sqlTemplate 类型
	 */
	private JdbcBigDatasourceApiConfigSqlTemplateType sqlTemplateType;

	/**
	 * 是否分页查询
	 */
	private JdbcBigDatasourceApiConfigDataType dataType;

	/**
	 * sql语句模板
	 */
	private String sqlTemplate;


	public static JdbcBigDatasourceApiConfig create(JdbcBigDatasourceApiConfigSqlTemplateType type, JdbcBigDatasourceApiConfigDataType dataType, String sqlTemplate) {
		JdbcBigDatasourceApiConfig jdbcBigDatasourceApiConfig = new JdbcBigDatasourceApiConfig();
		jdbcBigDatasourceApiConfig.setSqlTemplateType(type);
		jdbcBigDatasourceApiConfig.setDataType(dataType);
		jdbcBigDatasourceApiConfig.setSqlTemplate(sqlTemplate);
		return jdbcBigDatasourceApiConfig;
	}

	public static JdbcBigDatasourceApiConfig createByWithRawType(JdbcBigDatasourceApiConfigDataType dataType,String sqlTemplate){
		return create(JdbcBigDatasourceApiConfigSqlTemplateType.raw,dataType, sqlTemplate);
	}
	public static JdbcBigDatasourceApiConfig createByWithEnjoyType(JdbcBigDatasourceApiConfigDataType dataType,String sqlTemplate){
		return create(JdbcBigDatasourceApiConfigSqlTemplateType.enjoy_template,dataType, sqlTemplate);
	}
	public static JdbcBigDatasourceApiConfig createByWithMybatisXmlType(JdbcBigDatasourceApiConfigDataType dataType,String sqlTemplate){
		return create(JdbcBigDatasourceApiConfigSqlTemplateType.enjoy_template,dataType, sqlTemplate);
	}

	/**
	 * 返回渲染的sql
	 * @param jdbcService 支持动态渲染结果，一般用于groovy脚本，在一定条件下，可以不使用sql模板，而直接在脚本中获取结果
	 * @param command
	 * @param queryString
	 * @return
	 */
	@SneakyThrows
	public RenderResult render(IJdbcService jdbcService, Object command,String queryString){

		if(sqlTemplateType == JdbcBigDatasourceApiConfigSqlTemplateType.groovy_script_template){

			Map<String, Object> renderMap = TemplateRenderDataWrap.create(command).toRenderMap();
			renderMap.put("jdbcService", jdbcService);
			Bindings bindings = GroovyTool.createBindings();
			bindings.putAll(renderMap);
			Object evalResult = GroovyTool.compileAndEval(sqlTemplate,bindings,true);
			// 如果返回的是字符串表示为结果为模板渲染结果，否则认为为直接返回的数据
			boolean isRenderedTemplateStr = evalResult instanceof String;
			if (isRenderedTemplateStr) {
				return RenderResult.createByStrTemplateResult(((String) evalResult));
			}else {
				return RenderResult.createByResult(evalResult);
			}
		}
		if (sqlTemplateType == JdbcBigDatasourceApiConfigSqlTemplateType.enjoy_template) {
			String result =  TemplateTool.render(sqlTemplate, TemplateRenderDataWrap.create(command));
			return RenderResult.createByStrTemplateResult(result);
		}
		/**
		 * 原生支持，无需渲染 得益于 {@link DynamicSqlSourceHelperTool}
		 */
		if (sqlTemplateType == JdbcBigDatasourceApiConfigSqlTemplateType.mybatis_script_template) {
			return RenderResult.createByStrTemplateResult(sqlTemplate);
		}
		if (sqlTemplateType == JdbcBigDatasourceApiConfigSqlTemplateType.raw) {
			return RenderResult.createByStrTemplateResult(sqlTemplate);
		}

		throw new BigDatasourceException("not support render for type " + sqlTemplateType.name());
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

		public static RenderResult createByResult(Object result) {
			return RenderResult.builder().result(result).build();
		}
		public static RenderResult createByStrTemplateResult(String strTemplateResult) {
			return RenderResult.builder().strTemplateResult(strTemplateResult).build();
		}
	}
}
