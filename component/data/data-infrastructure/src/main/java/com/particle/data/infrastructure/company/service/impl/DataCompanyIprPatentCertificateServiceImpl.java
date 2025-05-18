package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentCertificateDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyIprPatentCertificateMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyIprPatentCertificateService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业知识产权专利证书信息 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:02
 */
@Component
public class DataCompanyIprPatentCertificateServiceImpl extends IBaseServiceImpl<DataCompanyIprPatentCertificateMapper, DataCompanyIprPatentCertificateDO> implements IDataCompanyIprPatentCertificateService {
	private IBaseQueryCommandMapStruct<DataCompanyIprPatentCertificateDO> queryCommandMapStruct;

	@Override
	protected DataCompanyIprPatentCertificateDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyIprPatentCertificateDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyIprPatentCertificateDO po) {
	}

	@Override
	protected void preUpdate(DataCompanyIprPatentCertificateDO po) {
    
	}
}
