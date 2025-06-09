package com.particle.global.tool.template;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.comparator.CompareUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.*;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.engine.enjoy.EnjoyTemplate;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.jfinal.template.source.FileSourceFactory;
import com.particle.global.tool.json.JsonTool;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.str.FilePathTool;
import com.particle.global.tool.str.NetPathTool;
import lombok.SneakyThrows;

import javax.script.Bindings;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义 EnjoyEngine
 * </p>
 *
 * @author yangwei
 * @since 2023-02-21 10:32
 */
public class CustomEnjoyEngine implements TemplateEngine{



	private com.jfinal.template.Engine engine;
	private TemplateConfig.ResourceMode resourceMode;

	static {
		com.jfinal.template.Engine.addExtensionMethod(String.class, StringExt.class);
	}
	// --------------------------------------------------------------------------------- Constructor start

	/**
	 * 默认构造
	 */
	public CustomEnjoyEngine() {}

	/**
	 * 构造
	 *
	 * @param config 模板配置
	 */
	public CustomEnjoyEngine(TemplateConfig config) {
		init(config);
	}

	/**
	 * 构造
	 *
	 * @param engine {@link com.jfinal.template.Engine}
	 */
	public CustomEnjoyEngine(com.jfinal.template.Engine engine) {
		init(engine);
	}
	// --------------------------------------------------------------------------------- Constructor end

	@Override
	public TemplateEngine init(TemplateConfig config) {
		if(null == config){
			config = TemplateConfig.DEFAULT;
		}
		this.resourceMode = config.getResourceMode();
		init(createEngine(config));
		return this;
	}

	/**
	 * 初始化引擎
	 * @param engine 引擎
	 */
	private void init(com.jfinal.template.Engine engine){
		this.engine = engine;
		engine.addSharedMethod(new SharedMethodKit());

	}

	@Override
	public Template getTemplate(String resource) {
		if(null == this.engine){
			init(TemplateConfig.DEFAULT);
		}
		if (ObjectUtil.equal(TemplateConfig.ResourceMode.STRING, this.resourceMode)) {
			return EnjoyTemplate.wrap(this.engine.getTemplateByString(resource));
		}
		return EnjoyTemplate.wrap(this.engine.getTemplate(resource));
	}

	/**
	 * 创建引擎
	 *
	 * @param config 模板配置
	 * @return {@link com.jfinal.template.Engine}
	 */
	private static com.jfinal.template.Engine createEngine(TemplateConfig config) {
		final com.jfinal.template.Engine engine = com.jfinal.template.Engine.create("Hutool-Enjoy-Engine-" + IdUtil.fastSimpleUUID());
		engine.setEncoding(config.getCharsetStr());

		switch (config.getResourceMode()) {
			case STRING:
				// 默认字符串类型资源:
				break;
			case CLASSPATH:
				engine.setToClassPathSourceFactory();
				engine.setBaseTemplatePath(config.getPath());
				break;
			case FILE:
				engine.setSourceFactory(new FileSourceFactory());
				engine.setBaseTemplatePath(config.getPath());
				break;
			case WEB_ROOT:
				engine.setSourceFactory(new FileSourceFactory());
				engine.setBaseTemplatePath(FileUtil.getAbsolutePath(FileUtil.getWebRoot()));
				break;
			default:
				break;
		}

		return engine;
	}


	/**
	 * 字符串扩展方法
	 */
	public static class StringExt{

		public String upperFirst(String self) {
			return StrUtil.upperFirst(self);
		}

		public String lowerFirst(String self) {
			return StrUtil.lowerFirst(self);
		}

	}

	/**
	 * Shared Method 扩展
	 */
	public static class SharedMethodKit {
		/**
		 * 判断对象是否为空，在模板中尽量使用该方法
		 * 因为在使用 hutool将json转为map时，如果值有null，并不是null而是 {@link JSONNull}
		 * @param obj
		 * @return
		 */
		public static boolean isObjNull(Object obj) {
			return JSONUtil.isNull(obj);
		}

		/**
		 * 如果为null设置一个默认值
		 * @param obj
		 * @param defaultObj
		 * @return
		 */
		public static Object nullToDefault(Object obj, Object defaultObj) {
			return isObjNull(obj) ? defaultObj : obj;
		}

		/**
		 * 将null转为null
		 * 因为在使用 hutool将json转为map时，如果值有null，并不是null而是 {@link JSONNull}
		 * @param obj
		 * @return
		 */
		public Object nullToNull(Object obj) {
			return isObjNull(obj) ? null : obj;
		}

		/**
		 * 是否为空
		 * @param str
		 * @return
		 */
		public static boolean isStrEmpty(String str) {
			return StrUtil.isEmpty(str);
		}
		/**
		 * 是否不为空
		 * @param str
		 * @return
		 */
		public static boolean isStrNotEmpty(String str) {
			return StrUtil.isNotEmpty(str);
		}
		/**
		 * 将空转为null文件在模板中统一判断
		 * @param str
		 * @return
		 */
		public static String emptyStrToNull(String str) {
			return StrUtil.emptyToNull(str);
		}

