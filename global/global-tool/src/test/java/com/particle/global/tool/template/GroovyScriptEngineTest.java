package com.particle.global.tool.template;

import cn.hutool.script.ScriptUtil;
import lombok.Builder;
import lombok.Data;

import javax.script.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2023-03-23 14:47
 */
public class GroovyScriptEngineTest {
	public static void main(String[] args) throws ScriptException {
		ScriptEngine groovyEngine = ScriptUtil.createGroovyEngine();
		ScriptEngine groovyEngine1 = ScriptUtil.createGroovyEngine();

		System.out.println("groovyEngine == groovyEngine1:" + (groovyEngine == groovyEngine1));

		String prefix = "name";
		createBindings(groovyEngine, prefix);

		System.out.println(groovyEngine.eval(prefix + "1"));

		prefix = "ttttt";
		createBindings(groovyEngine, prefix);

		System.out.println(groovyEngine.eval(prefix + "1"));


		createBindingsTestobj(groovyEngine, "test");
		groovyEngine.eval("testobj.changeName('sss')");
		System.out.println(groovyEngine.eval("testobj.name"));

		Compilable compilable = ((Compilable) groovyEngine);
		CompiledScript compile = compilable.compile("testobj.changeName(sss);def result = testobj.name;result;testobj.create('eeeee','ddddddd')");
		Bindings bindings = groovyEngine.createBindings();
		bindings.put("testobj", TestObj.builder().build());
		bindings.put("sss", "sssssssssss");

		System.out.println(compile.eval(bindings));


		String scriptContent = "def res = groovyTest.testQuery(num);\n" +
				"res";
	}

	public static void createBindings(ScriptEngine groovyEngine, String prefix) {
		Bindings bindings = groovyEngine.createBindings();

		bindings.put(prefix + "1", prefix + "1111");
		bindings.put(prefix + "2", prefix + "2222");
		// binding 重置设置之后，以前的就没有了
		groovyEngine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

	}
	public static void createBindingsTestobj(ScriptEngine groovyEngine, String prefix) {
		Bindings bindings = groovyEngine.createBindings();

		bindings.put(prefix + "obj", TestObj.builder().build());
		// binding 重置设置之后，以前的就没有了
		groovyEngine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

	}

	@Data
	@Builder
	public static class TestObj{
		private String name;
		private String value;

		public void changeName(String name) {
			this.name = name;
		}

		public TestObj create(String name, String value) {
			return TestObj.builder().name(name).value(value).build();
		}
	}
}
