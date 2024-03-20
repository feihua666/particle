package com.particle.openplatform.infrastructure.doc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDocTemplateMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放接口文档模板 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Component
public class OpenplatformDocApiDocTemplateServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDocTemplateMapper, OpenplatformDocApiDocTemplateDO> implements IOpenplatformDocApiDocTemplateService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDocTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDocTemplateDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDocTemplateDO po) {
	    // 模板名称 已存在不能添加
	    assertByColumn(po.getName(),OpenplatformDocApiDocTemplateDO::getName,false);

	}

	@Override
	protected void preUpdate(OpenplatformDocApiDocTemplateDO po) {
	    OpenplatformDocApiDocTemplateDO byId = null;
	    if (StrUtil.isNotEmpty(po.getName())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果模板名称有改动
	        if (!po.getName().equals(byId.getName())) {
	            // 模板名称已存在不能修改
	            assertByColumn(po.getName(),OpenplatformDocApiDocTemplateDO::getName,false);
	        }
	    }

    
	}
}
