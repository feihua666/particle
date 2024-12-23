package com.particle.openplatform.infrastructure.doc.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDocMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口文档 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Component
public class OpenplatformDocApiDocServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDocMapper, OpenplatformDocApiDocDO> implements IOpenplatformDocApiDocService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDocDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDocDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDocDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDocDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformDocApiDocDO po) {

	}
}
