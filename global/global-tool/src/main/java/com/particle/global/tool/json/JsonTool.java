package com.particle.global.tool.json;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONUtil;
import com.particle.global.tool.calendar.CalendarTool;

import java.util.Collection;

/**
 * <p>
 * json工具
 * </p>
 *
 * @author yangwei
 * @since 2022-04-13 19:52
 */
public class JsonTool {


	public static JSONConfig jsonConfig = JSONConfig.create().setDateFormat(CalendarTool.DateStyle.YYYY_MM_DD_HH_MM_SS.getValue()).setIgnoreNullValue(false);
	/**
	 * 转化为json
	 * @param object
	 * @return
	 */
	public static String toJsonStr(Object object) {
		if (object == null) {
			return null;
		}
		if (object instanceof Collection) {

			return JSONUtil.parseArray(object,
					jsonConfig).toString();
		}
		return JSONUtil.parseObj(object,
				jsonConfig).toString();
	}
}
