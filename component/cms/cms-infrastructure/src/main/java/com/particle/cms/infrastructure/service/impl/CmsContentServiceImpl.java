package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsContentDO;
import com.particle.cms.infrastructure.mapper.CmsContentMapper;
import com.particle.cms.infrastructure.service.ICmsContentService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 内容 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:16
 */
@Component
public class CmsContentServiceImpl extends IBaseServiceImpl<CmsContentMapper, CmsContentDO> implements ICmsContentService {
	private IBaseQueryCommandMapStruct<CmsContentDO> queryCommandMapStruct;

	@Override
	protected CmsContentDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsContentDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsContentDO po) {
	}

	@Override
	protected void preUpdate(CmsContentDO po) {
    
	}
}
