package com.particle.global.big.datasource.bigdatasource.command.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.XmlUtil;
import com.particle.global.tool.json.JsonTool;

import java.util.Map;

/**
 * <p>
 * 默认的大数据源map类型数据分页查询指令
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 13:23
 */
public class DefaultBigDatasourceMapPageQueryCommand extends AbstractBigDatasourcePageQueryCommand<Map<String,Object>>{

	public DefaultBigDatasourceMapPageQueryCommand(Map<String,Object> data) {
		super(data);
	}

	@Override
	public Map<String, Object> toMap() {

		Map<String, Object> objectMap = BeanUtil.beanToMap(this);
		if (data != null) {
			objectMap.putAll(data);
		}
		return objectMap;
	}

	@Override
	public String toJson() {
		return JsonTool.toJsonStr(toMap());
	}

	@Override
	public String toXml() {
		return XmlUtil.mapToXmlStr(toMap());
	}


	public static DefaultBigDatasourceMapPageQueryCommand create(Map<String,Object> data) {
		return new DefaultBigDatasourceMapPageQueryCommand(data);
	}

}
