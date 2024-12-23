package com.particle.usagecount.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.usagecount.infrastructure.dos.UsageCountRecordDO;
import com.particle.usagecount.infrastructure.mapper.UsageCountRecordMapper;
import com.particle.usagecount.infrastructure.service.IUsageCountRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 使用次数记录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-10-19 17:17:29
 */
@Component
public class UsageCountRecordServiceImpl extends IBaseServiceImpl<UsageCountRecordMapper, UsageCountRecordDO> implements IUsageCountRecordService {
	private IBaseQueryCommandMapStruct<UsageCountRecordDO> queryCommandMapStruct;

	@Override
	protected UsageCountRecordDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UsageCountRecordDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(UsageCountRecordDO po) {
	    // 使用次数key 已存在不能添加
	    assertByColumn(po.getUsageCountKey(),UsageCountRecordDO::getUsageCountKey,false);

	}

	@Override
	protected void preUpdate(UsageCountRecordDO po) {
	    UsageCountRecordDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUsageCountKey())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果使用次数key有改动
	        if (!po.getUsageCountKey().equals(byId.getUsageCountKey())) {
	            // 使用次数key已存在不能修改
	            assertByColumn(po.getUsageCountKey(),UsageCountRecordDO::getUsageCountKey,false);
	        }
	    }


	}
}
