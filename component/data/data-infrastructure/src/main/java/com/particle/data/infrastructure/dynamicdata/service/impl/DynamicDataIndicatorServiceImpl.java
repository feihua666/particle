package com.particle.data.infrastructure.dynamicdata.service.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataCategoryDO;
import com.particle.data.infrastructure.dynamicdata.dos.DynamicDataIndicatorDO;
import com.particle.data.infrastructure.dynamicdata.mapper.DynamicDataCategoryMapper;
import com.particle.data.infrastructure.dynamicdata.mapper.DynamicDataIndicatorMapper;
import com.particle.data.infrastructure.dynamicdata.service.IDynamicDataIndicatorService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 动态数据指标 服务实现类
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:31:12
 */
@Component
public class DynamicDataIndicatorServiceImpl extends IBaseServiceImpl<DynamicDataIndicatorMapper, DynamicDataIndicatorDO> implements IDynamicDataIndicatorService {


	private static String dynamicDataIndicatorDOTableNameCache = null;
	private static String dynamicDataCategoryDOTableNameCache = null;
	private static String getQueryWrapperDynamicDataCategoryTypeDictIdExistSqlCache = null;

	private IBaseQueryCommandMapStruct<DynamicDataIndicatorDO> queryCommandMapStruct;

	private DynamicDataCategoryMapper dynamicDataCategoryMapper;
	@Override
	protected DynamicDataIndicatorDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<DynamicDataIndicatorDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	@Override
	protected void preAdd(DynamicDataIndicatorDO po) {
	}

	@Override
	protected void preUpdate(DynamicDataIndicatorDO po) {

	}


	@Override
	public QueryWrapper<DynamicDataIndicatorDO> getQueryWrapper(QueryCommand queryForm) {
		QueryWrapper<DynamicDataIndicatorDO> queryWrapper = super.getQueryWrapper(queryForm);

		if (StrUtil.equalsAny(queryForm.getClass().getName(),
				"com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorPageQueryCommand",
				"com.particle.data.client.dynamicdata.dto.command.representation.DynamicDataIndicatorQueryListCommand")) {
			Object dynamicDataCategoryTypeDictId = ReflectUtil.getFieldValue(queryForm, "dynamicDataCategoryTypeDictId");
            addExistSqlIfDynamicDataCategoryTypeDictIdNotNull(queryWrapper, dynamicDataCategoryTypeDictId);
			// addInIfDynamicDataCategoryTypeDictIdNotNull(queryWrapper, dynamicDataCategoryTypeDictId);

		}


		return queryWrapper;
	}

	/**
	 * exist sql形式
	 * @param queryWrapper
	 * @param dynamicDataCategoryTypeDictId
	 */
	public void addExistSqlIfDynamicDataCategoryTypeDictIdNotNull(QueryWrapper<DynamicDataIndicatorDO> queryWrapper, Object dynamicDataCategoryTypeDictId) {
		if (dynamicDataCategoryTypeDictId == null) {
			return;
		}
		if (dynamicDataIndicatorDOTableNameCache == null) {
			TableName annotation = AnnotationUtil.getAnnotation(DynamicDataIndicatorDO.class, TableName.class);
			dynamicDataIndicatorDOTableNameCache = annotation.value();
		}
		if (dynamicDataCategoryDOTableNameCache == null) {
			TableName annotation = AnnotationUtil.getAnnotation(DynamicDataCategoryDO.class, TableName.class);
			dynamicDataCategoryDOTableNameCache = annotation.value();
		}
		if (getQueryWrapperDynamicDataCategoryTypeDictIdExistSqlCache == null) {
			// select id from component_data_dynamic_data_category where component_data_dynamic_data_category.id = component_data_dynamic_data_indicator.dynamic_data_indicator_category_id and component_data_dynamic_data_category.type_dict_id = {}
			getQueryWrapperDynamicDataCategoryTypeDictIdExistSqlCache = "select id from " + dynamicDataCategoryDOTableNameCache +" where "
					+ dynamicDataCategoryDOTableNameCache +".id = "+ dynamicDataIndicatorDOTableNameCache +".dynamic_data_indicator_category_id" +
					" and "+ dynamicDataCategoryDOTableNameCache +".type_dict_id = ";

		}
		String finalSql = getQueryWrapperDynamicDataCategoryTypeDictIdExistSqlCache + dynamicDataCategoryTypeDictId;

		queryWrapper.exists(dynamicDataCategoryTypeDictId != null, finalSql);

	}

	/**
	 * in 形式
	 * @param queryWrapper
	 * @param dynamicDataCategoryTypeDictId
	 */
	public void addInIfDynamicDataCategoryTypeDictIdNotNull(QueryWrapper<DynamicDataIndicatorDO> queryWrapper, Object dynamicDataCategoryTypeDictId){
		if (dynamicDataCategoryTypeDictId == null) {
			return;
		}
		List<DynamicDataCategoryDO> dynamicDataCategoryDOS = dynamicDataCategoryMapper.selectList(Wrappers.<DynamicDataCategoryDO>lambdaQuery().eq(DynamicDataCategoryDO::getTypeDictId, dynamicDataCategoryTypeDictId));
		List<Long> collect = dynamicDataCategoryDOS.stream().map(DynamicDataCategoryDO::getId).collect(Collectors.toList());
		if (collect.isEmpty()) {
			// 为空就是不存在，不存在，得加一个不存在的条件
			queryWrapper.apply("false");
		}else {
			queryWrapper.in(DynamicDataIndicatorDO.COLUMN_DYNAMIC_DATA_INDICATOR_CATEGORY_ID,collect);
		}
	}
	@Autowired
	public void setDynamicDataCategoryMapper(DynamicDataCategoryMapper dynamicDataCategoryMapper) {
		this.dynamicDataCategoryMapper = dynamicDataCategoryMapper;
	}
}
