package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsTemplateContentDO;
import com.particle.cms.infrastructure.mapper.CmsTemplateContentMapper;
import com.particle.cms.infrastructure.service.ICmsTemplateContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 模板内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:13:35
 */
@Component
public class CmsTemplateContentServiceImpl extends IBaseServiceImpl<CmsTemplateContentMapper, CmsTemplateContentDO> implements ICmsTemplateContentService {
	private IBaseQueryCommandMapStruct<CmsTemplateContentDO> queryCommandMapStruct;

	@Override
	protected CmsTemplateContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsTemplateContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsTemplateContentDO po) {
	}

	@Override
	protected void preUpdate(CmsTemplateContentDO po) {
    
	}
}
