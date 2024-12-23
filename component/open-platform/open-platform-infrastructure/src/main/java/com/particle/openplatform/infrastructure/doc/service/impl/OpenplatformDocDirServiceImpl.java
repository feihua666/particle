package com.particle.openplatform.infrastructure.doc.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocDirMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocDirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口目录 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Component
public class OpenplatformDocDirServiceImpl extends IBaseServiceImpl<OpenplatformDocDirMapper, OpenplatformDocDirDO> implements IOpenplatformDocDirService {
	private IBaseQueryCommandMapStruct<OpenplatformDocDirDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocDirDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocDirDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocDirDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformDocDirDO po) {

	}
}
