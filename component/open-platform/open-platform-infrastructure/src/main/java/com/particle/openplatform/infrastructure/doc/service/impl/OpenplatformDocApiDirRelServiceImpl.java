package com.particle.openplatform.infrastructure.doc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDirRelDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiDirRelMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDirRelService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 开放接口文档接口与目录关系 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Component
public class OpenplatformDocApiDirRelServiceImpl extends IBaseServiceImpl<OpenplatformDocApiDirRelMapper, OpenplatformDocApiDirRelDO> implements IOpenplatformDocApiDirRelService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDirRelDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDirRelDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDirRelDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDirRelDO po) {
	    // 开放接口文档接口id 已存在不能添加
	    assertByColumn(po.getOpenplatformDocApiId(),OpenplatformDocApiDirRelDO::getOpenplatformDocApiId,false);

	    // 开放接口文档目录id 已存在不能添加
	    assertByColumn(po.getOpenplatformDocDirId(),OpenplatformDocApiDirRelDO::getOpenplatformDocDirId,false);

	}

	@Override
	protected void preUpdate(OpenplatformDocApiDirRelDO po) {
	    OpenplatformDocApiDirRelDO byId = null;
	    if (po.getOpenplatformDocApiId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果开放接口文档接口id有改动
	        if (!po.getOpenplatformDocApiId().equals(byId.getOpenplatformDocApiId())) {
	            // 开放接口文档接口id已存在不能修改
	            assertByColumn(po.getOpenplatformDocApiId(),OpenplatformDocApiDirRelDO::getOpenplatformDocApiId,false);
	        }
	    }

	    if (po.getOpenplatformDocDirId() != null) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果开放接口文档目录id有改动
	        if (!po.getOpenplatformDocDirId().equals(byId.getOpenplatformDocDirId())) {
	            // 开放接口文档目录id已存在不能修改
	            assertByColumn(po.getOpenplatformDocDirId(),OpenplatformDocApiDirRelDO::getOpenplatformDocDirId,false);
	        }
	    }

    
	}
}
