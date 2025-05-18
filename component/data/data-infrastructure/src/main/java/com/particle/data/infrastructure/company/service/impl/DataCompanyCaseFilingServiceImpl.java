package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.data.infrastructure.company.dto.DataCompanyCaseFilingListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.mapper.DataCompanyCaseFilingMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyCaseFilingService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业立案信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Component
public class DataCompanyCaseFilingServiceImpl extends IBaseServiceImpl<DataCompanyCaseFilingMapper, DataCompanyCaseFilingDO> implements IDataCompanyCaseFilingService {
	private IBaseQueryCommandMapStruct<DataCompanyCaseFilingDO> queryCommandMapStruct;

	private DataCompanyCaseFilingMapper dataCompanyCaseFilingMapper;
	@Override
	protected DataCompanyCaseFilingDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(DataCompanyCaseFilingDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyCaseFilingDO po) {

	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyCaseFilingDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setDataCompanyCaseFilingMapper(DataCompanyCaseFilingMapper dataCompanyCaseFilingMapper) {
		this.dataCompanyCaseFilingMapper = dataCompanyCaseFilingMapper;
	}

	@Override
	public Page<DataCompanyCaseFilingDO> listPage(DataCompanyCaseFilingListPageByCompanyIdParam param, PageQueryCommand pageQueryForm) {
		Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
		return dataCompanyCaseFilingMapper.listPage(pageQuery, param);
	}
}
