package com.particle.global.tool.document.excel;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.WorkbookUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 主要是对excel操作做一些工具入口,主要是满足最见的导入导出的功能
 * </p>
 *
 * @author yangwei
 * @since 2023-06-06 17:17
 */
public class ExcelTool {

	public static final String xlsx_extension_suffix = ".xlsx";
	/**
	 * {@link JakartaServletUtil#write(HttpServletResponse, InputStream, String, String)}
	 */
	public static final String xlsx_response_content_type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";


	/**
	 * 封装 hutool,主要是满足最见的导入的功能
	 * 读取数据并将结果转为bean
	 * 默认读取第0个sheet
	 * 注意：该方法必须得指定标题，默认第0行为标题行
	 *
	 * @param inputStream
	 * @param beanType
	 * @param columnAndPropertyMapping 列与bean的字段映射关系，key=标题，value=值
	 * @return
	 */
	public static <T> List<T> readBeanAll(InputStream inputStream, Class<T> beanType, Map<String, String> columnAndPropertyMapping) {
		ExcelReader reader = ExcelUtil.getReader(inputStream);
		reader.setHeaderAlias(columnAndPropertyMapping);
		return reader.readAll(beanType);
	}

	/**
	 * 自动设置表头，需要配置注解 {@link ExcelHead#readAlias()}
	 *
	 * @param inputStream
	 * @param beanType
	 * @return
	 */
	public static <T> List<T> readBeanAll(InputStream inputStream, Class<T> beanType) {

		Map<String, String> headerAlias = resolveAlias(beanType, true, null);
		return readBeanAll(inputStream, beanType, headerAlias);
	}

	/**
	 * 自动设置表头，需要配置注解 {@link ExcelHead#readAlias()}
	 *
	 * @param inputStream
	 * @param beanType
	 * @param order       一般按beanType指标的ExcelHead的别名为A、B、C...等，按key进行排序
	 * @return
	 */
	public static <T> List<T> readBeanAll(InputStream inputStream, Class<T> beanType, Order order) {

		Map<String, String> headerAlias = resolveAlias(beanType, true, order);
		return readBeanAll(inputStream, beanType, headerAlias);
	}

	/**
	 * 根据excel模板，写出到输出流，具有保留模板样式的能力,主要是满足最见的导出的功能
	 * 默认读取第0个sheet
	 * 注意：该方法必须得指定标题，默认第0行为标题行，第1行为数据行（会沿用数据行的格式，如果有如LocalDate等字段在输出的excel中展示数字情况，请将模板对应的单元格设置为日期格式即可）
	 *
	 * @param inputStream                                                    excel输入流模板，只支持一个sheet，且需要带表头（表头必须在第一行）
	 * @param data                                                           数据 bean 对象， 支持map
	 * @param columnAndPropertyMapping，key为字段名，value为别名（一般为表头中文或A、B、C...等列名）
	 * @return
	 */
	public static <T> void writeBeanAll(InputStream inputStream, List<T> data, Map<String, String> columnAndPropertyMapping, OutputStream out) {
		Workbook book = WorkbookUtil.createBook(inputStream);
		int sheetIndex = 0;
		Sheet sheetAt = book.getSheetAt(sheetIndex);
		ExcelWriter writer = new CustomExcelWriter(book, sheetAt.getSheetName());

		writer.setStyleSet(new CustomStyleSet(book, sheetIndex));
		writer.setHeaderAlias(columnAndPropertyMapping);

		writer.setOnlyAlias(true);
		// 不写首行，因为模板已经有了表头，跳转第一行
		writer.passRows(1);
		// 不写首行，因为模板已经有了表头，设置为false
		writer.write(data, false);
		writer.flush(out, true);
		writer.close();
		IoUtil.close(out);
	}

	/**
	 * 自动获取标题
	 * 功能同 {@link ExcelTool#writeBeanAll(java.io.InputStream, java.util.List, java.util.Map, java.io.OutputStream)}
	 *
	 * @param inputStream
	 * @param data
	 * @param beanType
	 * @param out
	 */
	public static <T> void writeBeanAll(InputStream inputStream, List<T> data, Class<T> beanType, OutputStream out) {
		Map<String, String> headerAlias = resolveAlias(beanType, false, null);
		writeBeanAll(inputStream, data, headerAlias, out);
	}

	/**
	 * 自动获取标题
	 * 功能同 {@link ExcelTool#writeBeanAll(java.io.InputStream, java.util.List, java.util.Map, java.io.OutputStream)}
	 *
	 * @param inputStream
	 * @param data
	 * @param beanType
	 * @param out
	 * @param order       排序，如果 beanType 指定了 excelHead 别名为 A、B、C...等，一般按value正序排序
	 */
	public static <T> void writeBeanAll(InputStream inputStream, List<T> data, Class<T> beanType, OutputStream out, Order order) {
		Map<String, String> headerAlias = resolveAlias(beanType, false, order);
		writeBeanAll(inputStream, data, headerAlias, out);
	}

	/**
	 * 获取别名，获取bean class自定义的表头别名,主要用于读取或写出时使用
	 *
	 * @param beanType
	 * @param isRead   true=读取，false=写出
	 * @return
	 */
	public static Map<String, String> resolveAlias(Class<?> beanType, boolean isRead, Order order) {
		// 适配别名
		Field[] fields = ReflectUtil.getFields(beanType);
		Map<String, String> headerAlias = new LinkedHashMap<>();
		for (Field field : fields) {
			ExcelHead annotation = AnnotationUtil.getAnnotation(field, ExcelHead.class);
			if (annotation != null) {
				// 读取是key为别名，vakue为字段名
				if (isRead) {
					headerAlias.put(annotation.readAlias(), field.getName());
				} else {
					// 写出是key为字段名，value为别名（将输出为表头）
					headerAlias.put(field.getName(), annotation.writeAlias());
				}
			}
		}
		if (order != null) {
			headerAlias = headerAlias.entrySet().stream()
					.sorted(order.getComparator())
					.collect(Collectors.toMap(
							Map.Entry::getKey,
							Map.Entry::getValue,
							(e1, e2) -> e1,
							LinkedHashMap::new));
		}
		return headerAlias;
	}


	/**
	 * 指定一个排序方式，用来针对key或value排序，主要用来输出排序后的指定的表头
	 */
	public static enum Order {
		key_asc(Map.Entry.comparingByKey()),
		key_desc(Map.Entry.comparingByKey(java.util.Comparator.reverseOrder())),
		value_asc(Map.Entry.comparingByValue()),
		value_desc(Map.Entry.comparingByValue(java.util.Comparator.reverseOrder()));

		private Comparator comparator;

		Order(Comparator comparator) {
			this.comparator = comparator;
		}

		public <K extends Comparable<? super K>, V> Comparator<Map.Entry<K, V>> getComparator() {
			return comparator;
		}
	}
}
