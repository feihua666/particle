package com.particle.dept.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dept.infrastructure.dos.DeptTreeDO;
import com.particle.dept.infrastructure.mapper.DeptTreeMapper;
import com.particle.dept.infrastructure.service.IDeptTreeService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 部门树 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Component
public class DeptTreeServiceImpl extends IBaseServiceImpl<DeptTreeMapper, DeptTreeDO> implements IDeptTreeService {
	private IBaseQueryCommandMapStruct<DeptTreeDO> queryCommandMapStruct;

	@Override
	protected DeptTreeDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DeptTreeDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DeptTreeDO po) {
	}

	@Override
	protected void preUpdate(DeptTreeDO po) {
    
	}
}
