package com.particle.dataquery.domain.datasource.enums;

import cn.hutool.json.JSONUtil;
import com.particle.component.light.share.dict.api.IDictGroup;
import com.particle.component.light.share.dict.api.IDictItem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * <p>
 * 数据源接口参数类型 字典项
 * </p>
 *
 * @author yw
 * @since 2024-01-16 13:24:31
 */
public enum DataQueryDatasourceApiParamType implements IDictItem {

	/**
	 * 对象
	 */
	object
	,
	/**
	 * 数组
	 */
	array
	,
	/**
	 * 字符串
	 */
	string
	,
	/**
	 * 数字
	 */
	number
	,
	/**
	 * 浮点数，变量冲突，这里加参数构造
	 */
	floats("float"),

	/**
	 * 布尔值，变量冲突，这里加参数构造
	 */
	booleans("boolean");

	private String value;

	DataQueryDatasourceApiParamType() {
		this.value = this.name();
	}

	DataQueryDatasourceApiParamType(String value) {
		this.value = value;
	}

	@Override
	public String itemValue() {
		return this.value;
	}

	@Override
	public String groupCode() {
		return Group.dataquery_datasource_api_param_type.groupCode();
	}



	/**
	 * 自定义转换
	 * @param value
	 * @return
	 */
	public static DataQueryDatasourceApiParamType valuesOf(String value) {
		return Arrays.stream(DataQueryDatasourceApiParamType.values()).filter(item -> item.value.equals(value)).findFirst().orElse(null);
	}

	private static Map<DataQueryDatasourceApiParamType, Function<String,Object>> toObjectMap = new HashMap<>();
	static {
		toObjectMap.put(DataQueryDatasourceApiParamType.object, (str) -> {
			try {
				return JSONUtil.parseObj(str);
			} catch (Exception e) {
				return JSONUtil.parseArray(str);
			}
		});
		toObjectMap.put(DataQueryDatasourceApiParamType.array, (str) -> {
			try {
				return JSONUtil.parseArray(str);
			} catch (Exception e) {
				return JSONUtil.parseObj(str);
			}
		});
		toObjectMap.put(DataQueryDatasourceApiParamType.string, (str) -> str);

		toObjectMap.put(DataQueryDatasourceApiParamType.number, (str) -> Long.parseLong(str));

		toObjectMap.put(DataQueryDatasourceApiParamType.floats, (str) -> Float.parseFloat(str));

		toObjectMap.put(DataQueryDatasourceApiParamType.booleans, (str) -> Boolean.parseBoolean(str));
	}

	/**
	 * 根据类型转为对应的对象
	 * @param string
	 * @return
	 */
	public Object adaptType(String string) {
		return toObjectMap.get(this).apply(string);
	}

	/**
	 * 数据源接口参数类型 字典组
	 */
	public enum Group implements IDictGroup {
		dataquery_datasource_api_param_type;

		@Override
		public String groupCode() {
			return this.name();
		}
	}

}
