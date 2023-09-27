package com.particle.global.tool.template;

import cn.hutool.core.util.NumberUtil;
import com.particle.global.tool.script.GroovyTool;

import javax.script.ScriptException;
import java.util.HashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-09-20 11:03
 */
public class GroovyToolTest {

	public static void main(String[] args) throws ScriptException {
		HashMap<String, Object> objectObjectHashMap = new HashMap<>();
		objectObjectHashMap.put("name", "name");
		HashMap<String, Object> item = new HashMap<>();
		item.put("item", objectObjectHashMap);

		Object o = GroovyTool.compileAndEval("item.name", GroovyTool.createBindings(item), true);
		System.out.println(o);

		System.out.println(NumberUtil.decimalFormat("#.00%", 2.3));
	}
}
