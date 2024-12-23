package com.particle.openplatform.infrastructure.doc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocApiMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口文档接口 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Component
public class OpenplatformDocApiServiceImpl extends IBaseServiceImpl<OpenplatformDocApiMapper, OpenplatformDocApiDO> implements IOpenplatformDocApiService {
	private IBaseQueryCommandMapStruct<OpenplatformDocApiDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocApiDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocApiDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocApiDO po) {
	    // 编码 已存在不能添加
	    assertByColumn(po.getCode(),OpenplatformDocApiDO::getCode,false);

	}

	@Override
	protected void preUpdate(OpenplatformDocApiDO po) {
	    OpenplatformDocApiDO byId = null;
	    if (StrUtil.isNotEmpty(po.getCode())) {
	        byId = byId == null ? getById(po.getId()) : byId;
	        // 如果编码有改动
	        if (!po.getCode().equals(byId.getCode())) {
	            // 编码已存在不能修改
	            assertByColumn(po.getCode(),OpenplatformDocApiDO::getCode,false);
	        }
	    }


	}
}
