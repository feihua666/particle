package com.particle.data.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.data.infrastructure.company.dos.DataCompanyDO;
import com.particle.data.infrastructure.company.mapper.DataCompanyMapper;
import com.particle.data.infrastructure.company.service.IDataCompanyService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 企业 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Component
public class DataCompanyServiceImpl extends IBaseServiceImpl<DataCompanyMapper, DataCompanyDO> implements IDataCompanyService {
	private IBaseQueryCommandMapStruct<DataCompanyDO> queryCommandMapStruct;

	@Override
	protected DataCompanyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DataCompanyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DataCompanyDO po) {
	    // 统一社会信用代码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getUscc())) {
			assertByColumn(po.getUscc(),DataCompanyDO::getUscc,false);
		}
	    // 注册号 已存在不能添加
		if (StrUtil.isNotEmpty(po.getRegNo())) {
			assertByColumn(po.getRegNo(),DataCompanyDO::getRegNo,false);
		}
		// 组织机构代码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getOrgCode())) {
			assertByColumn(po.getOrgCode(),DataCompanyDO::getOrgCode,false);
		}

	}

	@Override
	protected void preUpdate(DataCompanyDO po) {
	    DataCompanyDO byId = null;
	    if (StrUtil.isNotEmpty(po.getUscc())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果统一社会信用代码有改动
	        if (!po.getUscc().equals(byId.getUscc())) {
	            // 统一社会信用代码已存在不能修改
	            assertByColumn(po.getUscc(),DataCompanyDO::getUscc,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getRegNo())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果注册号有改动
	        if (!po.getRegNo().equals(byId.getRegNo())) {
	            // 注册号已存在不能修改
	            assertByColumn(po.getRegNo(),DataCompanyDO::getRegNo,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getOrgCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果组织机构代码有改动
	        if (!po.getOrgCode().equals(byId.getOrgCode())) {
	            // 组织机构代码已存在不能修改
	            assertByColumn(po.getOrgCode(),DataCompanyDO::getOrgCode,false);
	        }
	    }

    
	}
}
