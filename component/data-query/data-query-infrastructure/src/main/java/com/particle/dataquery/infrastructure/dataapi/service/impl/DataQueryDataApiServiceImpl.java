package com.particle.dataquery.infrastructure.dataapi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.infrastructure.dataapi.dos.DataQueryDataApiDO;
import com.particle.dataquery.infrastructure.dataapi.mapper.DataQueryDataApiMapper;
import com.particle.dataquery.infrastructure.dataapi.service.IDataQueryDataApiService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 数据查询数据接口 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-21 13:11:05
 */
@Component
public class DataQueryDataApiServiceImpl extends IBaseServiceImpl<DataQueryDataApiMapper, DataQueryDataApiDO> implements IDataQueryDataApiService {
	private IBaseQueryCommandMapStruct<DataQueryDataApiDO> queryCommandMapStruct;

	@Override
	protected DataQueryDataApiDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataQueryDataApiDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataQueryDataApiDO po) {
		// url 已存在不能添加
		assertByColumn(po.getUrl(), DataQueryDataApiDO::getUrl,false);
	}

	@Override
	protected void preUpdate(DataQueryDataApiDO po) {
		DataQueryDataApiDO byId = null;
		if (StrUtil.isNotEmpty(po.getUrl())) {
			byId = byId == null ? getById(po.getId()) : byId;
			// 如果 url 有改动
			if (!po.getUrl().equals(byId.getUrl())) {
				// url 已存在不能修改
				assertByColumn(po.getUrl(),DataQueryDataApiDO::getUrl,false);
			}
		}
	}
}
