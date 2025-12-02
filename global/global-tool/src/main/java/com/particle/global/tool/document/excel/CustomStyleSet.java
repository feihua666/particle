package com.particle.global.tool.document.excel;

import cn.hutool.poi.excel.StyleSet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自定义样式集，为表头和数据提供更细粒度的样式
 * </p>
 *
 * @author yangwei
 * @since 2023-06-07 13:37
 */
public class CustomStyleSet extends StyleSet {

	/**
	 * 表头样式
	 */
	private List<CellStyle> headCellStyles;
	/**
	 * 数据样式
	 */
	private List<CellStyle> cellStyles;
	/**
	 * 构造
	 * @param workbook 工作簿
	 */
	public CustomStyleSet(Workbook workbook,int sheetIndex) {
		super(workbook);
		Sheet sheetAt = workbook.getSheetAt(sheetIndex);
		// 表头样式
		Row row = sheetAt.getRow(0);
		Short firstCellNum = null;
		Short lastCellNum = null;
		if (row != null) {
			firstCellNum = row.getFirstCellNum();
			lastCellNum = row.getLastCellNum();
            if (firstCellNum != null && lastCellNum != null && firstCellNum >= 0 && lastCellNum >= 0) {
                headCellStyles = new ArrayList<>(lastCellNum);
                for (int i = firstCellNum; i < lastCellNum; i++) {
                    headCellStyles.add(row.getCell(i) == null ? null : row.getCell(i).getCellStyle());
                }
            }

		}


		row = sheetAt.getRow(1);
		if (row != null && firstCellNum != null && lastCellNum != null && firstCellNum >= 0 && lastCellNum >= 0) {
			cellStyles = new ArrayList<>(lastCellNum);
			for (int i = firstCellNum; i < lastCellNum; i++) {
				cellStyles.add(row.getCell(i) == null ? null : row.getCell(i).getCellStyle());
			}
		}

	}
	/**
	 * 构造
	 * @param workbook 工作簿
	 */
	public CustomStyleSet(Workbook workbook) {
		this(workbook, 0);
	}

	@Override
	public CellStyle getStyleByValueType(Object value, boolean isHeader) {
		return super.getStyleByValueType(value, isHeader);
	}

	/**
	 * 扩展
	 * @param value
	 * @param isHeader
	 * @param index
	 * @return
	 */
	public CellStyle getStyleByValueType(Object value, boolean isHeader,int index) {
		if (isHeader) {
			if (headCellStyles != null && index < headCellStyles.size()) {
				return headCellStyles.get(index);
			}
		}else {
			if (cellStyles != null && index < cellStyles.size()) {
				return cellStyles.get(index);
			}
		}
		return super.getStyleByValueType(value, isHeader);
	}
}
