package com.particle.usagecount.infrastructure.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.usagecount.infrastructure.dos.UsageCountConfigDO;
import com.particle.usagecount.infrastructure.mapper.UsageCountConfigMapper;
import com.particle.usagecount.infrastructure.service.IUsageCountConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 使用次数配置 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:14:39
 */
@Component
public class UsageCountConfigServiceImpl extends IBaseServiceImpl<UsageCountConfigMapper, UsageCountConfigDO> implements IUsageCountConfigService {
	private IBaseQueryCommandMapStruct<UsageCountConfigDO> queryCommandMapStruct;

	@Override
	protected UsageCountConfigDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UsageCountConfigDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(UsageCountConfigDO po) {
	}

	@Override
	protected void preUpdate(UsageCountConfigDO po) {

	}
}
