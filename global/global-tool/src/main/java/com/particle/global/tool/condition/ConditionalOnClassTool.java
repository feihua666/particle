package com.particle.global.tool.condition;

import cn.hutool.core.util.ClassLoaderUtil;
import lombok.Data;

/**
 * <p>
 * 条件判断
 * </p>
 *
 * @author yangwei
 * @since 2022-08-08 14:07
 */
public class ConditionalOnClassTool {

	public static String CONDITION_ON_CLASS_PREFIX = "condition on class";


	/**
	 * 检查
	 * @param condition
	 * @return
	 */
	public static ConditionalOnClassResult checkResult(String condition) {
		ConditionalOnClassResult conditionalOnClassResult = new ConditionalOnClassResult();
		if (condition.contains(ConditionalOnClassTool.CONDITION_ON_CLASS_PREFIX)) {
			conditionalOnClassResult.setHasCondition(true);
			String[] split = condition.split(CONDITION_ON_CLASS_PREFIX);
			conditionalOnClassResult.setExpression(split[0].trim());
			conditionalOnClassResult.setClassPresent(ClassLoaderUtil.isPresent(split[1].trim()));
		}else {
			conditionalOnClassResult.setHasCondition(false);
			conditionalOnClassResult.setExpression(condition);
		}
		return conditionalOnClassResult;
	}

	/**
	 * 类条件结果
	 */
	@Data
	public static class ConditionalOnClassResult{
		/**
		 * 是否设置了条件
		 */
		Boolean hasCondition;
		/**
		 * 如果设置了条件，是否满足
		 */
		Boolean classPresent;
		/**
		 * 最终表达式
		 */
		private String expression;
	}
}
