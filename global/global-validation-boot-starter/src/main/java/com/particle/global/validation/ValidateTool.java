package com.particle.global.validation;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.scripting.DefaultScriptEvaluatorFactory;
import org.hibernate.validator.spi.scripting.ScriptEvaluator;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;

/**
 * <p>
 * 参数验证工具
 * </p>
 *
 * @author yangwei
 * @since 2023-03-20 11:12
 */
@Slf4j
public class ValidateTool {
	private static Validator validator;
	private static DefaultScriptEvaluatorFactory defaultScriptEvaluatorFactory;

	/**
	 * 获取手动校验器
	 * @return
	 */
	public static Validator getValidator(){
		if(validator == null){
			validator = Validation.buildDefaultValidatorFactory().getValidator();
		}
		return validator;
	}

	/**
	 * 脚本校验器
	 * @param languageName
	 * @return
	 */
	public static ScriptEvaluator getScriptEvaluator(String languageName){
		initDefaultScriptEvaluatorFactory();
		return defaultScriptEvaluatorFactory.getScriptEvaluatorByLanguageName(languageName);
	}

	/**
	 * 脚本断言
	 * @param script
	 * @param bindings
	 * @param languageName
	 * @return
	 */
	public static boolean scriptAssert(String script, Map<String, Object> bindings,String languageName) {
		ScriptEvaluator scriptEvaluator = getScriptEvaluator(languageName);
		Object evaluationResult = scriptEvaluator.evaluate(script, bindings);
		if (evaluationResult == null) {
			throw new RuntimeException("scriptAssert must return value. for " + script);
		}
		if ( !( evaluationResult instanceof Boolean ) ) {
			throw new RuntimeException("scriptAssert must return true or false. for " + script + " evaluationResult=" + evaluationResult + " evaluationResultClass=" + evaluationResult.getClass().getCanonicalName());
		}

		return Boolean.TRUE.equals( evaluationResult );
	}

	/**
	 * javascript断言
	 * @param script
	 * @param bindings
	 * @return
	 */
	public static boolean javascriptAssert(String script, Map<String, Object> bindings) {
		return scriptAssert(script, bindings, "javascript");
	}
	/**
	 * 初始化脚本工厂
	 */
	private static void initDefaultScriptEvaluatorFactory(){
		if (defaultScriptEvaluatorFactory == null) {
			defaultScriptEvaluatorFactory = new DefaultScriptEvaluatorFactory(Valid.class.getClassLoader());
		}
	}
}
