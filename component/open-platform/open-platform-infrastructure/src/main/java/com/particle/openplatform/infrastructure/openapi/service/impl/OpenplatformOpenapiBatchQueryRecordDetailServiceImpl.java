package com.particle.openplatform.infrastructure.openapi.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDetailDO;
import com.particle.openplatform.infrastructure.openapi.mapper.OpenplatformOpenapiBatchQueryRecordDetailMapper;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordDetailService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放接口批量查询记录明细 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:46:36
 */
@Component
public class OpenplatformOpenapiBatchQueryRecordDetailServiceImpl extends IBaseServiceImpl<OpenplatformOpenapiBatchQueryRecordDetailMapper, OpenplatformOpenapiBatchQueryRecordDetailDO> implements IOpenplatformOpenapiBatchQueryRecordDetailService {
	private IBaseQueryCommandMapStruct<OpenplatformOpenapiBatchQueryRecordDetailDO> queryCommandMapStruct;

	@Override
	protected OpenplatformOpenapiBatchQueryRecordDetailDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformOpenapiBatchQueryRecordDetailDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformOpenapiBatchQueryRecordDetailDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformOpenapiBatchQueryRecordDetailDO po) {
    
	}
}
