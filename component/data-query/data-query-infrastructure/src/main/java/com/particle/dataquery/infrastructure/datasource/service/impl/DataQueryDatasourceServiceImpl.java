package com.particle.dataquery.infrastructure.datasource.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceDO;
import com.particle.dataquery.infrastructure.datasource.mapper.DataQueryDatasourceMapper;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 数据查询数据源 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-08 14:29:00
 */
@Component
public class DataQueryDatasourceServiceImpl extends IBaseServiceImpl<DataQueryDatasourceMapper, DataQueryDatasourceDO> implements IDataQueryDatasourceService {
	private IBaseQueryCommandMapStruct<DataQueryDatasourceDO> queryCommandMapStruct;

	@Override
	protected DataQueryDatasourceDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataQueryDatasourceDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataQueryDatasourceDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 编码 已存在不能添加
			assertByColumn(po.getCode(),DataQueryDatasourceDO::getCode,false);

		}

	}

	@Override
	protected void preUpdate(DataQueryDatasourceDO po) {

	    DataQueryDatasourceDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),DataQueryDatasourceDO::getCode,false);
	        }
	    }


	}
}
