package com.particle.crm.infrastructure.company.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.crm.infrastructure.company.dos.CrmDeptDO;
import com.particle.crm.infrastructure.company.mapper.CrmDeptMapper;
import com.particle.crm.infrastructure.company.service.ICrmDeptService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 客户公司部门 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
@Component
public class CrmDeptServiceImpl extends IBaseServiceImpl<CrmDeptMapper, CrmDeptDO> implements ICrmDeptService {
	private IBaseQueryCommandMapStruct<CrmDeptDO> queryCommandMapStruct;

	@Override
	protected CrmDeptDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CrmDeptDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CrmDeptDO po) {
	    // 部门编码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(),CrmDeptDO::getCode,false);
		}

	}

	@Override
	protected void preUpdate(CrmDeptDO po) {
	    CrmDeptDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果部门编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 部门编码已存在不能修改
	            assertByColumn(po.getCode(),CrmDeptDO::getCode,false);
	        }
	    }


	}
}
