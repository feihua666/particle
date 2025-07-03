package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsChannelDO;
import com.particle.cms.infrastructure.mapper.CmsChannelMapper;
import com.particle.cms.infrastructure.service.ICmsChannelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 栏目 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:55
 */
@Component
public class CmsChannelServiceImpl extends IBaseServiceImpl<CmsChannelMapper, CmsChannelDO> implements ICmsChannelService {
	private IBaseQueryCommandMapStruct<CmsChannelDO> queryCommandMapStruct;

	@Override
	protected CmsChannelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsChannelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsChannelDO po) {
	    // 栏目编码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(),CmsChannelDO::getCode,false);
		}

	    // 栏目名称 已存在不能添加
		if (StrUtil.isNotEmpty(po.getName())) {
			assertByColumn(po.getName(),CmsChannelDO::getName,false);
		}

	}

	@Override
	protected void preUpdate(CmsChannelDO po) {
	    CmsChannelDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果栏目编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 栏目编码已存在不能修改
	            assertByColumn(po.getCode(),CmsChannelDO::getCode,false);
	        }
	    }

	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果栏目名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 栏目名称已存在不能修改
	            assertByColumn(po.getName(),CmsChannelDO::getName,false);
	        }
	    }


	}
}
