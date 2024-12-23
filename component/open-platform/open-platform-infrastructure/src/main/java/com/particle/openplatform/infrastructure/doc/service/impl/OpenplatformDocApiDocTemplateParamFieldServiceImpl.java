package com.particle.openplatform.infrastructure.doc.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateParamFieldDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDocTemplateParamFieldMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateParamFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口文档模板参数字段 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Component
public class OpenplatformDocApiDocTemplateParamFieldServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDocTemplateParamFieldMapper, OpenplatformDocApiDocTemplateParamFieldDO> implements IOpenplatformDocApiDocTemplateParamFieldService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateParamFieldDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDocTemplateParamFieldDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateParamFieldDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDocTemplateParamFieldDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformDocApiDocTemplateParamFieldDO po) {

	}
}
