package com.particle.global.light.share.trans;

/**
 * <p>
 * 翻译相关静态常量类
 * </p>
 *
 * @author yangwei
 * @since 2022-08-07 18:20
 */
public class TransConstants {

	/**
	 * 默认的表翻译，如果确认是单机运行，可直接使用
	 */
	public static final String defaultTransType = "trans_by_table_name";

	public static final String TRANS_DATE = "date";
	public static final String TRANS_DATETIME = "datetime";
	public static final String TRANS_BY_THREAD_LOCAL = "trans_by_thread_local";
}
