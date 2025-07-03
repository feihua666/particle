package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsSiteDO;
import com.particle.cms.infrastructure.mapper.CmsSiteMapper;
import com.particle.cms.infrastructure.service.ICmsSiteService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 站点 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:04
 */
@Component
public class CmsSiteServiceImpl extends IBaseServiceImpl<CmsSiteMapper, CmsSiteDO> implements ICmsSiteService {
	private IBaseQueryCommandMapStruct<CmsSiteDO> queryCommandMapStruct;

	@Override
	protected CmsSiteDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsSiteDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsSiteDO po) {
	    // 站点编码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(),CmsSiteDO::getCode,false);
		}

	    // 站点名称 已存在不能添加
		if (StrUtil.isNotEmpty(po.getName())) {
			assertByColumn(po.getName(),CmsSiteDO::getName,false);
		}

	}

	@Override
	protected void preUpdate(CmsSiteDO po) {
	    CmsSiteDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果站点编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 站点编码已存在不能修改
	            assertByColumn(po.getCode(),CmsSiteDO::getCode,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果站点名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 站点名称已存在不能修改
	            assertByColumn(po.getName(),CmsSiteDO::getName,false);
	        }
	    }
	}
}
