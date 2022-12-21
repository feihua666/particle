package com.particle.func.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.mapper.FuncMapper;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单功能 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Service
public class FuncServiceImpl extends IBaseServiceImpl<FuncMapper, FuncDO> implements IFuncService {

	private IBaseQueryCommandMapStruct<FuncDO> queryCommandMapStruct;

	@Override
	protected FuncDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FuncDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(FuncDO po) {
		// 编码已存在不能添加
		assertByColumn(po.getCode(),FuncDO::getCode,false);
	}

	@Override
	protected void preUpdate(FuncDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			FuncDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),FuncDO::getCode,false);
			}
		}
	}
}
