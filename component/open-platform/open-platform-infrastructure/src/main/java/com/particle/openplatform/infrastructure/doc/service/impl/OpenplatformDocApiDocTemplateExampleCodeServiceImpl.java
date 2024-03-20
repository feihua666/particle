package com.particle.openplatform.infrastructure.doc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateExampleCodeDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDocTemplateExampleCodeMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateExampleCodeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放接口文档模板示例代码 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Component
public class OpenplatformDocApiDocTemplateExampleCodeServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDocTemplateExampleCodeMapper, OpenplatformDocApiDocTemplateExampleCodeDO> implements IOpenplatformDocApiDocTemplateExampleCodeService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateExampleCodeDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDocTemplateExampleCodeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateExampleCodeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDocTemplateExampleCodeDO po) {
	}

	@Override
	protected void preUpdate(OpenplatformDocApiDocTemplateExampleCodeDO po) {
    
	}
}
