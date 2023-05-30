package com.particle.global.exception;

import com.particle.global.exception.biz.BizException;
import com.particle.global.exception.code.IErrorCode;
import org.mapstruct.ap.internal.util.Strings;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 常用断言工具
 * </p>
 *
 * @author yangwei
 * @since 2022-04-20 13:39
 */
public abstract class Assert {

	/**
	 * 断言 一个表达式是否为 {@code true} 否则抛出 {@code BizException}
	 * 示例：
	 * <pre class="code">Assert.isTrue(i != 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
	 *
	 * @param expression 布尔表达式
	 * @param errorCode 如果断言失败，异常编码异常信息
	 * @throws BizException 如果布尔表达式结果为 {@code false} 将会抛出该异常
	 */
	public static void isTrue(boolean expression, IErrorCode errorCode) {
		if (!expression) {
			throw new BizException(errorCode);
		}
	}

	/**
	 * 断言 一个表达式是否为 {@code true} 否则抛出 {@code BizException}
	 * 示例：
	 * <pre class="code">Assert.isFalse(i == 0, errorCode.B_ORDER_illegalNumber, "The order number can not be zero");</pre>
	 * @param expression 布尔表达式
	 * @param errorCode 如果断言失败，异常编码
	 * @throws BizException 如果布尔表达式结果为 {@code true} 将会抛出该异常
	 */
	public static void isFalse(boolean expression, IErrorCode errorCode) {
		if (expression) {
			throw new BizException(errorCode);
		}
	}

	/**
	 * 断言 一个表达式是否为 {@code true} 否则抛出 {@code BizException}
	 * 示例：
	 * <pre class="code">Assert.isTrue(i != 0, "The order number can not be zero");</pre>
	 * 该异常使用默认的 异常码 {@link BizException#DEFAULT_ERR_CODE}
	 * @param expression 布尔表达式
	 * @param errMessage 如果断言失败，异常信息
	 * @throws BizException 如果布尔表达式结果为 {@code false} 将会抛出该异常
	 */
	public static void isTrue(boolean expression, String errMessage) {
		if (!expression) {
			throw new BizException(errMessage);
		}
	}

	/**
	 * 断言 一个表达式是否为 {@code false} 否则抛出 {@code BizException}
	 * 示例：
	 * <pre class="code">Assert.isFalse(i == 0, "The order number can not be zero");</pre>
	 * 该异常使用默认的 异常码 {@link BizException#DEFAULT_ERR_CODE}
	 * @param expression 布尔表达式
	 * @param errMessage 如果断言失败，异常信息
	 * @throws BizException 如果布尔表达式结果为 {@code true} 将会抛出该异常
	 */
	public static void isFalse(boolean expression, String errMessage) {
		if (expression) {
			throw new BizException(errMessage);
		}
	}

	/**
	 * 断言 一个表达式是否为 {@code true} 否则抛出 {@code BizException}
	 * 示例：
	 * <pre class="code">Assert.isTrue(i != 0, "The order number can not be zero");</pre>
	 * 如果断言失败：
	 * 该异常使用默认的 异常码 {@link BizException#DEFAULT_ERR_CODE}
	 * @param expression 布尔表达式
	 * @throws BizException 如果布尔表达式结果为 {@code false} 将会抛出该异常
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] Must be true");
	}

	/**
	 * 断言 一个表达式是否为 {@code false} 否则抛出 {@code BizException}
	 * 示例：
	 * <pre class="code">Assert.isFalse(i == 0, "The order number can not be zero");</pre>
	 * 如果断言失败：
	 * 该异常使用默认的 异常码 {@link BizException#DEFAULT_ERR_CODE}
	 * @param expression 布尔表达式
	 * @throws BizException 如果布尔表达式结果为 {@code true} 将会抛出该异常
	 */
	public static void isFalse(boolean expression) {
		isFalse(expression, "[Assertion failed] Must be false");
	}

	public static void notNull(Object object, IErrorCode errorCode) {
		if (object == null) {
			throw new BizException(errorCode);
		}
	}

	public static void notNull(Object object, String errMessage) {
		if (object == null) {
			throw new BizException(errMessage);
		}
	}

	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] Must not null");
	}

	public static void notEmpty(Collection<?> collection, IErrorCode errorCode) {
		if (collection == null || collection.isEmpty()) {
			throw new BizException(errorCode);
		}
	}

	public static void notEmpty(Collection<?> collection, String errMessage) {
		if (collection == null || collection.isEmpty()) {
			throw new BizException(errMessage);
		}
	}

	public static void notEmpty(Collection<?> collection) {
		notEmpty(collection, "[Assertion failed] Collection must not be empty: it must contain at least 1 element");
	}

	public static void notEmpty(Map<?, ?> map, IErrorCode errorCode) {
		if (map == null || map.isEmpty()) {
			throw new BizException(errorCode);
		}
	}

	public static void notEmpty(Map<?, ?> map, String errMessage) {
		if (map == null || map.isEmpty()) {
			throw new BizException(errMessage);
		}
	}

	public static void notEmpty(Map<?, ?> map) {
		notEmpty(map, "[Assertion failed] Map must not be empty: it must contain at least one entry");
	}


	public static void notEmpty(String str, IErrorCode errorCode) {
		if (Strings.isEmpty(str)) {
			throw new BizException(errorCode);
		}
	}

	public static void notEmpty(String str, String errMessage) {
		if (Strings.isEmpty(str)) {
			throw new BizException(errMessage);
		}
	}
}
