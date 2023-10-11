/**
 * <p>
 * 修改版本 5.8.21
 * 主要解决在手动传了别名，优先使用手动的，优先以A、B等做为别名
 * 参见修改：{@link cn.hutool.poi.excel.reader.AbstractSheetReader#aliasHeader(java.lang.Object, int)}
 * 定位 AbstractSheetReader {@link cn.hutool.poi.excel.reader.BeanSheetReader}
 * </p>
 *
 * @author yangwei
 * @since 2023-06-06 17:42
 */
package cn.hutool.poi.excel.reader;