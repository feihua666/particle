package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyAnnualReportChangeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyAnnualReportChangeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyAnnualReportChangeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业年报变更 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Component
public class DataCompanyAnnualReportChangeServiceImpl extends IBaseServiceImpl<DataCompanyAnnualReportChangeMapper, DataCompanyAnnualReportChangeDO> implements IDataCompanyAnnualReportChangeService {
	private IBaseQueryCommandMapStruct<DataCompanyAnnualReportChangeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyAnnualReportChangeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyAnnualReportChangeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyAnnualReportChangeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyAnnualReportChangeDO po) {
    
	}
}
