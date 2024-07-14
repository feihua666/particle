package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.data.infrastructure.company.mapper.DataCompanyMd5Mapper;
import com.particle.data.infrastructure.company.service.IDataCompanyMd5Service;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业md5 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Component
public class DataCompanyMd5ServiceImpl extends IBaseServiceImpl<DataCompanyMd5Mapper, DataCompanyMd5DO> implements IDataCompanyMd5Service {
	private IBaseQueryCommandMapStruct<DataCompanyMd5DO> queryCommandMapStruct;

	@Override
	protected DataCompanyMd5DO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyMd5DO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyMd5DO po) {
	    // 统一社会信用代码md5 已存在不能添加
		if (StrUtil.isNotEmpty(po.getUsccMd5())) {
			assertByColumn(po.getUsccMd5(),DataCompanyMd5DO::getUsccMd5,false);
		}

	    // 注册号md5 已存在不能添加
		if (StrUtil.isNotEmpty(po.getRegNoMd5())) {
			assertByColumn(po.getRegNoMd5(),DataCompanyMd5DO::getRegNoMd5,false);
		}

	    // 组织机构代码md5 已存在不能添加
		if (StrUtil.isNotEmpty(po.getOrgCodeMd5())) {
			assertByColumn(po.getOrgCodeMd5(),DataCompanyMd5DO::getOrgCodeMd5,false);
		}

	}

	@Override
	protected void preUpdate(DataCompanyMd5DO po) {
	    DataCompanyMd5DO byId = null;
	    if (StrUtil.isNotEmpty(po.getUsccMd5())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果统一社会信用代码md5有改动
	        if (!po.getUsccMd5().equals(byId.getUsccMd5())) {
	            // 统一社会信用代码md5已存在不能修改
	            assertByColumn(po.getUsccMd5(),DataCompanyMd5DO::getUsccMd5,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getRegNoMd5())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果注册号md5有改动
	        if (!po.getRegNoMd5().equals(byId.getRegNoMd5())) {
	            // 注册号md5已存在不能修改
	            assertByColumn(po.getRegNoMd5(),DataCompanyMd5DO::getRegNoMd5,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getOrgCodeMd5())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果组织机构代码md5有改动
	        if (!po.getOrgCodeMd5().equals(byId.getOrgCodeMd5())) {
	            // 组织机构代码md5已存在不能修改
	            assertByColumn(po.getOrgCodeMd5(),DataCompanyMd5DO::getOrgCodeMd5,false);
	        }
	    }

    
	}
}
