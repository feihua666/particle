package com.particle.openplatform.infrastructure.doc.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateResponseCodeDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDocTemplateResponseCodeMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateResponseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口文档模板响应码 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Component
public class OpenplatformDocApiDocTemplateResponseCodeServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDocTemplateResponseCodeMapper, OpenplatformDocApiDocTemplateResponseCodeDO> implements IOpenplatformDocApiDocTemplateResponseCodeService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateResponseCodeDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDocTemplateResponseCodeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateResponseCodeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDocTemplateResponseCodeDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformDocApiDocTemplateResponseCodeDO po) {

	}
}
