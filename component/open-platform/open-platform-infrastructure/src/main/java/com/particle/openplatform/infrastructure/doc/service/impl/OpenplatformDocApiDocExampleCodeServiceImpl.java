package com.particle.openplatform.infrastructure.doc.service.impl;

import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocExampleCodeDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDocExampleCodeMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocExampleCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口文档示例代码 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Component
public class OpenplatformDocApiDocExampleCodeServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDocExampleCodeMapper, OpenplatformDocApiDocExampleCodeDO> implements IOpenplatformDocApiDocExampleCodeService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDocExampleCodeDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDocExampleCodeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDocExampleCodeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDocExampleCodeDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformDocApiDocExampleCodeDO po) {

	}
}
