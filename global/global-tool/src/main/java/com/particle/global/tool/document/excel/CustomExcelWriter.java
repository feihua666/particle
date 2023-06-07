package com.particle.global.tool.document.excel;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.SafeConcurrentHashMap;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.RowUtil;
import cn.hutool.poi.excel.StyleSet;
import cn.hutool.poi.excel.cell.CellUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * <p>
 * 自定义excel写出，主要是填充样式处理
 * </p>
 *
 * @author yangwei
 * @since 2023-06-07 13:25
 */
public class CustomExcelWriter extends ExcelWriter {


	public CustomExcelWriter(Workbook workbook, String sheetName) {
		super(workbook, sheetName);
	}

	@Override
	public ExcelWriter writeRow(Iterable<?> rowData) {
		Assert.isFalse(this.isClosed, "ExcelWriter has been closed!");
		writeRow(this.sheet.createRow(currentRowGetAndIncrement()), rowData, getStyleSet(), false);
		return this;
	}
	@Override
	public ExcelWriter writeHeadRow(Iterable<?> rowData) {
		Assert.isFalse(this.isClosed, "ExcelWriter has been closed!");

		ReflectUtil.setFieldValue(this,"headLocationCache", new SafeConcurrentHashMap<>());

		writeRow(this.sheet.createRow(currentRowGetAndIncrement()), rowData, getStyleSet(), true);

		return this;
	}
	/**
	 * 拷贝自 {@link RowUtil#writeRow(org.apache.poi.ss.usermodel.Row, java.lang.Iterable, cn.hutool.poi.excel.StyleSet, boolean)}
	 * 并做了修改样式的修改
	 * @param row
	 * @param rowData
	 * @param styleSet
	 * @param isHeader
	 */
	private static void writeRow(Row row, Iterable<?> rowData, StyleSet styleSet, boolean isHeader) {

		int i = 0;
		Cell cell;
		for (Object value : rowData) {
			cell = row.createCell(i);
			if (styleSet instanceof CustomStyleSet) {
				cell.setCellStyle(((CustomStyleSet) styleSet).getStyleByValueType(value,isHeader,i));
				CellUtil.setCellValue(cell, value, null, isHeader);
			}else {
				CellUtil.setCellValue(cell, value, styleSet, isHeader);
			}
			i++;
		}
	}

	synchronized
	private int currentRowGetAndIncrement(){

		int r = getCurrentRow();
		passCurrentRow();
		return r;
	}
}