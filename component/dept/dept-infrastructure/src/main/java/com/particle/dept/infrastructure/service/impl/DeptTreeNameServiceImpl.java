package com.particle.dept.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.dept.infrastructure.dos.DeptTreeNameDO;
import com.particle.dept.infrastructure.mapper.DeptTreeNameMapper;
import com.particle.dept.infrastructure.service.IDeptTreeNameService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * <p>
 * 部门树名称 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Component
public class DeptTreeNameServiceImpl extends IBaseServiceImpl<DeptTreeNameMapper, DeptTreeNameDO> implements IDeptTreeNameService {
	private IBaseQueryCommandMapStruct<DeptTreeNameDO> queryCommandMapStruct;

	@Override
	protected DeptTreeNameDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DeptTreeNameDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DeptTreeNameDO po) {
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 部门树名称编码 已存在不能添加
			assertByColumn(po.getCode(),DeptTreeNameDO::getCode,false);
		}
	}

	@Override
	protected void preUpdate(DeptTreeNameDO po) {

		DeptTreeNameDO byId = null;
		if (StrUtil.isNotEmpty(po.getCode())) {
			byId = byId == null ? getById(po.getId()) : byId;
			// 如果部门树名称编码有改动
			if (!po.getCode().equals(byId.getCode())) {
				// 部门树名称编码已存在不能修改
				assertByColumn(po.getCode(),DeptTreeNameDO::getCode,false);
			}
		}
	}
}
