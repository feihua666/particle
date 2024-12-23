package com.particle.openplatform.infrastructure.doc.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocResponseCodeDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDocResponseCodeMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocResponseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口文档响应码 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Component
public class OpenplatformDocApiDocResponseCodeServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDocResponseCodeMapper, OpenplatformDocApiDocResponseCodeDO> implements IOpenplatformDocApiDocResponseCodeService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDocResponseCodeDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDocResponseCodeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDocResponseCodeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDocResponseCodeDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformDocApiDocResponseCodeDO po) {

	}
}
