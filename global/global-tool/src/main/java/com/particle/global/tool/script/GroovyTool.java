package com.particle.global.tool.script;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.script.ScriptUtil;

import javax.script.*;

/**
 * <p>
 * groovy脚本动态执行
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 17:12
 */
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
	/**
	 * 编码并执行
	 * @param script
	 * @param bindings
	 * @return
	 */
	public static Object compileAndEval(String script, Bindings bindings,boolean useCache) throws ScriptException {
		Compilable compilable = ((Compilable) getGroovyEngine());
		CompiledScript compiledScript = null;
		if (useCache) {
			compiledScript = compiledScriptCache.get(script, () -> compilable.compile(script));
		}else {
			compiledScript = compilable.compile(script);
		}
		Object evalResult = compiledScript.eval(bindings);
		return evalResult;

	}
}