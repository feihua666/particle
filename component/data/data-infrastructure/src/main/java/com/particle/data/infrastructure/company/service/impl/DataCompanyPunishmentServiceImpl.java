package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyPunishmentDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyPunishmentMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyPunishmentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业行政处罚 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Component
public class DataCompanyPunishmentServiceImpl extends IBaseServiceImpl<DataCompanyPunishmentMapper, DataCompanyPunishmentDO> implements IDataCompanyPunishmentService {
	private IBaseQueryCommandMapStruct<DataCompanyPunishmentDO> queryCommandMapStruct;

	@Override
	protected DataCompanyPunishmentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyPunishmentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyPunishmentDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyPunishmentDO po) {
    
	}
}
