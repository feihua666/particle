package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsTemplateDO;
import com.particle.cms.infrastructure.mapper.CmsTemplateMapper;
import com.particle.cms.infrastructure.service.ICmsTemplateService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 模板 服务实现类
 * </p>
 *
 * @author yw
 * @since 2026-01-21 21:03:36
 */
@Component
public class CmsTemplateServiceImpl extends IBaseServiceImpl<CmsTemplateMapper, CmsTemplateDO> implements ICmsTemplateService {
	private IBaseQueryCommandMapStruct<CmsTemplateDO> queryCommandMapStruct;

	@Override
	protected CmsTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsTemplateDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsTemplateDO po) {
	    // 唯一键 已存在不能添加
	    assertByColumn(po.getTemplateKey(),CmsTemplateDO::getTemplateKey,false);

	}

	@Override
	protected void preUpdate(CmsTemplateDO po) {
	    CmsTemplateDO byId = null;
	    if (StrUtil.isNotEmpty(po.getTemplateKey())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果唯一键有改动
	        if (!po.getTemplateKey().equals(byId.getTemplateKey())) {
	            // 唯一键已存在不能修改
	            assertByColumn(po.getTemplateKey(),CmsTemplateDO::getTemplateKey,false);
	        }
	    }

    
	}
}
