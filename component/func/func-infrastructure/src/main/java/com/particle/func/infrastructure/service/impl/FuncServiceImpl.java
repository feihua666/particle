package com.particle.func.infrastructure.service.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.infrastructure.funcapplicationfuncrel.mapper.FuncApplicationFuncRelMapper;
import com.particle.func.infrastructure.mapper.FuncMapper;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

	private static String funcApplicationFuncRelDOTableNameCache = null;
	private static String funcDOTableNameCache = null;
	private static String getQueryWrapperFuncApplicationIdExistSqlCache = null;

	private IBaseQueryCommandMapStruct<FuncDO> queryCommandMapStruct;

	private FuncApplicationFuncRelMapper funcApplicationFuncRelMapper;

	@Override
	protected FuncDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
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

	@Override
	public QueryWrapper<FuncDO> getQueryWrapper(QueryCommand queryForm) {
		QueryWrapper<FuncDO> queryWrapper = super.getQueryWrapper(queryForm);

		if (StrUtil.equalsAny(queryForm.getClass().getName(),
				"com.particle.func.client.dto.command.representation.FuncPageQueryCommand",
				"com.particle.func.client.dto.command.representation.FuncQueryListCommand")) {
			Object funcApplicationId = ReflectUtil.getFieldValue(queryForm, "funcApplicationId");
			//addExistSqlIffuncApplicationIdNotNull(queryWrapper, funcApplicationId);
			addInIffuncApplicationIdNotNull(queryWrapper, funcApplicationId);

		}


		return queryWrapper;
	}

	/**
	 * exist sql形式
	 * @param queryWrapper
	 * @param funcApplicationId
	 */
	public void addExistSqlIffuncApplicationIdNotNull(QueryWrapper<FuncDO> queryWrapper,Object funcApplicationId) {
		if (funcApplicationFuncRelDOTableNameCache == null) {
			TableName annotation = AnnotationUtil.getAnnotation(FuncApplicationFuncRelDO.class, TableName.class);
			funcApplicationFuncRelDOTableNameCache = annotation.value();
		}
		if (funcDOTableNameCache == null) {
			TableName annotation = AnnotationUtil.getAnnotation(FuncDO.class, TableName.class);
			funcDOTableNameCache = annotation.value();
		}
		if (getQueryWrapperFuncApplicationIdExistSqlCache == null) {
			// select id from component_func_application_func_rel where component_func_application_func_rel.func_id = component_func.id and component_func_application_func_rel.func_application_id = {funcApplicationId}
			getQueryWrapperFuncApplicationIdExistSqlCache = "select id from " + funcApplicationFuncRelDOTableNameCache + " where"
					+ funcApplicationFuncRelDOTableNameCache + ".func_id = " + funcDOTableNameCache + ".id"
					+ funcApplicationFuncRelDOTableNameCache + ".func_application_id = " + funcApplicationId;

		}

		queryWrapper.exists(funcApplicationId != null, getQueryWrapperFuncApplicationIdExistSqlCache);

	}

	/**
	 * in 形式
	 * @param queryWrapper
	 * @param funcApplicationId
	 */
	public void addInIffuncApplicationIdNotNull(QueryWrapper<FuncDO> queryWrapper,Object funcApplicationId){
		if (funcApplicationId != null) {
			List<FuncApplicationFuncRelDO> funcApplicationFuncRelDOS = funcApplicationFuncRelMapper.selectList(Wrappers.<FuncApplicationFuncRelDO>lambdaQuery().eq(FuncApplicationFuncRelDO::getFuncApplicationId, funcApplicationId));
			List<Long> collect = funcApplicationFuncRelDOS.stream().map(FuncApplicationFuncRelDO::getFuncId).collect(Collectors.toList());
			if (collect.isEmpty()) {
				// 为空就是不存在，不存在，得加一个不存在的条件
				queryWrapper.apply("false");
			}else {
				queryWrapper.in(FuncDO.COLUMN_ID,collect);
			}
		}
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<FuncDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}
	@Autowired
	public void setFuncApplicationFuncRelMapper(FuncApplicationFuncRelMapper funcApplicationFuncRelMapper) {
		this.funcApplicationFuncRelMapper = funcApplicationFuncRelMapper;
	}
}
