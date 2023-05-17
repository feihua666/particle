package com.particle.func.infrastructure.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单功能 服务类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
public interface IFuncService extends IBaseService<FuncDO> {

	/**
	 * 根据 funcIds 查询
	 * @param funcIds
	 * @param isDisabled
	 * @return
	 */
	default List<FuncDO> listByFuncIds(List<Long> funcIds, Boolean isDisabled){
		List<FuncDO> list = list(Wrappers.<FuncDO>lambdaQuery().in(FuncDO::getId, funcIds));

		if (isDisabled == null) {
			return list;
		}
		return list.stream().filter(item -> isDisabled.equals(item.getIsDisabled())).collect(Collectors.toList());
	}

	/**
	 * exist sql形式
	 *
	 * @param queryWrapper
	 * @param funcApplicationId
	 */
	public void addExistSqlIfFuncApplicationIdNotNull(QueryWrapper<FuncDO> queryWrapper, Object funcApplicationId);

	/**
	 * in 形式
	 *
	 * @param queryWrapper
	 * @param funcApplicationId
	 */
	public void addInIfFuncApplicationIdNotNull(QueryWrapper<FuncDO> queryWrapper, Object funcApplicationId);
}
