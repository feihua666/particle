package com.particle.data.infrastructure.dynamicdata.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorCategoryUploadRecordDO;
import com.particle.data.infrastructure.dynamicdata.mapper.DynamicDataIndicatorCategoryUploadRecordMapper;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorCategoryUploadRecordService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 动态数据指标分类上传记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Component
public class DynamicDataIndicatorCategoryUploadRecordServiceImpl extends IBaseServiceImpl<DynamicDataIndicatorCategoryUploadRecordMapper, DynamicDataIndicatorCategoryUploadRecordDO> implements IDynamicDataIndicatorCategoryUploadRecordService {
	private IBaseQueryCommandMapStruct<DynamicDataIndicatorCategoryUploadRecordDO> queryCommandMapStruct;

	@Override
	protected DynamicDataIndicatorCategoryUploadRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DynamicDataIndicatorCategoryUploadRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DynamicDataIndicatorCategoryUploadRecordDO po) {
	}

	@Override
	protected void preUpdate(DynamicDataIndicatorCategoryUploadRecordDO po) {
    
	}
}
