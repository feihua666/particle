package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentNoticeDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentNoticeMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentNoticeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利通知书信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:13
 */
@Component
public class DataCompanyIprPatentNoticeServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentNoticeMapper, DataCompanyIprPatentNoticeDO> implements IDataCompanyIprPatentNoticeService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentNoticeDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentNoticeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentNoticeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentNoticeDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentNoticeDO po) {
    
	}
}
