package com.particle.data.infrastructure.dynamictable.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.dynamictable.dos.DynamicTableUploadRecordDO;
import com.particle.data.infrastructure.dynamictable.mapper.DynamicTableUploadRecordMapper;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableUploadRecordService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 动态数据表格上传记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Component
public class DynamicTableUploadRecordServiceImpl extends IBaseServiceImpl<DynamicTableUploadRecordMapper, DynamicTableUploadRecordDO> implements IDynamicTableUploadRecordService {
	private IBaseQueryCommandMapStruct<DynamicTableUploadRecordDO> queryCommandMapStruct;

	@Override
	protected DynamicTableUploadRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DynamicTableUploadRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DynamicTableUploadRecordDO po) {
	}

	@Override
	protected void preUpdate(DynamicTableUploadRecordDO po) {
    
	}
}
