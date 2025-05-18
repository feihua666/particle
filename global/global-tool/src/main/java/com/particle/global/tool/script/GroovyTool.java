package com.particle.global.tool.script;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.script.ScriptUtil;
import lombok.extern.slf4j.Slf4j;

import javax.script.*;
import java.util.Map;

/**
 * <p>
 * groovy脚本动态执行
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 17:12
 */
@Slf4j
public class GroovyTool {

	/**
	 * 如果直接使用 groovyEngine eval 可能有并发问题，因为 设置为 引擎级别 bindings数据会冲突
	 * 建议编译运行，数据与代码分隔
	 */
	private static volatile ScriptEngine groovyEngine;

	private static TimedCache<String, CompiledScript> compiledScriptCache = CacheUtil.newTimedCache(0);
	/**
	 * 确保已经初始化
	 */
	public static ScriptEngine getGroovyEngine(){

		if (groovyEngine == null) {
			synchronized (GroovyTool.class){
				if (groovyEngine == null) {
					groovyEngine = ScriptUtil.createGroovyEngine();
				}
			}
		}
		return groovyEngine;
	}

	public static Bindings createBindings(){
		return getGroovyEngine().createBindings();
	}
	public static Bindings createBindings(Map<String,Object> data){
		Bindings bindings = createBindings();
		if (data != null) {
			bindings.putAll(data);
		}
		return bindings;
	}
	/**
	 * 编码并执行
	 * @param script
	 * @param bindings
	 * @param useCache 是否使用已经编译好的脚本，否则重新编译
	 * @return
	 */
	public static Object compileAndEval(String script, Bindings bindings,boolean useCache) throws ScriptException {
		CompiledScript compiledScript = null;
		Object evalResult = null;
		try {
			compiledScript = compile(script, useCache);
			evalResult = compiledScript.eval(bindings);
		} catch (ScriptException e) {
			log.error("groovyScript error script=" + script);
			throw new RuntimeException(e);
		}
		return evalResult;

	}

	/**
	 * 编码
	 * @param script
	 */
	public static CompiledScript compile(String script,boolean useCache) throws ScriptException {
		Compilable compilable = ((Compilable) getGroovyEngine());
		CompiledScript compiledScript = null;
		if (useCache) {
			compiledScript = compiledScriptCache.get(script, () -> compilable.compile(script));
		}else {
			compiledScript = compilable.compile(script);
		}
		return compiledScript;
	}
}
