package com.particle.dept.infrastructure.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.dept.infrastructure.deptuserrel.mapper.DeptUserRelMapper;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.dept.infrastructure.mapper.DeptMapper;
import com.particle.dept.infrastructure.service.IDeptService;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


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

	private DeptUserRelMapper deptUserRelMapper;
	@Override
	protected DeptDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DeptDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setDeptUserRelMapper(DeptUserRelMapper deptUserRelMapper) {
		this.deptUserRelMapper = deptUserRelMapper;
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

	@Override
	public DeptDO getByUserId(Long userId) {
		Assert.notNull(userId,"userId 不能为空");
		DeptUserRelDO deptUserRelDO = deptUserRelMapper.selectOne(Wrappers.<DeptUserRelDO>lambdaQuery().eq(DeptUserRelDO::getUserId, userId));
		if (deptUserRelDO != null) {
			return getById(deptUserRelDO.getDeptId());
		}
		return null;
	}

	@Override
	public Map<Long, DeptDO> getMapByUserIds(List<Long> userIds) {
		Assert.notEmpty(userIds,"userIds 不能为空");
		List<DeptUserRelDO> deptUserRelDOS = deptUserRelMapper.selectList(Wrappers.<DeptUserRelDO>lambdaQuery().in(DeptUserRelDO::getUserId, userIds));
		Map<Long, DeptDO> map = new HashMap<>();

		if (CollectionUtil.isEmpty(deptUserRelDOS)) {
			return map;
		}
		List<Long> deptIds = deptUserRelDOS.stream().map(DeptUserRelDO::getDeptId).collect(Collectors.toList());
		List<DeptDO> deptDOS = listByIds(deptIds);
		Map<Long, DeptDO> deptMap = deptDOS.stream().collect(Collectors.toMap(DeptDO::getId, Function.identity()));
		for (DeptUserRelDO deptUserRelDO : deptUserRelDOS) {
			map.put(deptUserRelDO.getUserId(), deptMap.get(deptUserRelDO.getDeptId()));
		}

		return map;
	}
}
