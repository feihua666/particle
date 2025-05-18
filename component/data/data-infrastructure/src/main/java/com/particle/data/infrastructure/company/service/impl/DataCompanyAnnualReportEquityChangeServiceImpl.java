package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportEquityChangeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportEquityChangeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportEquityChangeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报股权变更 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Component
public class DataCompanyAnnualReportEquityChangeServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportEquityChangeMapper, DataCompanyAnnualReportEquityChangeDO> implements IDataCompanyAnnualReportEquityChangeService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportEquityChangeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportEquityChangeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportEquityChangeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportEquityChangeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportEquityChangeDO po) {
    
	}
}
