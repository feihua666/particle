package com.particle.dept.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.infrastructure.mapper.DeptMapper;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Component
public class DeptServiceImpl extends IBaseServiceImpl<DeptMapper, DeptDO> implements IDeptService {
	private IBaseQueryCommandMapStruct<DeptDO> queryCommandMapStruct;

	@Override
	protected DeptDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DeptDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DeptDO po) {
		// 部门编码 已存在不能添加
		if (StrUtil.isNotEmpty(po.getCode())) {
			assertByColumn(po.getCode(),DeptDO::getCode,false);
		}

	}

	@Override
	protected void preUpdate(DeptDO po) {

		DeptDO byId = null;
		if (StrUtil.isNotEmpty(po.getCode())) {
			byId = byId == null ? getById(po.getId()) : byId;
			// 如果部门编码有改动
			if (!po.getCode().equals(byId.getCode())) {
				// 部门编码已存在不能修改
				assertByColumn(po.getCode(),DeptDO::getCode,false);
			}
		}
	}
}
