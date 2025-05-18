package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import com.particle.data.infrastructure.company.dto.DataCompanyRestrictHighConsumeListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.mapper.DataCompanyRestrictHighConsumeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyRestrictHighConsumeService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业限制高消费 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Component
public class DataCompanyRestrictHighConsumeServiceImpl extends IBaseServiceImpl<DataCompanyRestrictHighConsumeMapper, DataCompanyRestrictHighConsumeDO> implements IDataCompanyRestrictHighConsumeService {
	private IBaseQueryCommandMapStruct<DataCompanyRestrictHighConsumeDO> queryCommandMapStruct;

	private DataCompanyRestrictHighConsumeMapper dataCompanyRestrictHighConsumeMapper;

	@Override
	protected DataCompanyRestrictHighConsumeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}


	@Override
	protected void preAdd(DataCompanyRestrictHighConsumeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyRestrictHighConsumeDO po) {

	}

	@Override
	public Page<DataCompanyRestrictHighConsumeDO> listPage(DataCompanyRestrictHighConsumeListPageByCompanyIdParam param, PageQueryCommand pageQueryForm) {
		Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
		return dataCompanyRestrictHighConsumeMapper.listPage(pageQuery,param);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyRestrictHighConsumeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setDataCompanyRestrictHighConsumeMapper(DataCompanyRestrictHighConsumeMapper dataCompanyRestrictHighConsumeMapper) {
		this.dataCompanyRestrictHighConsumeMapper = dataCompanyRestrictHighConsumeMapper;
	}
}
