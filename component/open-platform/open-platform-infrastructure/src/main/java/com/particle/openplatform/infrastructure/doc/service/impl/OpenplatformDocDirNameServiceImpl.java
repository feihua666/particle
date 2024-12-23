package com.particle.openplatform.infrastructure.doc.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirNameDO;
import com.particle.openplatform.infrastructure.doc.mapper.OpenplatformDocDirNameMapper;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocDirNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 开放接口目录名称 服务实现类
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Component
public class OpenplatformDocDirNameServiceImpl extends IBaseServiceImpl<OpenplatformDocDirNameMapper, OpenplatformDocDirNameDO> implements IOpenplatformDocDirNameService {
	private IBaseQueryCommandMapStruct<OpenplatformDocDirNameDO> queryCommandMapStruct;

	@Override
	protected OpenplatformDocDirNameDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<OpenplatformDocDirNameDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(OpenplatformDocDirNameDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 编码已存在不能添加
			assertByColumn(po.getCode(),OpenplatformDocDirNameDO::getCode,false);
		}
	}

	@Override
	protected void preUpdate(OpenplatformDocDirNameDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			OpenplatformDocDirNameDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),OpenplatformDocDirNameDO::getCode,false);
			}
		}
	}
}
