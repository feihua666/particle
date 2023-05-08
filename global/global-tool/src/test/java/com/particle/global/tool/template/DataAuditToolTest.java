package com.particle.global.tool.template;

import com.particle.global.tool.diff.DataAuditTool;
import lombok.Data;
import org.javers.common.collections.Lists;
import org.javers.core.Changes;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.changetype.PropertyChange;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 数据审计工具测试
 * </p>
 *
 * @author yangwei
 * @since 2023-05-05 11:06
 */
public class DataAuditToolTest {

	public static void main(String[] args) throws InterruptedException {

		TestData testDataOldVersion = new TestData();
		/**
		 * {@link DataAuditTool#formatString(java.lang.Object)} 日期格式化毫秒级别的变化在格式化以后不显示，看上去是相等的，但实际不相等
		 * 这里隔一秒
		 */
		Thread.sleep(1000);
		TestData testDataNewVersion = new TestData();
		testDataNewVersion.setValueBigDecimal(new BigDecimal("23.3"));
		Diff diff = DataAuditTool.compare(testDataOldVersion, testDataNewVersion);
		print(diff,"update");

		diff = DataAuditTool.compare(null, testDataNewVersion);
		print(diff,"add");

		diff = DataAuditTool.compare(Lists.asList(testDataOldVersion), Lists.asList(testDataNewVersion));
		print(diff,"list");
	}

	/**
	 * 分隔打印
	 * @param diff
	 * @param printFlag
	 */
	public static void print(Diff diff,String printFlag) {
		System.out.println("*******************************" + printFlag + "********************************");
		System.out.println(diff.prettyPrint());
		Changes changes = diff.getChanges();

		for (Change change : changes) {
			if (change instanceof PropertyChange) {
				PropertyChange valueChange = (PropertyChange) change;
				String propertyName = valueChange.getPropertyName();
				Object left = valueChange.getLeft();
				Object right = valueChange.getRight();
				System.out.println(((PropertyChange<?>) change).getChangeType());
				System.out.println(((PropertyChange<?>) change).getPropertyNameWithPath());
				System.out.println(String.format("属性名：%s，原来值： %s 新值 %s",
						valueChange.getPropertyName(),
						DataAuditTool.formatString(valueChange.getLeft()),
						DataAuditTool.formatString(valueChange.getRight())
				));
			}
		}
	}

	/**
	 * 测试数据 pojo
	 */
	@Data
	public static class TestData{
		private String valueStr = "valueStr";
		private Integer valueInteger = 2;
		private int valueInt = 3;
		private boolean valueBool = true;
		private Boolean valueBoolean = false;

		private LocalDateTime valueLocalDateTime = LocalDateTime.now();
		private Date valueDate = Date.from(Instant.now());
		private BigDecimal valueBigDecimal = new BigDecimal("23.30");
	}
}
