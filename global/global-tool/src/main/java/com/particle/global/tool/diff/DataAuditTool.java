package com.particle.global.tool.diff;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.diff.changetype.PropertyChange;
import org.javers.core.diff.changetype.ValueChange;
import org.javers.core.diff.custom.BigDecimalComparatorWithFixedEquals;
import org.javers.core.diff.custom.CustomBigDecimalComparator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 数据审计对比工具
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 10:59
 */
@Slf4j
public class DataAuditTool {

	private static volatile Javers JAVERS;

	/**
	 * 获取 javers 实例
	 * @return
	 */
	public static Javers getJavers() {
		if (null == JAVERS) {
			synchronized(DataAuditTool.class) {
				if (JAVERS == null) {
					JAVERS = JaversBuilder.javers()
							.registerValue(BigDecimal.class,new CustBigDecimalComparator())
							.withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE)
							.build();
				}
			}
		}

		return JAVERS;
	}

	/**
	 * 将对象转为字符串
	 * @param obj
	 * @return
	 */
	public static String formatString(Object obj) {
		if (obj == null) {
			return "";
		}
		if (obj instanceof Date) {
			return DateUtil.format(((Date) obj), DatePattern.NORM_DATETIME_PATTERN);
		}
		if (obj instanceof LocalDateTime) {
			return ((LocalDateTime) obj).format(DatePattern.NORM_DATETIME_FORMATTER);
		}
		if (obj instanceof LocalDate) {
			return ((LocalDate) obj).format(DatePattern.NORM_DATE_FORMATTER);
		}
		if (obj instanceof LocalTime) {
			return ((LocalTime) obj).format(DatePattern.NORM_TIME_FORMATTER);
		}
		return obj.toString();
	}

	/**
	 * 比较差异
	 * @param oldVersion 原来版本数据
	 * @param currentVersion 当前版本数据
	 * @return
	 */
	public static Diff compare(Object oldVersion, Object currentVersion) {
		return compare(oldVersion, currentVersion, getJavers());
	}
	/**
	 * 比较差异
	 * @param oldVersion 原来版本数据
	 * @param currentVersion 当前版本数据
	 * @return
	 */
	public static Diff compare(Object oldVersion, Object currentVersion,Javers javers) {
		log.debug("compare 开始");
		long start = System.currentTimeMillis();
		try {
			return javers.compare(oldVersion, currentVersion);
		} finally {
			log.info("compare 结束，耗时{} ms",System.currentTimeMillis() - start);
		}
	}
	/**
	 * 比较并返回对比结果
	 * @param oldVersion
	 * @param currentVersion
	 * @return
	 */
	public static List<PropertyCompareResult> compareWithResult(Object oldVersion, Object currentVersion) {
		Diff compare = compare(oldVersion, currentVersion);
		Changes changes = compare.getChanges();
		return changesToPropertyCompareResults(changes);
	}

	/**
	 * 数据转换
	 * @param changes
	 * @return
	 */
	public static List<PropertyCompareResult> changesToPropertyCompareResults(List<Change> changes) {
		if (CollectionUtil.isEmpty(changes)) {
			return Collections.emptyList();
		}
		List<PropertyCompareResult> results = new ArrayList<>(changes.size());
		for (Change change : changes) {
			if (change instanceof ValueChange) {
				ValueChange valueChange = (ValueChange) change;
				results.add(PropertyCompareResult.create(valueChange));
			}else {
				throw new RuntimeException(change.getClass().getName() + " do not support for convert PropertyCompareResult");
			}
		}
		return results;
	}
	/**
	 * 转为字符串
	 * @param results
	 * @return
	 */
	public static String prettyPrint(List<PropertyCompareResult> results){
		return results.stream().map(PropertyCompareResult::prettyPrint).collect(Collectors.joining(","));

	}

	/**
	 * 自定义比较器，只比较数值相等即可
	 * {@link CustomBigDecimalComparator} 使用 equals 比较 即使数值相等字面不相等也会不相等
	 */
	public static class CustBigDecimalComparator extends BigDecimalComparatorWithFixedEquals {
		@Override
		public String toString(BigDecimal value) {
			return value.toString();
		}
	}


	/**
	 * 单个字段对比结果
	 */
	@Setter
	@Getter
	@Accessors(chain = true)
	public static class PropertyCompareResult implements Serializable {
		/**
		 * 字段名称，一般为中文
		 */
		private String name;
		/**
		 * 字段属性
		 */
		private String property;
		/**
		 * 原来值
		 */
		private String oldValue;
		/**
		 * 新值
		 */
		private String newValue;

		/**
		 * 变化类型,如添加、删除等 取值 {@link PropertyChange#getChangeType()}
		 */
		private String changeType;

		/**
		 * 美化打印
		 * @return
		 */
		public String prettyPrint() {
			String prefix = property;
			if (StrUtil.isNotEmpty(name)) {
				prefix += StrUtil.format("({})",name);
			}
			return StrUtil.format("{} : {} -> {}",prefix,oldValue,newValue);
		}

		public static PropertyCompareResult create(String name,
				String property,
				String oldValue,
				String newValue,
												   String changeType) {
			PropertyCompareResult propertyCompareResult = new PropertyCompareResult();
			propertyCompareResult.setName(name);
			propertyCompareResult.setProperty(property);
			propertyCompareResult.setOldValue(oldValue);
			propertyCompareResult.setNewValue(newValue);
			propertyCompareResult.setChangeType(changeType);
			return propertyCompareResult;
		}

		public static PropertyCompareResult create(ValueChange valueChange) {
			return PropertyCompareResult.create(valueChange.getPropertyName(),
					valueChange.getPropertyNameWithPath(),
					formatString(valueChange.getLeft()),
					formatString(valueChange.getRight()),
					valueChange.getChangeType().name());
		}

	}
}
