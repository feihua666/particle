package com.particle.global.big.datasource.bigdatasource.command.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.XmlUtil;
import com.particle.global.tool.json.JsonTool;

import java.util.Map;

/**
 * <p>
 * 默认的大数据源map类型查询指令
 * </p>
 *
 * @author yangwei
 * @since 2023-03-10 13:31
 */
public class DefaultBigDatasourceMapQueryCommand extends AbstractBigDatasourceQueryCommand<Map<String,Object>>{

	public DefaultBigDatasourceMapQueryCommand(Map<String,Object> data) {
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


	public static DefaultBigDatasourceMapQueryCommand create(Map<String,Object> data){
		return new DefaultBigDatasourceMapQueryCommand(data);
	}
}
