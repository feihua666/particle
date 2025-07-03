package com.particle.cms.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.cms.infrastructure.dos.CmsContentMultimediaDO;
import com.particle.cms.infrastructure.mapper.CmsContentMultimediaMapper;
import com.particle.cms.infrastructure.service.ICmsContentMultimediaService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 内容多媒体 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-06-24 17:14:28
 */
@Component
public class CmsContentMultimediaServiceImpl extends IBaseServiceImpl<CmsContentMultimediaMapper, CmsContentMultimediaDO> implements ICmsContentMultimediaService {
	private IBaseQueryCommandMapStruct<CmsContentMultimediaDO> queryCommandMapStruct;

	@Override
	protected CmsContentMultimediaDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<CmsContentMultimediaDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(CmsContentMultimediaDO po) {
	}

	@Override
	protected void preUpdate(CmsContentMultimediaDO po) {
    
	}
}
