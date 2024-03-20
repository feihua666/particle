package com.particle.openplatform.infrastructure.doc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocParamFieldDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDocParamFieldMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocParamFieldService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放接口文档参数字段 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Component
public class OpenplatformDocApiDocParamFieldServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDocParamFieldMapper, OpenplatformDocApiDocParamFieldDO> implements IOpenplatformDocApiDocParamFieldService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDocParamFieldDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDocParamFieldDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDocParamFieldDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDocParamFieldDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformDocApiDocParamFieldDO po) {
    
	}
}
