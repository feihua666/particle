package com.particle.global.mybatis.plus.mybatis;

import cn.hutool.core.lang.SimpleCache;
import cn.hutool.core.util.StrUtil;
import com.particle.global.mybatis.plus.mapper.NativeSqlMapper;
import org.apache.ibatis.binding.BindingException;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.scripting.xmltags.MixedSqlNode;
import org.apache.ibatis.scripting.xmltags.XMLScriptBuilder;
import org.apache.ibatis.session.Configuration;

import java.util.Map;

/**
 * <p>
 * 动态sql帮助判断工具
 * 可以支持纯 xml动态传参，动态解析
 * </p>
 *
 * @author yangwei
 * @since 2023-03-20 15:49
 */
public class DynamicSqlSourceHelperTool {

	private static final SimpleCache<String, MixedSqlNode> MIXEDSQLNODE_CACHE = new SimpleCache<>();


	/**
	 * 是否为动态脚本
	 * @param script
	 * @return
	 */
	private static boolean isDynamic(String script) {
		if (script.startsWith("<script>")) {
			return true;
		}
		return false;
	}

	/**
	 * 动态sql根节点
	 * @param script
	 * @param configuration
	 * @return
	 */
	public static MixedSqlNode rootSqlNode(String script, Configuration configuration){
		if (!isDynamic(script) || StrUtil.isEmpty(script)) {
			return null;
		}
		MixedSqlNode mixedSqlNodeCached = MIXEDSQLNODE_CACHE.get(script);
		if (mixedSqlNodeCached != null) {
			return mixedSqlNodeCached;
		}
		XPathParser parser = new XPathParser(script, false, configuration.getVariables(), new XMLMapperEntityResolver());

		CustomXMLScriptBuilder builder = new CustomXMLScriptBuilder(configuration,  parser.evalNode("/script"), null);

		MixedSqlNode mixedSqlNode = builder.publicParseDynamicTags(parser.evalNode("/script"));
		MIXEDSQLNODE_CACHE.put(script, mixedSqlNode);
		return mixedSqlNode;
	}


	/**
	 * 直接根据参数判断提取
	 * @param configuration
	 * @param parameterObject
	 * @return
	 */
	public static MixedSqlNode rootSqlNode(Configuration configuration,Object parameterObject){
		if (parameterObject instanceof Map) {
			Object script = null;
			try {
				script = ((Map<?, ?>) parameterObject).get(NativeSqlMapper.paramSQL);
			} catch (BindingException e) {
				/**
				 * 消息来源 {@link MapperMethod.ParamMap#get(java.lang.Object)}
				 */
				String exceptionMessage = "Parameter '" + NativeSqlMapper.paramSQL + "' not found. Available parameters are";
				if (e.getMessage() != null && e.getMessage().contains(exceptionMessage)) {
					// 预料之中异常，不处理
				}else {
					throw e;
				}
			}
			if (script != null && script instanceof String) {
				return rootSqlNode(script.toString(),configuration);
			}
		}

		return null;
	}

	/**
	 * 自定义，主要是开放 parseDynamicTags
	 */
	public static class CustomXMLScriptBuilder extends XMLScriptBuilder{

		public CustomXMLScriptBuilder(Configuration configuration, XNode context) {
			super(configuration, context);
		}

		public CustomXMLScriptBuilder(Configuration configuration, XNode context, Class<?> parameterType) {
			super(configuration, context, parameterType);
		}

		public MixedSqlNode publicParseDynamicTags(XNode node){
			return parseDynamicTags(node);
		}
	}
}