		/**
		 * 如果一个对象为空，则转为null，包括字符串和集合
		 * @param o
		 * @return
		 */
		public static Object emptyToNull(Object o) {
			if (isObjNull(o)) {
				return null;
			}
			if (o instanceof String) {
				return emptyStrToNull(((String) o));
			}
			if (o instanceof Collection) {
				return CollectionUtil.isEmpty(((Collection<?>) o)) ? null : o;
			}
			if (o instanceof Object[]) {
				return ((Object[]) o).length == 0 ? null : o;
			}
			return o;
		}

		/**
		 * 返回第一个有值的字符串
		 * @param str
		 * @return
		 */
		public static String firstNoneEmptyStr(String ...str) {
			if (str != null && str.length > 0) {
				for (String s : str) {
					if (StrUtil.isNotEmpty(s)) {
						return s;
					}
				}
			}
			return null;
		}

		public static Map<Integer, String> strSplit(String str, String split) {
			if (StrUtil.isEmpty(str)) {
				return Collections.emptyMap();
			}
			String[] split1 = str.split(split);
			Map<Integer, String> map = new HashMap<>(split1.length);
			for (int i = 0; i < split1.length; i++) {
				map.put(i, split1[i]);
			}
			return map;
		}

		/**
		 * 正则替换
		 * @param content
		 * @param regex
		 * @param replacementTemplate
		 * @return
		 */
		public static String regReplaceAll(CharSequence content, String regex, String replacementTemplate) {
			return ReUtil.replaceAll(content, regex, replacementTemplate);
		}

		/**
		 * 新建一个map
		 * @return
		 */
		public static Map<Object, Object> newHashMap() {
			return new HashMap<>();
		}


		/**
		 * 新建一个list
		 * @return
		 */
		public static List<Object> newArrayList() {
			return new ArrayList<>();
		}

		/**
		 * 将集合转为字符串
		 * @param collection
		 * @param split
		 * @return
		 */
		public static String arrayJoin(Collection<String> collection, String split) {
            if (CollectionUtil.isEmpty(collection)) {
				return null;
            }
			return collection.stream().collect(Collectors.joining(split));
		}
		/**
		 * 将对象转为jsonStr
		 * @param obj
		 * @return
		 */
		public static String toJsonStr(Object obj) {
			return JsonTool.toJsonStr(obj);
		}

		/**
		 * 先从map获取一个key ,先返回然后加步长
		 * @param map
		 * @param key
		 * @param step
		 * @return
		 */
		public static Integer integerGetAndIncrement(Map<Object,Object> map,String key,int step){
			Integer result = (Integer)map.get(key);
			if (result != null) {
				map.put(key, result + step);
			}
			return result;
		}
		/**
		 * 先从map获取一个key ,先返回然后加步长 1
		 * @param map
		 * @param key
		 * @return
		 */
		public static Integer integerGetAndIncrementOne(Map<Object,Object> map,String key){
			return integerGetAndIncrement(map, key, 1);
		}
		/**
		 * 先从map获取一个key并加步长后返回
		 * @param map
		 * @param key
		 * @param step
		 * @return
		 */
		public static Integer integerIncrementAndGet(Map<Object,Object> map,String key,int step){
			Integer result = (Integer)map.get(key);
			if (result != null) {
				result += step;
				map.put(key, result);
			}
			return result;
		}
		/**
		 * 先从map获取一个key并加步长 1 后返回
		 * @param map
		 * @param key
		 * @return
		 */
		public static Integer integerIncrementAndGetOne(Map<Object,Object> map,String key){
			return integerIncrementAndGet(map, key, 1);
		}

		/**
		 * 执行groovy脚本
		 * @param expression
		 * @param data
		 * @return
		 */
		@SneakyThrows
		public static Object groovyScript(String expression, Map<String, Object> data) {
			Bindings bindings = GroovyTool.createBindings();
			if (data != null) {
				bindings.putAll(data);
			}
			Object o = GroovyTool.compileAndEval(expression, bindings, true);
			return o;
		}

		/**
		 * 将字符格式化为字符串
		 * @param pattern
		 * @param value
		 * @return
		 */
		public static String decimalFormat(String pattern, Object value) {
			if (value == null) {
				return null;
			}
			try {
				return NumberUtil.decimalFormat(pattern, value);
			} catch (Exception e) {
				return null;
			}
		}

		/**
		 * 格式化为百分数，并保留两位小数
		 * @param value
		 * @return
		 */
		public static String decimalFormatP2(Object value) {
			return decimalFormat("#.##%", value);
		}

		/**
		 * 可比较的排序，一般集合中的元素必须实现{@link Comparable} 接口
		 * @param collection
		 * @param asc true=升序，false=降序
		 * @param isNullGreater null值是否做为最大值
		 * @return
		 */
		public static Collection<Object> comparableSort(Collection<Object> collection, boolean asc, boolean isNullGreater) {
			if (CollectionUtil.isEmpty(collection)) {
				return collection;
			}
			List<Object> sort = CollectionUtil.sort(collection, (v1, v2) -> {
				if (asc) {
					return CompareUtil.compare(v1, v2, false);
				}
				return 0 - CompareUtil.compare(v1, v2, false);
			});
			return sort;
		}

		/**
		 * 拼接网络路径
		 * @param str
		 * @return
		 */
		public static String netPathConcat(String ...str) {
			return NetPathTool.concat(str);
		}
		/**
		 * 拼接网络路径
		 * @param str
		 * @return
		 */
		public static String filePathConcat(String ...str) {
			return FilePathTool.concat(str);
		}
	}
}
