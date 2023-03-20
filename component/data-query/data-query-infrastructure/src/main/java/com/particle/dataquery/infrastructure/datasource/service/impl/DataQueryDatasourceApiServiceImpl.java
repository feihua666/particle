package com.particle.dataquery.infrastructure.datasource.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dataquery.infrastructure.datasource.dos.DataQueryDatasourceApiDO;
import com.particle.dataquery.infrastructure.datasource.mapper.DataQueryDatasourceApiMapper;
import com.particle.dataquery.infrastructure.datasource.service.IDataQueryDatasourceApiService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 数据查询数据源接口 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-03-14 22:21:40
 */
@Component
public class DataQueryDatasourceApiServiceImpl extends IBaseServiceImpl<DataQueryDatasourceApiMapper, DataQueryDatasourceApiDO> implements IDataQueryDatasourceApiService {
	private IBaseQueryCommandMapStruct<DataQueryDatasourceApiDO> queryCommandMapStruct;

	@Override
	protected DataQueryDatasourceApiDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataQueryDatasourceApiDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataQueryDatasourceApiDO po) {
		// 编码不必填，这里判断一下，但保证唯一
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 编码 已存在不能添加
			assertByColumn(po.getCode(),DataQueryDatasourceApiDO::getCode,false);
		}

	}

	@Override
	protected void preUpdate(DataQueryDatasourceApiDO po) {

	    DataQueryDatasourceApiDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),DataQueryDatasourceApiDO::getCode,false);
	        }
	    }

    
	}
}
