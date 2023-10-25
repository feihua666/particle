package com.particle.usagecount.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDetailDO;
import com.particle.usagecount.infrastructure.mapper.UsageCountRecordDetailMapper;
import com.particle.usagecount.infrastructure.service.IUsageCountRecordDetailService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 使用次数记录明细 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-23 16:23:29
 */
@Component
public class UsageCountRecordDetailServiceImpl extends IBaseServiceImpl<UsageCountRecordDetailMapper, UsageCountRecordDetailDO> implements IUsageCountRecordDetailService {
	private IBaseQueryCommandMapStruct<UsageCountRecordDetailDO> queryCommandMapStruct;

	@Override
	protected UsageCountRecordDetailDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UsageCountRecordDetailDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(UsageCountRecordDetailDO po) {
	}

	@Override
	protected void preUpdate(UsageCountRecordDetailDO po) {
    
	}
}
