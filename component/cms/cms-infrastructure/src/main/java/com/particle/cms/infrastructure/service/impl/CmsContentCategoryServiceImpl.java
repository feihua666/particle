package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsContentCategoryDO;
import com.particle.cms.infrastructure.mapper.CmsContentCategoryMapper;
import com.particle.cms.infrastructure.service.ICmsContentCategoryService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 内容分类 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:40
 */
@Component
public class CmsContentCategoryServiceImpl extends IBaseServiceImpl<CmsContentCategoryMapper, CmsContentCategoryDO> implements ICmsContentCategoryService {
	private IBaseQueryCommandMapStruct<CmsContentCategoryDO> queryCommandMapStruct;

	@Override
	protected CmsContentCategoryDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsContentCategoryDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsContentCategoryDO po) {
	}

	@Override
	protected void preUpdate(CmsContentCategoryDO po) {
    
	}
}
