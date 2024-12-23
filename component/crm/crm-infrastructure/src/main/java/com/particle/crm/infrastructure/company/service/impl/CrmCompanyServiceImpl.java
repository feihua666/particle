package com.particle.crm.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crm.infrastructure.company.dos.CrmCompanyDO;
import com.particle.crm.infrastructure.company.mapper.CrmCompanyMapper;
import com.particle.crm.infrastructure.company.service.ICrmCompanyService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 客户公司 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Component
public class CrmCompanyServiceImpl extends IBaseServiceImpl<CrmCompanyMapper, CrmCompanyDO> implements ICrmCompanyService {
	private IBaseQueryCommandMapStruct<CrmCompanyDO> queryCommandMapStruct;

	@Override
	protected CrmCompanyDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrmCompanyDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrmCompanyDO po) {
	    // 公司编码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(),CrmCompanyDO::getCode,false);
		}

	}

	@Override
	protected void preUpdate(CrmCompanyDO po) {
	    CrmCompanyDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果公司编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 公司编码已存在不能修改
	            assertByColumn(po.getCode(),CrmCompanyDO::getCode,false);
	        }
	    }


	}
}
