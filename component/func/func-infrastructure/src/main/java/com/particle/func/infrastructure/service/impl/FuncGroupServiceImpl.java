package com.particle.func.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.dos.FuncGroupDO;
import com.particle.func.infrastructure.mapper.FuncGroupMapper;
import com.particle.func.infrastructure.service.IFuncGroupService;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能组 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Component
public class FuncGroupServiceImpl extends IBaseServiceImpl<FuncGroupMapper, FuncGroupDO> implements IFuncGroupService {
	private IBaseQueryCommandMapStruct<FuncGroupDO> queryCommandMapStruct;


	private IFuncService iFuncService;

	@Override
	protected FuncGroupDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FuncGroupDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(FuncGroupDO po) {
		// 编码已存在不能添加
		assertByColumn(po.getCode(),FuncGroupDO::getCode,false);
	}

	@Override
	protected void preUpdate(FuncGroupDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			FuncGroupDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),FuncGroupDO::getCode,false);
			}
		}
	}

	@Override
	protected void preDeleteById(Long id, FuncGroupDO DO) {
		// 删除前判断是否存在对应的功能，如果存在不能删除
		iFuncService.assertByColumn(id, FuncDO::getFuncGroupId,false,"还有功能在使用，不能删除");
	}

	@Autowired
	public void setiFuncService(IFuncService iFuncService) {
		this.iFuncService = iFuncService;
	}
}
